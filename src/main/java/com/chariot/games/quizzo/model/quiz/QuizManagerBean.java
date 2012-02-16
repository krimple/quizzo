package com.chariot.games.quizzo.model.quiz;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class QuizManagerBean implements QuizManager {

  private Map<Long, QuizSession> quizSessions = new HashMap<Long, QuizSession>();
  
  @Override
  public Long startQuiz(long quizId) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void nextQuestion() {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void previousQuestion() {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public Question getCurrentQuestion() {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public Score submitAnswers(QuizAnswer answer) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }
}
