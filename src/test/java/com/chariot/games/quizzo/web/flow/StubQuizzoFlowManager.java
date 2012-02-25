package com.chariot.games.quizzo.web.flow;

import org.springframework.webflow.action.MultiAction;
import org.springframework.webflow.core.FlowException;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

public class StubQuizzoFlowManager extends MultiAction implements QuizzoFlowManager {

  public StubQuizzoFlowManager() {
    System.err.println("Creating stub...");
  }
  private String pollValue = "yes";

  public void setPollValue(String pollValue) {
    this.pollValue = pollValue;
  }

  @Override
  public Event saveTeamData(RequestContext flowRequestContext) throws FlowException {
    return success();
  }

  @Override
  public Event debug(RequestContext flowRequestContext) throws FlowException {
    return success();
  }

  @Override
  public void loadTeamStats(RequestContext flowRequestContext) throws FlowException {

  }

  @Override
  public void acceptAnswer(RequestContext flowRequestContext) throws FlowException {

  }

  @Override
  public void setupQuestionAndChoices(RequestContext flowRequestContext) throws FlowException {

  }

  @Override
  public Event pollReady(RequestContext flowRequestContext) throws FlowException {
    System.err.println("returning " + pollValue + " from pollReady.");
    return new Event(this, pollValue);
  }

  @Override
  public void finalizeAnswers(RequestContext flowRequestContext) throws FlowException {
    //To change body of implemented methods use File | Settings | File Templates.
  }
}
