package com.chariot.games.quizzo.service;


import com.chariot.games.quizzo.model.Quiz;
import org.hibernate.Hibernate;
import org.springframework.transaction.annotation.Transactional;

public class QuizServiceBean implements QuizService {
  @Override
  @Transactional
  public Quiz getQuizAndQuestions(Long quizId) {
    Quiz quiz = findQuiz(quizId);
    Hibernate.initialize(quiz);
    quiz.getQuestions();
    return quiz;
  }
}
