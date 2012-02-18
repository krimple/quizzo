package com.chariot.games.quizzo.model.quiz;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

public class QuizBeanTest {

  QuizBean quizBean;

  @Before
  public void setUp() {
    quizBean = new QuizBean();
  }

  @Test
  public void testCreateTeamMembers() {
    UUID teamToken = generateAndAddTeam();
    assertNotNull(teamToken);
    assertEquals(GameState.NOT_STARTED, quizBean.getGameState());
  }

  @Test
  public void testStartQuiz() {
    generateAndAddTeam();
    quizBean.startGame();
    assertEquals(GameState.IN_PROGRESS, quizBean.getGameState());
  }

  @Test
  public void testSetupTeamMembers() {
    UUID teamToken = generateAndAddTeam();
    assertNotNull(teamToken);
    assertEquals(GameState.NOT_STARTED, quizBean.getGameState());
  }

  private UUID generateAndAddTeam() {
    List<String> teamMembers = new ArrayList<String>();
    teamMembers.add("Ken");
    teamMembers.add("Mark");
    UUID uuid = quizBean.setupTeam("The Birds", "Fly on us, we'll poop on you.",
        teamMembers);
    return uuid;
  }

  @Test(expected = IllegalStateException.class)
  public void testCreateTeamMembersFailWhenStarted() {
    quizBean.startGame();
    generateAndAddTeam();
  }

  @Test
  public void testCloseVoting() {
    quizBean.closeVoting();
    throw new RuntimeException("FINISH ME");
  }
}
