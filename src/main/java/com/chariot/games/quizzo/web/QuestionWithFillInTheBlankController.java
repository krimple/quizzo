package com.chariot.games.quizzo.web;

import com.chariot.games.quizzo.model.QuestionWithFillInTheBlankAnswers;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/questionwithfillintheblankanswers")
@Controller
@RooWebScaffold(path = "questionwithfillintheblankanswers", formBackingObject = QuestionWithFillInTheBlankAnswers.class)
public class QuestionWithFillInTheBlankController {
}
