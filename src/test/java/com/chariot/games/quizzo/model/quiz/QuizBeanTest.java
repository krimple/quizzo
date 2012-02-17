package com.chariot.games.quizzo.model.quiz;

import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

public class QuizBeanTest {
  
  QuizBean quizBean;
  @Before
  public void setUp() {
    quizBean = new QuizBean();
  }

  public void test() {
    List<String> teamMembers = new ArrayList<String>();
    teamMembers.add("Ken");
    teamMembers.add("Mark");
    quizBean.setupTeam("The Birds", "Fly on us, we'll poop on you.", 
        teamMembers);
    

    
  }
}
