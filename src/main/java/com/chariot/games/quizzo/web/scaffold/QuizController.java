package com.chariot.games.quizzo.web.scaffold;

import com.chariot.games.quizzo.model.Quiz;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/quizes")
@Controller
@RooWebScaffold(path = "quizes", formBackingObject = Quiz.class)
public class QuizController {
}
