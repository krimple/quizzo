package com.chariot.games.quizzo.engine;

import com.chariot.games.quizzo.model.*;
import com.chariot.games.quizzo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Manages a map of quiz runs by their quiz run ID. The controllers are
 * responsible for holding on to a state run for a given user (admin or player)
 * and the user should never see the quiz run ID as it should live in something
 * on the server side (i.e. session).
 * <p/>
 * This is a naieve implementation - I'm assuming we'll cut it apart at Chariot
 * Day for threading issues etc. I tried putting the quizrun state in the database
 * but ran into trouble when it would not move the state properly. However, I want
 * to fix that and persist the game state so we can resume it from a shutdown/startup
 * (under less stress than the day before the event).
 */
@Component(value = "quizStateMachine")
@Secured(value = "hasRole('ROLE_ADMIN')")
public class QuizRunStateMachineInMemory implements QuizRunStateMachine {
  class QuizRunData {
    QuizRunData(QuizRun quizRun, QuizRunState quizRunState) {
      this.quizRun = quizRun;
      this.quizRunState = quizRunState;

    }

    private List<Question> questions;
    private QuizRun quizRun;
    private QuizRunState quizRunState;
    private int currentQuestionIndex;

    public boolean hasMoreQuestions() {
      if (currentQuestionIndex + 1 < questions.size()) {
        currentQuestionIndex++;
        return true;
      } else {
        quizRunState = QuizRunState.COMPLETE;
        return false;
      }
    }
  }

  //TODO = synchronize
  private Map<Long, QuizRunData> quizRuns
      = new HashMap<Long, QuizRunData>();

  /* LES EVIL COLLABORATORS - WE ARE A SERVICE FACADE TO OUR CONTROLLERS FOR MOST THINGS */

  @Autowired
  private TeamService teamService;

  @Autowired
  private QuestionService questionService;

  @Autowired
  private AnswerService answerService;

  @Autowired
  private QuizService quizService;

  @Autowired
  private QuizRunService quizRunService;

  @Autowired
  private ChoiceService choiceService;

  @Override
  @Transactional
  public long createQuizRun(long quizId, String text) {
    Quiz quiz = quizService.findQuiz(quizId);
    QuizRun quizRun = new QuizRun();
    quizRun.setQuiz(quiz);
    quizRun.setText(text);
    quizRunService.saveQuizRun(quizRun);
    QuizRunData quizRunData = new QuizRunData(quizRun, QuizRunState.NOT_STARTED);
    quizRuns.put(quizRun.getId(), quizRunData);
    quizRunData.questions = questionService.getQuestionsByQuizId(quiz.getId());
    return quizRun.getId();
  }

  @Override
  @Transactional
  public void enrollTeams(long quizRunId) {
    QuizRunData data = getQuizRunData(quizRunId);
    if (data == null) throw new RuntimeException("Improper Quiz Run ID");
    assert (data.quizRunState == QuizRunState.NOT_STARTED);
    data.quizRunState = QuizRunState.ENROLL_TEAMS;
  }

  @Override
  @Transactional
  public void startQuizRun(long quizRunId) {
    QuizRunData data = getQuizRunData(quizRunId);

    // now, load up our first question...
    assert (data.questions != null && data.questions.size() > 0);
    assert (data.quizRunState == QuizRunState.ENROLL_TEAMS);

    data.quizRunState = QuizRunState.IN_PROGRESS;
    data.currentQuestionIndex = -1;
  }

  @Override
  @Transactional
  public boolean nextQuestion(long quizRunId) {
    QuizRunData data = getQuizRunData(quizRunId);

    if (data.quizRunState != QuizRunState.IN_PROGRESS) System.err.println("Next Question Failed!");
    assert data.quizRunState == QuizRunState.IN_PROGRESS;
    return data.hasMoreQuestions();
  }

  @Override
  @Transactional
  public Map<String, BigDecimal> getScores(long quizRunId) {
    return teamService.calcScoresByQuizRun(quizRunId);
  }

  @Override
  @Transactional
  public BigDecimal getScoreForTeam(long teamId) {
    return teamService.findTeam(teamId).calculateTotalScore();
  }

  @Override
  @Transactional
  @Secured(value = "hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
  public Question getCurrentQuestion(long quizRunId) {
    QuizRunData data = getQuizRunData(quizRunId);
    int questionIndex = data.currentQuestionIndex;
    return data.questions.get(questionIndex);
  }

  @Override
  public boolean hasMoreQuestions(long quizRunId) {
    QuizRunData data = getQuizRunData(quizRunId);
    return data.hasMoreQuestions();
  }

  @Override
  @Transactional
  @Secured(value = "hasRole('ROLE_USER')")
  public boolean submitAnswer(long quizRunId, long teamId, long choiceId) {
    QuizRunData data = getQuizRunData(quizRunId);
    Team team = teamService.findTeam(teamId);
    Question currentQuestion = getCurrentQuestion(quizRunId);


    Answer answer = new Answer();
    Choice choice = choiceService.findChoice(choiceId);
    // broken window
    Long currentRunStateQuestionId = data.questions.get(data.currentQuestionIndex).getId();

    if (!currentRunStateQuestionId.equals(choice.getQuestion().getId())) return false; // we've moved on

    // otherwise we're good, vote away man
    answer.setChoice(choice);
    answer.setTeam(team);
    answerService.replaceAnswers(team, currentQuestion, choice);

    return true;
  }

  @Secured(value = "hasRole('ROLE_USER')")
  @Transactional
  public long registerTeam(Long quizRunId, String teamName, String mission) {

    Team team = new Team();
    team.setName(teamName);
    team.setMission(mission);

    QuizRun quizRun = getQuizRun(quizRunId);
    if (quizRun != null) {
      QuizRunState state = getQuizRunState(quizRunId);
      if (state != QuizRunState.ENROLL_TEAMS) {
        throw new IllegalArgumentException("Team cannot be registered, as we are not enrolling teams.");
      }
    } else {
      throw new IllegalArgumentException("No Quiz Run started with id of " + quizRunId);
    }

    team.setQuizRun(quizRun);
    teamService.saveTeam(team);
    return team.getId();
  }


  /**
   * Utilities
   */
  @Override
  @Transactional
  public void endQuizRun(long quizRunId) {
    QuizRunData data = quizRuns.get(quizRunId);
    QuizRunState currentState = quizRuns.get(quizRunId).quizRunState;
    if (currentState == QuizRunState.NOT_STARTED || currentState == QuizRunState.COMPLETE) {
      throw new IllegalArgumentException("cannot stop a completed or non-started quiz. Current state is " + currentState);
    }
    data.quizRunState = QuizRunState.COMPLETE;

    //TODO - figure out removal of old quizRuns - beyond restarting or initializing the map
    // hey, good idea for now...
  }

  @Override
  public QuizRunState getQuizRunState(long quizRunId) {
    QuizRunData data = quizRuns.get(quizRunId);
    return data.quizRunState;
  }

  private QuizRun getQuizRun(long quizRunId) {
    return quizRuns.get(quizRunId).quizRun;
  }

  private QuizRunData getQuizRunData(long quizRunId) {
    return quizRuns.get(quizRunId);
  }
}
