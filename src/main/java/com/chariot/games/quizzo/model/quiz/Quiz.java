package com.chariot.games.quizzo.model.quiz;

import java.util.List;

public interface Quiz {
  
  Team setupTeam(String name, String description, List<String> teamMembers);
  Score submitAnswers(String teamId, Question question, List<QuizAnswer> answers);
  Score getTeamScore(String teamId);
  List<Score> getAllScores();
  void getNextQuestion();
  void getPreviousQuestion();
  void startVoting();
  void closeVoting();
  void startGame();
  void endGame();
  
}
