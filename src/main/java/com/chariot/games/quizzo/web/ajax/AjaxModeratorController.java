package com.chariot.games.quizzo.web.ajax;

import com.chariot.games.quizzo.engine.QuizRunStateMachine;
import com.chariot.games.quizzo.model.Quiz;
import com.chariot.games.quizzo.model.QuizRun;
import com.chariot.games.quizzo.model.Team;
import com.chariot.games.quizzo.service.QuizRunService;
import com.chariot.games.quizzo.service.QuizService;
import com.chariot.games.quizzo.service.TeamService;
import com.chariot.games.quizzo.web.ajax.dojo.DataStoreUtils;
import com.chariot.games.quizzo.web.ajax.request.NewTeamRequest;
import com.chariot.games.quizzo.web.form.CreateQuizRunForm;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin/moderator_ajax/**")
public class AjaxModeratorController {

  private static final Logger logger = Logger.getLogger(AjaxModeratorController.class);
  @Autowired
  private QuizService quizService;

  @Autowired
  private QuizRunService quizRunService;

  @Autowired
  private QuizRunStateMachine stateMachine;

  @RequestMapping(value = "quiz", method = RequestMethod.GET)
  public
  @ResponseBody
  String listQuizzes() {
    String jsonArray =
        Quiz.toJsonArray(quizService.findAllQuizes());
    return DataStoreUtils.asReadStoreForSelect("id", "title", jsonArray);
  }

  @RequestMapping(value = "quiz", method = RequestMethod.POST)
  @ResponseStatus(HttpStatus.OK)
  public void createQuiz(@RequestBody String quizJson) {
    logger.trace("incoming Json request for quiz POST");
    logger.trace(quizJson);
    Quiz quiz = Quiz.fromJsonToQuiz(quizJson);
    quizService.saveQuiz(quiz);
  }

  @RequestMapping(value = "quizRun", method = RequestMethod.GET)
  public
  @ResponseBody
  String listQuizRuns() {
    String jsonArray = QuizRun.toJsonArray(quizRunService.findAllQuizRuns());
    return DataStoreUtils.asReadStoreForSelect("id", "text", jsonArray);
  }

  @RequestMapping(value = "quizRun", method = RequestMethod.POST)
  @ResponseStatus(HttpStatus.OK)
  public void createQuizRun(@RequestBody String quizRunFormJson, HttpSession session) {
    logger.trace("incoming Json request for quizRun FORM POST");
    logger.trace(quizRunFormJson);
    CreateQuizRunForm quizRunForm = CreateQuizRunForm.fromJsonToCreateQuizRunForm(quizRunFormJson);
    long quizRunId = stateMachine.createQuizRun(quizRunForm.getQuizId(), quizRunForm.getText());
    session.setAttribute("currentQuizRunId", quizRunId);
    logger.trace("quiz run created : " + quizRunId + ".");
  }


  @RequestMapping(value = "quizRun", method = RequestMethod.PUT)
  @ResponseStatus(HttpStatus.OK)
  public @ResponseBody void startQuizRun(HttpSession session) {
    Long quizRunId = getQuizRunIdFromSession(session, true);
    // otherwise, start it!
    stateMachine.startQuizRun(quizRunId);
  }

  private Long getQuizRunIdFromSession(HttpSession session, boolean required) {
    Long quizRunId = (Long)session.getAttribute("currentQuizRunId");
    if (required && quizRunId == null) {
      throw new IllegalStateException("Quiz Run not created. Run /admin/moderator_ajax/quizRun as a POST to create one.");
    }
    return quizRunId;
  }

  @RequestMapping(value = "question", method = RequestMethod.PUT)
  public @ResponseBody String nextQuestion(HttpSession session) {
    long currentQuizRunId = getQuizRunIdFromSession(session, true);
    //TODO - send Json form with details that include question or no, etc...answers
    if (!stateMachine.hasMoreQuestions(currentQuizRunId)) {
      return "NO QUESTION";
    } else {
      return "TODO";
    }

  }

  @RequestMapping(value = "quizRun/{id}", method = RequestMethod.DELETE)
  @ResponseStatus(HttpStatus.OK)
  public void endQuizRun(@PathVariable("id") long quizRunId,
                           HttpSession session) {
    long currentQuizRunId = getQuizRunIdFromSession(session, true);
    stateMachine.endQuizRun(quizRunId);
  }

}
