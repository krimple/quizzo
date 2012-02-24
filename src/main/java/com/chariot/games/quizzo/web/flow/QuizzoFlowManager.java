package com.chariot.games.quizzo.web.flow;

import org.springframework.webflow.core.FlowException;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

public interface QuizzoFlowManager {

  public Event saveTeamData(RequestContext flowRequestContext) throws FlowException;
  public Event debug(RequestContext flowRequestContext) throws FlowException;
  public void loadTeamStats(RequestContext flowRequestContext) throws FlowException;
  public void acceptAnswer(RequestContext flowRequestContext) throws FlowException;
  public void setupQuestionAndChoices(RequestContext flowRequestContext) throws FlowException;

}
