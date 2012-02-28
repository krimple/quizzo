package com.chariot.games.quizzo.engine;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.chariot.games.quizzo.model.Choice;
import com.chariot.games.quizzo.model.ChoiceDataOnDemand;
import com.chariot.games.quizzo.model.Question;
import com.chariot.games.quizzo.model.QuestionDataOnDemand;
import com.chariot.games.quizzo.model.Quiz;
import com.chariot.games.quizzo.model.QuizDataOnDemand;
import com.chariot.games.quizzo.model.QuizRunState;
import com.chariot.games.quizzo.service.QuestionService;
import com.chariot.games.quizzo.service.QuizRunService;
import com.chariot.games.quizzo.service.QuizService;

@ContextConfiguration(locations = {"classpath*:META-INF/spring/applicationContext*.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = false)
public class QuizRunStateMachineMemoryTest {

  private static Logger logger = Logger.getLogger(QuizRunStateMachineMemoryTest.class);

  private static QuizRunStateMachine stateMachine;

  @Autowired
  private QuizService quizService;

  @Autowired
  private QuestionService questionService;

  @Autowired
  private QuizRunService quizRunService;

  @PersistenceContext
  private EntityManager em;

  @Autowired
  public void setStateMachine(QuizRunStateMachine stateMachine) {
    logger.debug("injecting state machine...");
    this.stateMachine = stateMachine;
  }

  @Before
  public void setUp() {
    logger.debug("Running setup...");
    Quiz quiz = setupQuiz();
    stateMachine.initializeQuiz(quiz.getId(), "Sample Run");
    stateMachine.startQuiz();
    logger.debug("Setup finished...");
  }

  @After
  public void tearDown() {
    //quizRunService.deleteQuizRun(stateMachine.getQuizRun());
  }

  @Test
  @Transactional
  @DirtiesContext
  public void testLoadQuizRunFromDatabase() {
    assertEquals(QuizRunState.IN_PROGRESS, stateMachine.getQuizRunState());
  }

  @Test
  @Transactional
  @DirtiesContext
  public void testSequentialQuestionIds() {
    Assert.assertEquals(QuizRunState.IN_PROGRESS, stateMachine.getQuizRunState());
    Assert.assertTrue(stateMachine.nextQuestion());
    long questionId = stateMachine.getCurrentQuestionId();
    stateMachine.nextQuestion();
    long questionId2 = stateMachine.getCurrentQuestionId();
    assertTrue(questionId2 != questionId);
    Question q1 = questionService.findQuestion(questionId);
    Question q2 = questionService.findQuestion(questionId2);
    assertTrue(q1.getSortOrder() <= q2.getSortOrder());
  }

  @Test
  @Transactional
  @DirtiesContext
  public void testFetchAllQuestionsAndDone() {
    Assert.assertEquals(QuizRunState.IN_PROGRESS, stateMachine.getQuizRunState());
    for (int i = 0; i < 5; i++) {
      Assert.assertTrue(stateMachine.nextQuestion());
    }
    assertFalse(stateMachine.nextQuestion());
    assertEquals(QuizRunState.COMPLETE, stateMachine.getQuizRunState());
  }

  private Quiz setupQuiz() {
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
    quizService.saveQuiz(quiz);

    em.flush();
    em.clear();
    return quiz;
  }
}
