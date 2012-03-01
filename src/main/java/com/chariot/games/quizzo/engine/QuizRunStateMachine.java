package com.chariot.games.quizzo.engine;

import com.chariot.games.quizzo.model.*;

import java.math.BigDecimal;
import java.util.Map;

public interface QuizRunStateMachine {
  void initializeQuiz(Long quizId, String text);
  void enrollTeams();
  void startQuiz();
  boolean nextQuestion();
  Map<String, BigDecimal> getScores();
  Long getCurrentQuestionId();
  boolean submitAnswer(Team team, AnswerByChoice answer);
  boolean submitTextAnswer(Team team, FillInTheBlankAnswer answer);
  void endQuiz();
  QuizRun getQuizRun();
  QuizRunState getQuizRunState();
}
