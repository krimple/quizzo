package com.chariot.games.quizzo.web.scaffold;

import com.chariot.games.quizzo.model.Question;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/questions")
@Controller
@RooWebScaffold(path = "questions", formBackingObject = Question.class)
public class QuestionController {
}
