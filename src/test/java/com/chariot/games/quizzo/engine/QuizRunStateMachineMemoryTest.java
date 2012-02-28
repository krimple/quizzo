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
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
  public void setUp() {
    logger.debug("Running setup...");
    Quiz quiz = setupQuiz();
    stateMachine.initializeQuiz(quiz.getId(), "Sample Run");
    stateMachine.enrollTeams();
    logger.debug("Teams enrolled.");
    setupTeams(stateMachine.getQuizRun().getId());
    logger.debug("Setup and Team Enroll finished...");
    stateMachine.startQuiz();
  }

  private void setupTeams(Long id) {
    Team team1 = new Team();
    team1.setName("The wingnuts");
    team1.setMission("to be wingnuts.");
    TeamMember tm1 = new TeamMember();
    tm1.setName("Joey");
    TeamMember tm2 = new TeamMember();
    tm2.setName("Freddie");
    List<TeamMember> teamMemberList1 = new ArrayList<TeamMember>();
    teamMemberList1.add(tm1);
    teamMemberList1.add(tm2);
    team1.setTeamMembers(teamMemberList1);
    teamService.saveTeam(team1);

    Team team2 = new Team();
    team2.setName("The bolts");
    team2.setMission("to be bolted.");
    tm1 = new TeamMember();
    tm1.setName("Artie");
    tm2 = new TeamMember();
    tm2.setName("Ralph");
    List<TeamMember> teamMemberList2 = new ArrayList<TeamMember>();
    teamMemberList2.add(tm1);
    teamMemberList2.add(tm2);
    team2.setTeamMembers(teamMemberList2);
    teamService.saveTeam(team2);
  }

  @After
  public void tearDown() {
    //quizRunService.deleteQuizRun(stateMachine.getQuizRun());
  }

  @Test
  @Transactional
  @DirtiesContext
  public void testQuizIsStarted() {
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
