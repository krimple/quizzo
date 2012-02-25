package com.chariot.games.quizzo.web.flow;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.mock.staticmock.MockStaticEntityMethods;
import org.springframework.webflow.test.MockFlowSession;
import org.springframework.webflow.test.MockRequestContext;

import java.util.ArrayList;
import java.util.List;

@MockStaticEntityMethods
public class QuizzoFlowManagerBeanTest {

  private QuizzoFlowManagerBean flowManager;

  @Test
  @Ignore
  //TODO - convert to mocking and DAOs once repository layer is in place
  public void testSetupTeam() {
    MockRequestContext requestContext = new MockRequestContext();
    TeamSetupForm teamSetupForm = new TeamSetupForm();
    teamSetupForm.setName("Wonder pigs");
    teamSetupForm.setMessage("We oink with quizzo.");
    List<String> memberNames = new ArrayList<String>();
    memberNames.add("Stinky");
    memberNames.add("Smelly");
    memberNames.add("Oinky");

    MockFlowSession flowSession = new MockFlowSession();

    //requestContext.setActiveSession(n);
  }
}
