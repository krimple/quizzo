package com.chariot.games.quizzo.web.flow;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.webflow.action.MultiAction;
import org.springframework.webflow.core.FlowException;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

import com.chariot.games.quizzo.engine.QuizRunStateMachine;
import com.chariot.games.quizzo.model.Team;

/**
 * Created by IntelliJ IDEA.
 * User: kenrimple
 * Date: 2/20/12
 * Time: 4:27 PM
 * To change this template use File | Settings | File Templates.
 */
@Component("quizzoFlowManager")
public class QuizzoFlowManagerBean extends MultiAction implements QuizzoFlowManager {

  private final static Logger logger = Logger.getLogger(QuizzoFlowManagerBean.class);

  @Autowired
  private QuizRunStateMachine stateMachine;
	
  @Override
  public Event saveTeam(RequestContext flowRequestContext) throws FlowException {
    Team team = (Team)flowRequestContext.getViewScope().get("team");
    team.setQuizRun(stateMachine.getQuizRun());
    team.setMission("wow man");
    team.persist();
    return success();
  }

  @Override
  public Event debug(RequestContext flowRequestContext) throws FlowException {
    logger.debug(flowRequestContext);
    return success();
  }
}
