package com.chariot.games.quizzo.web.flow;

import com.chariot.games.quizzo.web.form.TeamSetupForm;
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

import static org.mockito.Mockito.mock;

public class QuizzoFlowPollingTest extends AbstractXmlFlowExecutionTests {

  @Override
  protected FlowDefinitionResource getResource(FlowDefinitionResourceFactory resourceFactory) {
    return resourceFactory.
        createFileResource("src/main/webapp/WEB-INF/views/playQuizzo/flow.xml");
  }

  @Test
  public void testPoll() {

    setCurrentState("register-team-members");
    QuizzoFlowManager quizzoFlowManager = mock(QuizzoFlowManager.class);
    MockExternalContext context = new MockExternalContext();

    MutableAttributeMap map = new LocalAttributeMap();

    // elaborate hack - can't use mocks with different values in the base
    // webflow test, so we create a stub, and expose a setter to change the
    // test condition.  Not safe for multi-threading.

    getFlowScope().put("teamSetupForm",
        createTeamSetupForm("The Jets",
                "When you're a Jet you're a Jet",
        "Ice", "Action", "Baby John", "Tiger", "Joyboy"));

    MockRequestContext requestContext = new MockRequestContext();

    context.setEventId("continue");
    StubQuizzoFlowManager manager = (StubQuizzoFlowManager)
            getFlow().getApplicationContext().getBean("quizzoFlowManager");
    manager.setPollValue("poll");

    resumeFlow(context);
    assertCurrentStateEquals("poll");


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
    stubQuizzoFlowManager.setPollValue("poll");
    builderContext.registerBean("quizzoFlowManager", stubQuizzoFlowManager);
  }
}
