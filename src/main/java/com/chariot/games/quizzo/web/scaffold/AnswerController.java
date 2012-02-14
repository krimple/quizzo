package com.chariot.games.quizzo.web.scaffold;

import com.chariot.games.quizzo.model.Answer;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/answers")
@Controller
@RooWebScaffold(path = "answers", formBackingObject = Answer.class)
public class AnswerController {
}
