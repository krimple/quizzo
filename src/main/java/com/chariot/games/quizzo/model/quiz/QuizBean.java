package com.chariot.games.quizzo.model.quiz;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * This is meant to live as a Spring singleton bean
 */
@RooJavaBean
@Component("quiz")
public class QuizBean implements Quiz {

  private GameState gameState = GameState.NOT_STARTED;
  Map<UUID, Team> teams = new HashMap<UUID, Team>();

  @Override
  public UUID setupTeam(String name, String description, List<String> teamMembers) throws IllegalStateException {
    if (gameState != GameState.NOT_STARTED) throw new IllegalStateException("Teams cannot be set up once the game is in play");
    Team team = new Team(name, description);
    UUID uuid = UUID.randomUUID();
    teams.put(uuid, team);
    return uuid;
  }

  @Override
  public Score submitAnswers(UUID team, List<QuizAnswer> answers) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public Score getTeamScore(UUID team) {
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
    assert gameState == GameState.NOT_STARTED;
    gameState = GameState.IN_PROGRESS;
  }

  @Override
  public void endGame() {
    assert gameState == GameState.IN_PROGRESS;
      gameState = GameState.COMPLETE;
  }

  public GameState getGameState() {
      return this.gameState;
  }
}
