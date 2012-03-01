package com.chariot.games.quizzo.engine;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.chariot.games.quizzo.model.*;
import com.chariot.games.quizzo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.chariot.games.quizzo.service.AnswerByChoiceService;

@Component(value = "quizStateMachine")
public class QuizRunStateMachineInMemory implements QuizRunStateMachine {

  /**
   * The quiz under execution by this instance
   */
  private QuizRun quizRun;

  @Autowired
  private TeamService teamService;

  @Autowired
  private QuestionService questionService;

  @Autowired
  private AnswerByChoiceService answerByChoiceService;

  @Autowired
  private FillInTheBlankAnswerService fillInTheBlankAnswerService;

  @Autowired
  private QuizService quizService;

  @Autowired
  private QuizRunService quizRunService;

  private QuizRunState runState = QuizRunState.NOT_STARTED;

  /**
   * Our key to the current question ID. All questions are sorted by a sort order.
   */
  private int currentQuestionIndex;

  private List<Question> questions;

  @Override
  @Transactional
  public void initializeQuiz(Long quizId, String text) {
    Quiz quiz = quizService.findQuiz(quizId);
    quizRun = new QuizRun();
    quizRun.setQuiz(quiz);
    runState = QuizRunState.NOT_STARTED;
    quizRun.setText(text);
    quizRunService.saveQuizRun(quizRun);
    questions = questionService.getQuestionsByQuizId(quiz.getId());
  }

  @Override
  @Transactional
  public void enrollTeams() {
    assert (runState == QuizRunState.NOT_STARTED);
    runState = QuizRunState.ENROLL_TEAMS;
  }

  @Override
  @Transactional
  public void startQuiz() {
    // now, load up our first question...
    assert (questions != null && questions.size() > 0);
    assert runState == QuizRunState.ENROLL_TEAMS;

    runState = QuizRunState.IN_PROGRESS;
    currentQuestionIndex = -1;
  }

  @Override
  @Transactional
  public boolean nextQuestion() {
    if (runState != QuizRunState.IN_PROGRESS) System.err.println("Next Question Failed!");
    assert runState == QuizRunState.IN_PROGRESS;
    if (currentQuestionIndex + 1 < questions.size()) {
      currentQuestionIndex++;
      return true;
    } else {
      runState = QuizRunState.COMPLETE;
      return false;
    }
  }

  @Override
  public Map<String, BigDecimal> getScores() {
    return teamService.calcScoresByQuizRun(quizRun.getId());
  }

  @Override
  public Long getCurrentQuestionId() {
    return questions.get(currentQuestionIndex).getId();
  }

  @Override
  @Transactional
  public boolean submitAnswer(Team team, AnswerByChoice answer) {
    // voting over sucker, you are hosed...
    if (!getCurrentQuestionId().equals(answer.getQuestion().getId())) return false;

    // otherwise, wipe existing answer & save new one
    List<AnswerByChoice> existingAnswers = answerByChoiceService.getAnswersByTeamIdAndQuestionId(
        team.getId(), answer.getQuestion().getId());

    // remove current answer to overwrite with new one
    if (existingAnswers.size() == 1) {
      answerByChoiceService.deleteAnswerByChoice(existingAnswers.get(0));
    }
    answerByChoiceService.saveAnswerByChoice(answer);
    return true;
  }

  @Override
  @Transactional
  boolean submitTextAnswer(Team team, FillInTheBlankAnswer answer) {
    if (!getCurrentQuestionId().equals(answer.getQuestion().getId())) return false;
    if (fillInTheBlankAnswerService.fin)
    fillInTheBlankAnswerService.deleteFillInTheBlankAnswer(answer);
  }


  @Override
  public void endQuiz() {
    runState = QuizRunState.COMPLETE;
  }

  public QuizRun getQuizRun() {
    return quizRun;
  }


  public QuizRunState getQuizRunState() {
    return runState;
  }
}
