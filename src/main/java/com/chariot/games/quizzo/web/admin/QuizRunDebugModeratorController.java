package com.chariot.games.quizzo.web.admin;

import com.chariot.games.quizzo.engine.QuizRunStateMachine;
import com.chariot.games.quizzo.model.Quiz;
import com.chariot.games.quizzo.model.QuizRun;
import com.chariot.games.quizzo.model.Team;
import com.chariot.games.quizzo.model.TeamMember;
import com.chariot.games.quizzo.service.QuizRunService;
import com.chariot.games.quizzo.service.QuizService;
import com.chariot.games.quizzo.service.TeamService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequestMapping("/admin/moderator/**")
@Controller
public class QuizRunDebugModeratorController {

  @Autowired
  private QuizService quizService;

  @ModelAttribute(value = "quizzes")
  public List<Quiz> getAllQuizzes() {
    return quizService.findAllQuizes();
  }

  private static final Logger logger = Logger.getLogger(QuizRunDebugModeratorController.class);

  @Autowired
  QuizRunStateMachine stateMachine;

  @Autowired
  TeamService teamService;

  @Autowired
  QuizRunService quizRunService;

  @RequestMapping(value = "initialize", method = RequestMethod.GET)
  public String createQuizRun(@RequestParam(value = "id", required = true) long quizId, Model model) {
    logger.debug("initializing quiz with id of " + quizId);
    stateMachine.initializeQuiz(quizId, "Debugger created...");
    model.asMap().put("statusMessage", "OK, quiz run initialized.");
    logger.debug("Quiz run intitialized.");
    logger.debug("status = " + stateMachine.getQuizRunState().toString());
    return "admin/moderator/index";
  }

  @RequestMapping(value = "enroll", method = RequestMethod.GET)
  public String enroll(Model model) {
    setupTeams(stateMachine.getQuizRun().getId());
    model.asMap().put("statusMessage", "teams generated.");
    return "admin/moderator/index";
  }

  @RequestMapping(value = "start", method = RequestMethod.GET)
  public String startQuiz(Model model) {
    stateMachine.startQuiz();
    model.asMap().put("statusMessage", "OK, quiz started.");
    return "admin/moderator/index";
  }

  @RequestMapping(value = "next", method = RequestMethod.GET)
  public String nextQuestion(Model model) {
    stateMachine.startQuiz();
    Long questionId = stateMachine.getCurrentQuestionId();
    model.asMap().put("statusMessage", "Next question triggered, id is " + questionId);
    return "admin/moderator/index";
  }

  @RequestMapping(value = "end", method = RequestMethod.GET)
  public String endQuiz(Model model) {
    stateMachine.endQuiz();
    model.asMap().put("statusMessage",  "quiz complete");
    return "admin/moderator/index";
  }

  @RequestMapping(value = "status", method = RequestMethod.GET)
  public String getRunState(Model model) {
    String state = stateMachine.getQuizRunState().toString();
    model.asMap().put("statusMessage", "current run state of quiz is " + state);
    return "admin/moderator/index";
  }

  @RequestMapping(value = "scores", method = RequestMethod.GET)
  public String getScores(Model model) {
    Map<String, BigDecimal> scores = stateMachine.getScores();
    model.asMap().put("statusMessage", scores.toString());
    return "admin/moderator/index";
  }

  @RequestMapping
  public String index() {
    return "admin/moderator/index";
  }

  private void setupTeams(Long quizRunId) {
    QuizRun quizRun = quizRunService.findQuizRun(quizRunId);
    Team team1 = new Team();
    team1.setName("The wingnuts");
    team1.setMission("to be wingnuts.");
    TeamMember tm1 = new TeamMember();
    tm1.setName("Joey");
    TeamMember tm2 = new TeamMember();
    tm2.setName("Freddie");
    List<TeamMember> teamMemberList1 = new ArrayList<TeamMember>();
    teamMemberList1.add(tm1);
    teamMemberList1.add(tm2);
    team1.setTeamMembers(teamMemberList1);
    team1.setQuizRun(quizRun);
    teamService.saveTeam(team1);

    Team team2 = new Team();
    team2.setName("The bolts");
    team2.setMission("to be bolted.");
    tm1 = new TeamMember();
    tm1.setName("Artie");
    tm2 = new TeamMember();
    tm2.setName("Ralph");
    List<TeamMember> teamMemberList2 = new ArrayList<TeamMember>();
    teamMemberList2.add(tm1);
    teamMemberList2.add(tm2);
    team2.setTeamMembers(teamMemberList2);
    team2.setQuizRun(quizRun);
    teamService.saveTeam(team2);
  }
}
