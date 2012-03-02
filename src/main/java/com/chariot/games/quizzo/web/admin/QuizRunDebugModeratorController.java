package com.chariot.games.quizzo.web.admin;

import com.chariot.games.quizzo.engine.QuizRunStateMachine;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@SessionAttributes("quizSelectionForm")
@RequestMapping("/admin/moderator/**")
@Controller
public class QuizRunDebugModeratorController {

  // todo - get this the hell out of here!
  @RequestMapping
  public String index(@ModelAttribute("quizSelectionForm") QuizSelectionForm form, Model model) {
    return "admin/moderator/index";
  }

  private static final Logger logger = Logger.getLogger(QuizRunDebugModeratorController.class);

  @Autowired
  QuizRunStateMachine stateMachine;


}
