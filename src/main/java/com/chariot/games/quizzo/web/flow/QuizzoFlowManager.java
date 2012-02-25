package com.chariot.games.quizzo.web.flow;

import org.springframework.webflow.core.FlowException;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

public interface QuizzoFlowManager {

  Event saveTeamData(RequestContext flowRequestContext) throws FlowException;
  Event debug(RequestContext flowRequestContext) throws FlowException;
  void loadTeamStats(RequestContext flowRequestContext) throws FlowException;
  void acceptAnswer(RequestContext flowRequestContext) throws FlowException;
  void setupQuestionAndChoices(RequestContext flowRequestContext) throws FlowException;
  Event pollReady(RequestContext flowRequestContext) throws FlowException;
  void finalizeAnswers(RequestContext flowRequestContext)  throws FlowException;
}
