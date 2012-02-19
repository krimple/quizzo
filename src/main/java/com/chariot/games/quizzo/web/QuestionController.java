package com.chariot.games.quizzo.web;

import com.chariot.games.quizzo.model.Question;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin/questions")
@Controller
@RooWebScaffold(path = "admin/questions", formBackingObject = Question.class)
public class QuestionController {
}
