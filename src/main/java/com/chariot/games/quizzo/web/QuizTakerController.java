package com.chariot.games.quizzo.web;

import com.chariot.games.quizzo.db.QuizRepository;
import com.chariot.games.quizzo.model.Quiz;
import com.chariot.games.quizzo.service.QuizService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/quiztaker")
public class QuizTakerController {
  
  @Autowired
  private QuizService quizService;
  
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public String takeQuiz(@PathVariable(value="id") Long id,
                         Model model) {
    Quiz quiz = quizService.getQuizAndQuestions(id);
    model.addAttribute("quiz", quiz);
    return "quiztaker/index";
  }

}
