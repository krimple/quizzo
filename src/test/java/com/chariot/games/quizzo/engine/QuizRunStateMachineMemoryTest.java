package com.chariot.games.quizzo.engine;

import com.chariot.games.quizzo.model.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@ContextConfiguration(locations = {"classpath*:META-INF/spring/applicationContext*.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class QuizRunStateMachineMemoryTest {

  private QuizRun testQuizRun;

  @Autowired
  private QuizRunStateMachine stateMachine;

  @Autowired
  private DataSource dataSource;

  @Before
  public void setUp() {
    testQuizRun = new QuizRun();
    testQuizRun.setRunState(QuizRunState.NOT_STARTED);
    Quiz quiz = setupQuizRunModels();
    testQuizRun.setText("Hiya mom");
    testQuizRun.setQuiz(quiz);
    testQuizRun.persist();
    testQuizRun.flush();
    testQuizRun.clear();

    stateMachine.startQuiz(quiz.getId(), "Sample Run");
    stateMachine.nextQuestion();
  }

  @Test
  @Transactional
  public void testLoadQuizRunFromDatabase() {
    Quiz quiz = setupQuizRunModels();
    stateMachine.startQuiz(quiz.getId(), "Random run");
    assertNotNull(stateMachine.getCurrentQuestionId());
  }

  @Test
  @Transactional
  public void testCheckNextQuestionId() {
    long questionId = stateMachine.getCurrentQuestionId();
  }

  @Test
  @Transactional
  public void testSequentialQuestionIds() {
    long questionId = stateMachine.getCurrentQuestionId();
    stateMachine.nextQuestion();
    long questionId2 = stateMachine.getCurrentQuestionId();
    assertTrue (questionId2 != questionId);
    Question q1 = Question.findQuestion(questionId);
    Question q2 = Question.findQuestion(questionId2);
    assertTrue(q1.getSortOrder() <= q2.getSortOrder());
  }

  @Test
  @Transactional
  public void testFetchAllQuestionsAndDone() {
    stateMachine.nextQuestion();
    stateMachine.nextQuestion();
    stateMachine.nextQuestion();
    stateMachine.nextQuestion();
    assertFalse(stateMachine.nextQuestion());
  }

  private Quiz setupQuizRunModels() {
    QuizDataOnDemand dod = new QuizDataOnDemand();
    Quiz quiz = dod.getRandomQuiz();
    QuestionDataOnDemand qdod = new QuestionDataOnDemand();
    for (int i = 0; i < 5; i++) {
      ChoiceDataOnDemand cdod = new ChoiceDataOnDemand();
      Question q = qdod.getNewTransientQuestion(i);
      q.setQuiz(quiz);
      quiz.getQuestions().add(q);
      for (int j = 0; j < 5; j++) {
        Choice c = cdod.getNewTransientChoice(i);
        c.setQuestion(q);
        q.getChoices().add(c);

      }
    }
    quiz.merge();
    quiz.flush();
    quiz.clear();
    return quiz;
  }
}
