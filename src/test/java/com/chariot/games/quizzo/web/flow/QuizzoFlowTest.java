package com.chariot.games.quizzo.web.flow;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.webflow.config.FlowDefinitionResource;
import org.springframework.webflow.config.FlowDefinitionResourceFactory;
import org.springframework.webflow.core.collection.LocalAttributeMap;
import org.springframework.webflow.core.collection.MutableAttributeMap;
import org.springframework.webflow.test.MockExternalContext;
import org.springframework.webflow.test.MockFlowBuilderContext;
import org.springframework.webflow.test.MockRequestContext;
import org.springframework.webflow.test.execution.AbstractXmlFlowExecutionTests;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

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
    QuizzoFlowManager quizzoFlowManager = mock(QuizzoFlowManager.class);
    MockExternalContext context = new MockExternalContext();
    MutableAttributeMap map = new LocalAttributeMap();

    MockFlowBuilderContext flowBuilderContext = new MockFlowBuilderContext("playQuizzo", map);
    getFlowScope().put("teamSetupForm",
        createTeamSetupForm("The Jets", "When you're a Jet you're a Jet"));

    flowBuilderContext.registerBean("quizzoFlowManager", quizzoFlowManager);
//    configureFlowBuilderContext(flowBuilderContext);
    context.setEventId("continue");
    resumeFlow(context);
    assertCurrentStateEquals("register-team-members");
  }

  @Test
  public void testSubmitTeamMembers() {
    setCurrentState("register-team-members");
    QuizzoFlowManager quizzoFlowManager = mock(QuizzoFlowManager.class);
    MockExternalContext context = new MockExternalContext();
    MutableAttributeMap map = new LocalAttributeMap();

    MockFlowBuilderContext flowBuilderContext = new MockFlowBuilderContext("playQuizzo", map);
    getFlowScope().put("teamSetupForm",
        createTeamSetupForm("The Jets",
                "When you're a Jet you're a Jet",
        "Ice", "Action", "Baby John", "Tiger", "Joyboy"));

    flowBuilderContext.registerBean("quizzoFlowManager", quizzoFlowManager);
    MockRequestContext requestContext = new MockRequestContext();
    context.setEventId("continue");
    resumeFlow(context);

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
    form.setTeamMemberNames(teamMembers);
    return form;
  }

  @Override
  protected void configureFlowBuilderContext(MockFlowBuilderContext builderContext) {
    System.out.println("configuring context");
    StubQuizzoFlowManager stubQuizzoFlowManager = new StubQuizzoFlowManager();
    builderContext.registerBean("quizzoFlowManager", stubQuizzoFlowManager);
  }
}
