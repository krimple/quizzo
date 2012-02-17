package com.chariot.games.quizzo.model.quiz;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * This is meant to live as a Spring singleton bean
 */
@RooJavaBean
@Component("quiz")
public class QuizBean implements Quiz {

  @Override
  public Team setupTeam(String name, String description, List<String> teamMembers) {
    Team team = new Team(name, description);

  }

  @Override
  public Score submitAnswers(String teamId, Question question, List<QuizAnswer> answers) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public Score getTeamScore(String teamId) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public List<Score> getAllScores() {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void getNextQuestion() {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void getPreviousQuestion() {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void startVoting() {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void closeVoting() {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void startGame() {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void endGame() {
    //To change body of implemented methods use File | Settings | File Templates.
  }
}
