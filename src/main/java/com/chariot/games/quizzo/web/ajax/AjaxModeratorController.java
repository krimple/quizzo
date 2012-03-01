package com.chariot.games.quizzo.web.ajax;

import com.chariot.games.quizzo.model.Quiz;
import com.chariot.games.quizzo.model.QuizRun;
import com.chariot.games.quizzo.model.Team;
import com.chariot.games.quizzo.service.QuizRunService;
import com.chariot.games.quizzo.service.QuizService;
import com.chariot.games.quizzo.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * User: kenrimple
 * Date: 2/28/12
 * Time: 6:40 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/admin/moderator_ajax/**")
public class AjaxModeratorController {

  @Autowired
  QuizService quizService;

  @Autowired
  QuizRunService quizRunService;

  @Autowired
  TeamService teamService;


  @RequestMapping(value = "quiz", method = RequestMethod.GET)
  public @ResponseBody String listQuizzes() {
    return Quiz.toJsonArray(quizService.findAllQuizes());
  }

  @RequestMapping(value = "quizRun", method=RequestMethod.GET)
  public @ResponseBody String listQuizRuns() {
    return QuizRun.toJsonArray(quizRunService.findAllQuizRuns());
  }

  @RequestMapping(value = "quiz", method = RequestMethod.POST)
  @ResponseStatus(HttpStatus.OK)
  public void createQuiz(@RequestBody String quizJson) {
    Quiz quiz = Quiz.fromJsonToQuiz(quizJson);
    quizService.saveQuiz(quiz);
  }

  @RequestMapping(value = "team", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void createTeam(@RequestBody String teamJson) {
      Team team = Team.fromJsonToTeam(teamJson);
      teamService.saveTeam(team);
    }
}
