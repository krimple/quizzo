package com.chariot.games.quizzo.web.flow;

import org.springframework.webflow.core.FlowException;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

/**
 * Created by IntelliJ IDEA.
 * User: kenrimple
 * Date: 2/20/12
 * Time: 4:25 PM
 * To change this template use File | Settings | File Templates.
 */
public interface QuizzoFlowManager {
  public Event saveTeam(RequestContext flowRequestContext) throws FlowException;
  public Event debug(RequestContext flowRequestContext) throws FlowException;
}
