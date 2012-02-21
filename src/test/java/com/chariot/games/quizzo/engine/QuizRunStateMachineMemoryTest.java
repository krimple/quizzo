package com.chariot.games.quizzo.engine;

import com.chariot.games.quizzo.model.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration(locations = {"classpath*:META-INF/spring/applicationContext*.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class QuizRunStateMachineMemoryTest {

  private QuizRun testQuizRun;
  private QuizRunStateMachine stateMachine;

  @Before
  public void setUp() {
    testQuizRun = new QuizRun();
    Quiz quiz = setupQuizRunModels();
    testQuizRun.setQuiz(quiz);
    testQuizRun.persist();
    testQuizRun.flush();
    testQuizRun.clear();
    stateMachine = new QuizRunStateMachineInMemory();
    stateMachine.startQuiz(testQuizRun.getId());
  }

  @Test
  @Transactional
  public void testLoadQuizRunFromDatabase() {
    QuizRunStateMachine runState = new QuizRunStateMachineInMemory();
    Quiz quiz = setupQuizRunModels();
    runState.startQuiz(quiz.getId());
  }

  @Test
  @Transactional
  public void testAskNextQuestion() {

    stateMachine.nextQuestion();
    long questionId = stateMachine.getCurrentQuestionId();


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
