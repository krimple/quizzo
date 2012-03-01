package com.chariot.games.quizzo.web;

import com.chariot.games.quizzo.model.QuestionWithChoices;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/questionwithchoices")
@Controller
@RooWebScaffold(path = "questionwithchoices", formBackingObject = QuestionWithChoices.class)
public class QuestionWithChoicesController {
}
