package com.chariot.games.quizzo.web;

import com.chariot.games.quizzo.model.Quiz;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin/quizzes")
@Controller
@RooWebScaffold(path = "admin/quizzes", formBackingObject = Quiz.class)
public class QuizController {
}
