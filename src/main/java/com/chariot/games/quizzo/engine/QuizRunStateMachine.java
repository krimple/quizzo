package com.chariot.games.quizzo.engine;

import com.chariot.games.quizzo.model.Answer;
import com.chariot.games.quizzo.model.Team;

public interface QuizRunStateMachine {
  void startQuiz(Long quizId);
  void nextQuestion();
  void getScores();
  Long getCurrentQuestionId();
  void submitAnswer(Team team, Answer answer);
  void endQuiz();
}
