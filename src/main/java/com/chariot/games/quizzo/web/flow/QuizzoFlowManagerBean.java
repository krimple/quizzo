package com.chariot.games.quizzo.web.flow;

import org.springframework.stereotype.Component;

@Component("quizzoFlowManager")
public class QuizzoFlowManagerBean /* extends MultiAction implements QuizzoFlowManager */{
/*
  private final static Logger logger = Logger.getLogger(QuizzoFlowManagerBean.class);

  @Autowired
  private TeamService teamService;

  @Autowired
  private QuestionService questionService;

  @Autowired
  private ChoiceService choiceService;

  @Autowired
  private QuizRunStateMachine stateMachine;

  @Autowired
  private AnswerService answerService;

  @Override
  public Event saveTeamData(RequestContext flowRequestContext) throws FlowException {
    TeamSetupForm teamSetupForm = (TeamSetupForm)flowRequestContext.getFlowScope().get("teamSetupForm");

    Team team = new Team();
    team.setName(teamSetupForm.getName());
    team.setMission(teamSetupForm.getMessage());
    team.setQuizRun(stateMachine.getQuizRun());
    teamService.saveTeam(team);
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

  @Override
  public Event pollReady(RequestContext flowRequestContext) throws FlowException {
    return new Event(this, "yes");
  }

  @Override
  public void setupQuestionAndChoices(RequestContext flowRequestContext) throws FlowException {
    MutableAttributeMap viewScope = flowRequestContext.getViewScope();
    Long questionId = stateMachine.getCurrentQuestionId();
    Question question = questionService.findQuestion(questionId);
    viewScope.put("question", question);
    Map<Long, Boolean> answers = new HashMap<Long, Boolean>();
    viewScope.put("answers", answers);
  }

  @Override
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

  @Override
  public void finalizeAnswers(RequestContext flowRequestContext)  throws FlowException {
    MutableAttributeMap viewScope = flowRequestContext.getViewScope();
    //TODO - perhaps delegate all finalization by answer type
//    Answer answer = new Answer();
//    Question question = (Question) viewScope.get("question");
//    answer.setQuestion(question);
//    Map<Long, Boolean> answers = (Map<Long, Boolean>)viewScope.get("answers");
//    for (Long choiceId : answers.keySet()) {
//      Choice choice = choiceService.findChoice(choiceId);
//      answer.getChoices().add(choice);
//    }
//    answerByChoiceService.saveAnswer(answer);
  }
  */
}
