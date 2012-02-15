package com.chariot.games.quizzo.web.scaffold;

import com.chariot.games.quizzo.model.TrueFalseQuestion;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/truefalsequestions")
@Controller
@RooWebScaffold(path = "truefalsequestions", formBackingObject = TrueFalseQuestion.class)
public class TrueFalseQuestionController {
}
