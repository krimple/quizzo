package com.chariot.games.quizzo.model.quiz;

import java.util.List;
import java.util.UUID;

public interface Quiz {

  UUID setupTeam(String name, String description, List<String> teamMembers);
  Score submitAnswers(UUID team, List<QuizAnswer> answers);
  Score getTeamScore(UUID team);
  List<Score> getAllScores();
  void getNextQuestion();
  void getPreviousQuestion();
  void startVoting();
  void closeVoting();
  void startGame();
  void endGame();
}
