package com.chariot.games.quizzo.web.ajax;

import com.chariot.games.quizzo.engine.QuizRunStateMachine;
import com.chariot.games.quizzo.model.Choice;
import com.chariot.games.quizzo.model.Question;
import com.chariot.games.quizzo.model.Quiz;
import com.chariot.games.quizzo.model.QuizRun;
import com.chariot.games.quizzo.service.QuizRunService;
import com.chariot.games.quizzo.service.QuizService;
import com.chariot.games.quizzo.web.QuizWebSessionUtils;
import com.chariot.games.quizzo.web.ajax.dojo.DataStoreUtils;
import com.chariot.games.quizzo.web.ajax.response.QuestionResponse;
import com.chariot.games.quizzo.web.ajax.response.QuizSelectData;
import com.chariot.games.quizzo.web.form.CreateQuizRunForm;
import org.apache.log4j.Logger;
import org.hibernate.cfg.SetSimpleValueTypeSecondPass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

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
  private final QuizWebSessionUtils quizWebSessionUtils = new QuizWebSessionUtils();

  @RequestMapping(value = "quiz", method = RequestMethod.GET)
  public
  @ResponseBody
  String listQuizzes() {
    String jsonArray = QuizSelectData.toJsonArray(QuizSelectData.convert(quizService.findAllQuizes()));
    String payload = DataStoreUtils.asReadStoreForSelect("id", "value", jsonArray);
    logger.trace("outgoing quiz data:");
    logger.trace(payload);
    return payload;
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

  @RequestMapping(value = "quizRun/{id}", method = RequestMethod.GET)
  @ResponseStatus(HttpStatus.OK)
  public @ResponseBody void setQuizRun(HttpSession session, @PathVariable Long id) {
    if (stateMachine.isValidQuizRun(id)) {
      session.setAttribute("currentQuizRunId", id);
      logger.trace("associated current quizrun of " + id);
    } else {
      logger.error("invalid quiz run ID sent. Ignoring.");
    }
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

  @RequestMapping(value = "enroll", method = RequestMethod.PUT)
  @ResponseStatus(HttpStatus.OK)
  public void enrollmentState(HttpSession session) {
    Long quizRunId = quizWebSessionUtils.getQuizRunIdFromSession(session, true);
    stateMachine.enrollTeams(quizRunId);
  }

  @RequestMapping(value = "quizRun", method = RequestMethod.PUT)
  @ResponseStatus(HttpStatus.OK)
  public void startQuizRun(HttpSession session) {
    Long quizRunId = quizWebSessionUtils.getQuizRunIdFromSession(session, true);
    // otherwise, start it!
    stateMachine.startQuizRun(quizRunId);
  }

  @RequestMapping(value = "question", method = RequestMethod.PUT)
  public @ResponseBody String nextQuestion(HttpSession session) {
    long currentQuizRunId = quizWebSessionUtils.getQuizRunIdFromSession(session, true);
    if (!stateMachine.hasMoreQuestions(currentQuizRunId)) {
      return "{ 'nodata' : 'true'}";
    } else {
      return convertQuestionPayload(stateMachine.getCurrentQuestion(currentQuizRunId));
    }
  }

  @RequestMapping(value = "quizRun/{id}", method = RequestMethod.DELETE)
  @ResponseStatus(HttpStatus.OK)
  public void endQuizRun(@PathVariable("id") long quizRunId,
                           HttpSession session) {
    long currentQuizRunId = quizWebSessionUtils.getQuizRunIdFromSession(session, true);
    stateMachine.endQuizRun(quizRunId);
  }

  private String convertQuestionPayload(Question currentQuestion) {
    QuestionResponse response = new QuestionResponse(
        currentQuestion.getSortOrder(),
        currentQuestion.getQuestionText()
    );
    List<Choice> choices = new ArrayList<Choice>(currentQuestion.getChoices());
    // overkill - probably should @OrderBy it
    Collections.sort(choices);
    Iterator<Choice> iterator = choices.iterator();
    while (iterator.hasNext()) {
      Choice choice = iterator.next();
      response.addChoiceResponse(
          choice.getSortOrder(),
          choice.getCorrect(),
          choice.getText(),
          choice.getId()
          );
    }

    // now serialize that mutha.
    return response.toJson();
  }

  @ExceptionHandler(value = IllegalStateException.class)
  @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
  public void handleError(IllegalStateException e) {
    logger.error("An illegal state exception has occurred");
    logger.error(e);
  }

}
