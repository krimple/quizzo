package com.chariot.games.quizzo.engine;

import com.chariot.games.quizzo.model.*;
import com.chariot.games.quizzo.service.QuestionService;
import com.chariot.games.quizzo.service.QuizRunService;
import com.chariot.games.quizzo.service.QuizService;
import com.chariot.games.quizzo.service.TeamService;
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
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@ContextConfiguration(locations = {"classpath*:META-INF/spring/applicationContext*.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class QuizRunStateMachineMemoryTest {

  private static Logger logger = Logger.getLogger(QuizRunStateMachineMemoryTest.class);

  private static QuizRunStateMachine stateMachine;

  private List<Long> teamIds
      = new ArrayList<Long>();

  private Long quizRunId = null;

  @Autowired
  private QuizService quizService;

  @Autowired
  private QuestionService questionService;

  @Autowired
  private QuizRunService quizRunService;

  @Autowired
  private TeamService teamService;


  @PersistenceContext
  private EntityManager em;

  @Autowired
  public void setStateMachine(QuizRunStateMachine stateMachine) {
    logger.debug("injecting state machine...");
    this.stateMachine = stateMachine;
  }

  @Before
  public void loadQuizDataAndStartQuizRun() {
    logger.debug("Loading and configuring Quiz...");
    Quiz quiz = setupQuizWithQuestions();
    logger.debug("Generating a new quiz run and storing");
    quizRunId = stateMachine.createQuizRun(quiz.getId(), "Sample Run");
    logger.debug("new quiz run id is " + quizRunId);
    stateMachine.enrollTeams(quizRunId);
    logger.debug("quiz run state is now ENROLL_TEAMS");
    setupTeams(quizRunId);
    logger.debug("teams set up");
    stateMachine.startQuizRun(quizRunId);
    logger.debug("quiz run started. Here we go...");
  }

  @After
  public void tearDown() {
    //quizRunService.deleteQuizRun(stateMachine.getQuizRun());
  }

  @Test
  @Transactional
  @DirtiesContext
  public void testQuizIsStarted() {
    assertEquals(QuizRunState.READY_TO_PLAY, stateMachine.getQuizRunState(quizRunId));
  }

  @Test
  @Transactional
  @DirtiesContext
  public void testSequentialQuestionIds() {
    Assert.assertEquals(QuizRunState.READY_TO_PLAY, stateMachine.getQuizRunState(quizRunId));
    Assert.assertTrue(stateMachine.askNextQuestion(quizRunId));
    stateMachine.reviewAnswer(quizRunId);
    long questionId = stateMachine.getCurrentQuestion(quizRunId).getId();
    stateMachine.askNextQuestion(quizRunId);
    long questionId2 = stateMachine.getCurrentQuestion(quizRunId).getId();
    assertTrue(questionId2 != questionId);
    Question q1 = questionService.findQuestion(questionId);
    Question q2 = questionService.findQuestion(questionId2);
    assertTrue(q1.getSortOrder() <= q2.getSortOrder());
  }

  @Test
  @Transactional
  @DirtiesContext
  public void testFetchAllQuestionsAndDone() {
    Assert.assertEquals(QuizRunState.READY_TO_PLAY, stateMachine.getQuizRunState(quizRunId));
    for (int i = 0; i < 5; i++) {
      Assert.assertTrue(stateMachine.askNextQuestion(quizRunId));
      stateMachine.reviewAnswer(quizRunId);
    }
    assertFalse(stateMachine.hasMoreQuestions(quizRunId));
    stateMachine.endQuizRun(quizRunId);
    assertEquals(QuizRunState.COMPLETE, stateMachine.getQuizRunState(quizRunId));
  }

  private Quiz setupQuizWithQuestions() {
    QuizDataOnDemand dod = new QuizDataOnDemand();
    QuestionDataOnDemand qdod = new QuestionDataOnDemand();
    Quiz quiz = dod.getRandomQuiz();

    for (short i = 0; i < 5; i++) {

      Question q = qdod.getNewTransientQuestion(i);
      quiz.getQuestions().add(q);
      q.setQuiz(quiz);
      q.setSortOrder(i);

      for (int j = 0; j < 5; j++) {
        ChoiceDataOnDemand cdod = new ChoiceDataOnDemand();
        Choice c = cdod.getNewTransientChoice(j);
        c.setQuestion(q);
        c.setPointValue(new BigDecimal("3.0"));
        c.setCorrect(j % 2 == 0);
        q.getChoices().add(c);
      }
    }
    quizService.saveQuiz(quiz);

    em.flush();
    em.clear();
    return quiz;
  }

  private void setupTeams(Long quizRunId) {

    List<Long> teamIds = new ArrayList<Long>();
    Team team1 = new Team();
    team1.setName("The wingnuts");
    team1.setMission("to be wingnuts.");

    teamIds.add(stateMachine.registerTeam(quizRunId, "The Wingnuts", "to be wingnuts"));
    teamIds.add(stateMachine.registerTeam(quizRunId, "The Bolts", "to be bolted."));
  }
}
