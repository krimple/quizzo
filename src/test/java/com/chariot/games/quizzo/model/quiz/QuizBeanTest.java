package com.chariot.games.quizzo.model.quiz;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static junit.framework.Assert.assertNotNull;

public class QuizBeanTest {

    QuizBean quizBean;

    @Before
    public void setUp() {
        quizBean = new QuizBean();
    }

    @Test
    public void testCreateTeamMembers() {
        List<String> teamMembers = generateTestTeam();
        UUID uuid = quizBean.setupTeam("The Birds", "Fly on us, we'll poop on you.",
                teamMembers);
        assertNotNull(uuid);
    }

    private List<String> generateTestTeam() {
        List<String> teamMembers = new ArrayList<String>();
        teamMembers.add("Ken");
        teamMembers.add("Mark");
        return teamMembers;
    }

    @Test(expected = AssertionError.class)
    public void testCreateTeamMembersFailIfNotStarted() {
        quizBean.startGame();
        quizBean.setupTeam("The coyotes", "Don't go shopping for Acme", generateTestTeam());
    }

    public void test
}
