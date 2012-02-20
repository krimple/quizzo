package com.chariot.games.quizzo.web.flow;

import com.chariot.games.quizzo.model.Team;
import org.springframework.stereotype.Component;
import org.springframework.webflow.action.MultiAction;
import org.springframework.webflow.core.FlowException;
import org.springframework.webflow.execution.Action;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

/**
 * Created by IntelliJ IDEA.
 * User: kenrimple
 * Date: 2/20/12
 * Time: 4:27 PM
 * To change this template use File | Settings | File Templates.
 */
@Component("quizzoFlowManager")
public class QuizzoFlowManagerBean extends MultiAction implements QuizzoFlowManager {
  @Override
  public Event saveTeam(RequestContext flowRequestContext) throws FlowException {
    Team team = (Team)flowRequestContext.getViewScope().get("team");
    team.persist();
    return success();
  }
}
