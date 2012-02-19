package com.chariot.games.quizzo.web;

import com.chariot.games.quizzo.model.QuizRun;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin/quizruns")
@Controller
@RooWebScaffold(path = "admin/quizruns", formBackingObject = QuizRun.class)
public class QuizRunController {
}
