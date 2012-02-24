package com.chariot.games.quizzo.web.flow;

import com.chariot.games.quizzo.engine.QuizRunStateMachine;
import com.chariot.games.quizzo.model.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.webflow.action.MultiAction;
import org.springframework.webflow.core.FlowException;
import org.springframework.webflow.core.collection.MutableAttributeMap;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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
  public Event saveTeamData(RequestContext flowRequestContext) throws FlowException {
    TeamSetupForm teamSetupForm = (TeamSetupForm)flowRequestContext.getViewScope().get("teamSetupForm");

    Team team = new Team();
    team.setName(teamSetupForm.getName());
    team.setMission(teamSetupForm.getMessage());
    Iterator<String> memberNamesIterator = teamSetupForm.getTeamMemberNames().iterator();
    while (memberNamesIterator.hasNext()) {
      String teamMemberName = memberNamesIterator.next();
      TeamMember member = new TeamMember();
      member.setName(teamMemberName);
      team.getTeamMembers().add(member);
    }

    team.setQuizRun(stateMachine.getQuizRun());
    team.persist();
    flowRequestContext.getFlowScope().put("team", team);
    return success();
  }

  @Override
  public Event debug(RequestContext flowRequestContext) throws FlowException {
    logger.debug(flowRequestContext);
    return success();
  }

  @Override
  public void loadTeamStats(RequestContext flowRequestContext) throws FlowException {
    Team team = (Team) flowRequestContext.getFlowScope().get("team");
    BigDecimal score = team.calculateTotalScore();
    flowRequestContext.getRequestScope().put("score", score);
  }

  public void setupQuestionAndChoices(RequestContext flowRequestContext) throws FlowException {
    MutableAttributeMap viewScope = flowRequestContext.getViewScope();
    Long questionId = stateMachine.getCurrentQuestionId();
    Question question = Question.findQuestion(questionId);
    viewScope.put("question", question);
    Map<Long, Boolean> answers = new HashMap<Long, Boolean>();
    viewScope.put("answers", answers);
  }

  public void acceptAnswer(RequestContext flowRequestContext)  throws FlowException {
    MutableAttributeMap viewScope = flowRequestContext.getViewScope();
    Long answerId = Long.valueOf(flowRequestContext.getExternalContext().getRequestParameterMap().get("answerId"));
    Boolean value = Boolean.valueOf(flowRequestContext.getExternalContext().getRequestParameterMap().get("value"));
    //TODO - perhaps delegate all answer acceptance polymorphically by question type
    //TODO - also consider maybe not a map but a set of answers...
    Map<Long, Boolean> answers = (Map<Long, Boolean>)viewScope.get("answers");

    if (value.equals(Boolean.TRUE)) {
      answers.put(answerId, value);
    } else {
      answers.remove(answerId);
    }
  }

  public void finalizeAnswers(RequestContext flowRequestContext)  throws FlowException {
    MutableAttributeMap viewScope = flowRequestContext.getViewScope();
    //TODO - perhaps delegate all finalization by answer type
    Answer answer = new Answer();
    Question question = (Question) viewScope.get("question");
    answer.setQuestion(question);
    Map<Long, Boolean> answers = (Map<Long, Boolean>)viewScope.get("answers");
    for (Long choiceId : answers.keySet()) {
      Choice choice = Choice.findChoice(choiceId);
      answer.getChoices().add(choice);
    }
    answer.persist();
  }
}
