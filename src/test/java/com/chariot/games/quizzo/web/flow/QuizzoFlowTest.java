package com.chariot.games.quizzo.web.flow;

import com.chariot.games.quizzo.web.form.TeamSetupForm;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.webflow.config.FlowDefinitionResource;
import org.springframework.webflow.config.FlowDefinitionResourceFactory;
import org.springframework.webflow.core.collection.LocalAttributeMap;
import org.springframework.webflow.core.collection.MutableAttributeMap;
import org.springframework.webflow.test.MockExternalContext;
import org.springframework.webflow.test.MockFlowBuilderContext;
import org.springframework.webflow.test.execution.AbstractXmlFlowExecutionTests;

import java.util.ArrayList;
import java.util.List;

public class QuizzoFlowTest extends AbstractXmlFlowExecutionTests {

  @Override
  protected FlowDefinitionResource getResource(FlowDefinitionResourceFactory resourceFactory) {
    return resourceFactory.
        createFileResource("src/main/webapp/WEB-INF/views/playQuizzo/flow.xml");
  }

//  @Override
//  protected void configureFlowBuilderContext(MockFlowBuilderContext builderContext) {
//      //builderContext.registerBean("bookingService", new StubBookingService());
//  }

  @Test
  public void testStartFlow() {
    MutableAttributeMap map = new LocalAttributeMap();
    MockExternalContext context = new MockExternalContext();
    startFlow(map, context);
    assertCurrentStateEquals("register-team");
    TeamSetupForm form = (TeamSetupForm) getFlowScope().get("teamSetupForm");
    assertNotNull(form);
  }

  @Test
  public void testSubmitTeamName() {
    setCurrentState("register-team");
    MockExternalContext context = new MockExternalContext();
    getFlowScope().put("teamSetupForm",
        createTeamSetupForm("The Jets", "When you're a Jet you're a Jet"));

    context.setEventId("continue");
    resumeFlow(context);
    assertCurrentStateEquals("register-team-members");
  }

  @Test
  public void testSubmitTeamMembers() {
    setCurrentState("register-team-members");

    MockExternalContext context = new MockExternalContext();
    getFlowScope().put("teamSetupForm",
        createTeamSetupForm("The Jets",
                "When you're a Jet you're a Jet",
        "Ice", "Action", "Baby John", "Tiger", "Joyboy"));

    context.setEventId("continue");
    resumeFlow(context);
    assertCurrentStateEquals("play-round");
  }

  @Test
  @Ignore
  public void testPoll() {

    // we do this in QuizzoFlowPollingTest due to the limitations of
    // the one pass of the configureFlowBuilderServices() method, where
    // we are registering our quizzo manager bean.

  }

  private TeamSetupForm createTeamSetupForm(String name, String message, String... names) {
    TeamSetupForm form = new TeamSetupForm();
    form.setName("Boogity Boogity");
    form.setMessage("Boo, it's a message");
    List<String> teamMembers = new ArrayList<String>();
    for (String teamMember : names) {
      teamMembers.add(teamMember);
    }

    return form;
  }

  @Override
  protected void configureFlowBuilderContext(MockFlowBuilderContext builderContext) {
    System.out.println("configuring context");
    StubQuizzoFlowManager stubQuizzoFlowManager = new StubQuizzoFlowManager();
    builderContext.registerBean("quizzoFlowManager", stubQuizzoFlowManager);
  }
}
