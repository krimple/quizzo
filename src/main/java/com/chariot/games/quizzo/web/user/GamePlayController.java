package com.chariot.games.quizzo.web.user;

import com.chariot.games.quizzo.engine.QuizRunStateMachine;
import com.chariot.games.quizzo.model.Choice;
import com.chariot.games.quizzo.model.Question;
import com.chariot.games.quizzo.web.ajax.request.NewTeamRequest;
import com.chariot.games.quizzo.web.ajax.request.VotingForm;
import com.chariot.games.quizzo.web.form.ChoiceForm;
import com.chariot.games.quizzo.web.form.QuestionAndChoicesForm;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Map;

@RequestMapping("/game/**")
@Controller
public class GamePlayController {

  private static final Logger logger = Logger.getLogger(GamePlayController.class);

  @Autowired
  private QuizRunStateMachine stateMachine;


  /**
   * Render the player panels
   */
  @RequestMapping(method = RequestMethod.GET)
  public String index() {
    return "game/index";
  }


  @RequestMapping(value = "question", method = RequestMethod.GET)
  public
  @ResponseBody
  String getCurrentQuestion(HttpSession session) {
    Long quizRunId = getCurrentQuizRunForSession(session);
    Question currentQuestion = stateMachine.getCurrentQuestion(quizRunId);
    Long currentQuestionId = currentQuestion.getId();
    logger.trace("current question id fetched: " + currentQuestionId);
    logger.trace("question fetched from database.");

    // TODO - this smells like seepage. Sewage. ugh.
    QuestionAndChoicesForm form = new QuestionAndChoicesForm();
    form.setQuestionId(currentQuestionId);
    form.setQuestionText(currentQuestion.getQuestionText());
    for (Choice c : currentQuestion.getChoices()) {
      ChoiceForm choiceForm = new ChoiceForm();
      choiceForm.setChecked(false);
      choiceForm.setChoiceText(c.getText());
      form.getChoices().add(choiceForm);
    }

    // TODO - review whether we can use this some way else in Dojo
    return form.toJson();
  }

  @RequestMapping(value = "team", method = RequestMethod.POST)
  @ResponseStatus(HttpStatus.OK)
  public void registerTeamForQuizRun(
      HttpSession session,
      @RequestBody String teamJson) {
    logger.trace("incoming Json request for team POST");
    logger.trace(teamJson);
    NewTeamRequest newTeamRequest = NewTeamRequest.fromJsonToNewTeamRequest(teamJson);
    session.getAttribute("currentQuizRunId");
    Long quizRunId = getCurrentQuizRunForSession(session);
    Long teamId = stateMachine.registerTeam(quizRunId, newTeamRequest.getTeamName(), newTeamRequest.getMission());

    // now, store team in session as well
    // so long, spoofers (ok, at least it's tougher for them)
    session.setAttribute("currentTeamId", teamId);
  }

  @Transactional
  @ResponseStatus(HttpStatus.OK)
  public boolean submitAnswer(HttpSession session, @RequestBody String votingFormJson) {
    VotingForm votingForm = VotingForm.fromJsonToVotingForm(votingFormJson);
    Long quizRunId = getCurrentQuizRunForSession(session);
    Long teamId = (Long) session.getAttribute("currentTeamId");
    Long choiceId = votingForm.getChoiceId();
    stateMachine.submitAnswer(quizRunId, teamId, choiceId);
    return true;
  }

   // TODO - convert to JSON object result
  @RequestMapping(value = "scores", method = RequestMethod.GET)
  public @ResponseBody String getScores(HttpSession session) {
    Long teamId = (Long) session.getAttribute("currentTeamId");
    Map<String, BigDecimal> scores = stateMachine.getScores(teamId);
    return scores.toString();
  }

  private Long getCurrentQuizRunForSession(HttpSession session) {
    Long currentQuizRun = (Long) session.getAttribute("currentQuizRunId");
    if (currentQuizRun == null)
      throw new IllegalStateException("No current quiz run exists. Please attach to a quiz run.");
    return currentQuizRun;
  }
}
