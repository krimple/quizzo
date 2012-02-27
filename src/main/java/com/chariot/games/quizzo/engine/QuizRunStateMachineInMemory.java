package com.chariot.games.quizzo.engine;

import com.chariot.games.quizzo.model.*;
import com.chariot.games.quizzo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

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
  private AnswerService answerService;

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

  @Transactional
  public void startQuiz() {
    assert (runState == QuizRunState.NOT_STARTED);
    runState = QuizRunState.IN_PROGRESS;
  }

  @Transactional
  private void setupFirstQuestion() {
    // now, load up our first question...
    assert (questions != null && questions.size() > 0);
    assert runState == QuizRunState.NOT_STARTED;

    runState = QuizRunState.IN_PROGRESS;
    currentQuestionIndex = -1;
  }

  @Override
  @Transactional(readOnly = true)
  public boolean nextQuestion() {
    assert runState == QuizRunState.IN_PROGRESS;
    if (currentQuestionIndex + 1 > questions.size()) {
      runState = QuizRunState.COMPLETE;
      return false;
    } else {
      currentQuestionIndex++;
      return true;
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
  public boolean submitAnswer(Team team, Answer answer) {
    // voting over sucker, you are hosed...
    if (!getCurrentQuestionId().equals(answer.getQuestion().getId())) return false;

    // otherwise, wipe existing answer & save new one
    List<Answer> existingAnswers = answerService.getAnswersByTeamIdAndQuestionId(
        team.getId(), answer.getQuestion().getId());

    // remove current answer to overwrite with new one
    if (existingAnswers.size() == 1) {
      answerService.deleteAnswer(existingAnswers.get(0));
    }
    answerService.saveAnswer(answer);
    return true;
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
