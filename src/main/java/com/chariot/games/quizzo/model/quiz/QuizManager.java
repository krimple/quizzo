package com.chariot.games.quizzo.model.quiz;


public interface QuizManager {
  
  public Long startQuiz(long quizId);
  public void nextQuestion();
  public void previousQuestion();
  public Question getCurrentQuestion();
  public Score submitAnswers(QuizAnswer answer);

}
