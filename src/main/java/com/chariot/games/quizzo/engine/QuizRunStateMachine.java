package com.chariot.games.quizzo.engine;

import com.chariot.games.quizzo.model.Answer;
import com.chariot.games.quizzo.model.QuizRun;
import com.chariot.games.quizzo.model.Team;

import java.math.BigDecimal;
import java.util.Map;

public interface QuizRunStateMachine {
  void startQuiz(Long quizId, String text);
  boolean nextQuestion();
  Map<String, BigDecimal> getScores();
  Long getCurrentQuestionId();
  boolean submitAnswer(Team team, Answer answer);
  void endQuiz();
  QuizRun getQuizRun();
}
