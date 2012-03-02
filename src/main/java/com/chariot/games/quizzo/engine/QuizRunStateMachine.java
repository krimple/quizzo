package com.chariot.games.quizzo.engine;

import com.chariot.games.quizzo.model.Answer;
import com.chariot.games.quizzo.model.QuizRun;
import com.chariot.games.quizzo.model.QuizRunState;
import com.chariot.games.quizzo.model.Team;

import java.math.BigDecimal;
import java.util.Map;

public interface QuizRunStateMachine {
  void initializeQuiz(Long quizId, String text);
  void enrollTeams();
  void startQuiz();
  boolean nextQuestion();
  Map<String, BigDecimal> getScores();
  Long getCurrentQuestionId();
  boolean submitAnswer(Team team, Answer answer);
  void endQuiz();
  QuizRun getQuizRun();
  QuizRunState getQuizRunState();
}
