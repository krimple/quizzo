package com.chariot.games.quizzo.web.play;

import com.chariot.games.quizzo.engine.QuizRunStateMachine;
import com.chariot.games.quizzo.model.Choice;
import com.chariot.games.quizzo.model.Question;
import com.chariot.games.quizzo.web.QuizWebSessionUtils;
import com.chariot.games.quizzo.web.ajax.request.NewTeamRequest;
import com.chariot.games.quizzo.web.ajax.request.VotingForm;
import com.chariot.games.quizzo.web.ajax.response.CurrentScoreResponse;
import com.chariot.games.quizzo.web.ajax.response.QuizRunSelectData;
import com.chariot.games.quizzo.web.form.ChoiceForm;
import com.chariot.games.quizzo.web.form.QuestionAndChoicesForm;
import com.chariot.games.quizzo.web.ajax.dojo.DataStoreUtils;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.*;

@RequestMapping("/quizzo/**")
@Controller
public class GamePlayController {

  private static final Logger logger = Logger.getLogger(GamePlayController.class);

  @Autowired
  private QuizRunStateMachine stateMachine;

  /**
   * Render the player panels - first register, then use dojo app to play game
   */
  @RequestMapping(method = RequestMethod.GET)
  public String index(HttpSession session, ModelMap model) {
    Long quizRunId = QuizWebSessionUtils.getQuizRunIdFromSession(session, false);

    return "quizzo/index";
  }

  @RequestMapping(value = "status")
  public
  @ResponseBody
  String getGameStatus(HttpSession session) {
    Long quizRunId = QuizWebSessionUtils.getQuizRunIdFromSession(session, false);
    if (quizRunId == null) {
      return "NOT_STARTED";
    } else {
      return stateMachine.getQuizRunState(quizRunId).toString();
    }
  }

  @RequestMapping(value = "question", method = RequestMethod.GET)
  public
  @ResponseBody
  String getCurrentQuestion(HttpSession session) {
    Long quizRunId = QuizWebSessionUtils.getQuizRunIdFromSession(session, true);
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
  public
  @ResponseBody
  String registerTeamForQuizRun(
      HttpSession session,
      @RequestBody String teamJson) {
    logger.trace("incoming Json request for team POST");
    logger.trace(teamJson);

    NewTeamRequest newTeamRequest = NewTeamRequest.fromJsonToNewTeamRequest(teamJson);
    // associate the user session with the quiz run
    QuizWebSessionUtils.setQuizRunIdForSession(session, newTeamRequest.getQuizRunId());
    // now, create and register the team
    Long teamId = stateMachine.registerTeam(newTeamRequest.getQuizRunId(),
        newTeamRequest.getTeamName(), newTeamRequest.getMission());

    // now, store team in session as well
    // so long, spoofers (ok, at least it's tougher for them)
    session.setAttribute("currentTeamId", teamId);
    return String.valueOf(teamId);
  }

  @Transactional
  @ResponseStatus(HttpStatus.OK)
  public boolean submitAnswer(HttpSession session, @RequestBody String votingFormJson) {
    VotingForm votingForm = VotingForm.fromJsonToVotingForm(votingFormJson);
    Long quizRunId = QuizWebSessionUtils.getQuizRunIdFromSession(session, true);
    Long teamId = QuizWebSessionUtils.getCurrentTeamIdFromSession(session, true);
    Long choiceId = votingForm.getChoiceId();
    stateMachine.submitAnswer(quizRunId, teamId, choiceId);
    return true;
  }

  @RequestMapping(value = "scores", method = RequestMethod.GET)
  public
  @ResponseBody
  String getScores(HttpSession session) {
    Long quizRunId = QuizWebSessionUtils.getQuizRunIdFromSession(session, true);
    //Long teamId = (Long) session.getAttribute("currentTeamId");
    Map<String, BigDecimal> scores = stateMachine.getScores(quizRunId);
    //TODO should sort by name - my Java collections memory is getting a tad fuzzy this week - verify in test
    SortedSet<String> keys = new TreeSet(scores.keySet());
    List<CurrentScoreResponse> responses = new ArrayList<CurrentScoreResponse>();
    Iterator<String> keyIterator = keys.iterator();
    while (keyIterator.hasNext()) {
      CurrentScoreResponse response = new CurrentScoreResponse();
      String team = keyIterator.next();
      response.setTeam(team);
      BigDecimal score = scores.get(team);
      // TODO - maybe make our points BigInteger anyway
      response.setValue(score.toBigInteger().toString());
      responses.add(response);
    }
    return CurrentScoreResponse.toJsonArray(responses);
  }

  @RequestMapping(value = "quizrun", method = RequestMethod.GET)
  public
  @ResponseBody
  String listQuizRuns() {
    String jsonArray =
        QuizRunSelectData.toJsonArray(QuizRunSelectData.convert(stateMachine.getAllReadyQuizRuns()));
    return DataStoreUtils.asReadStoreForSelect("id", "label", jsonArray);
  }

//  @RequestMapping(value = "teamSetup")
//  public String teamSetup() {
//    return "quizzo/_teamSetup";
//  }
//
//  @RequestMapping(value = "poller")
//  public String poller() {
//    return "quizzo/_poller";
//  }
//
//  @RequestMapping(value = "question")
//  public String question() {
//    return "quizzo/_question";
//  }
//
//  @RequestMapping(value = "review")
//  public String review() {
//    return "quizzo/_review";
//  }
//
//  @RequestMapping(value = "gameover")
//  public String gameover() {
//    return "quizzo/_gameover";
//  }
}
