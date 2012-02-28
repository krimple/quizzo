package com.chariot.games.quizzo.web.ajax;

import com.chariot.games.quizzo.model.Quiz;
import com.chariot.games.quizzo.service.QuizService;
import flexjson.JSONSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 * User: kenrimple
 * Date: 2/28/12
 * Time: 6:40 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/admin/moderator_ajax/**")
public class AjaxModeratorController {

  @Autowired
  QuizService quizService;

  @RequestMapping("quiz")
  public @ResponseBody String listQuizzes() {
    return Quiz.toJsonArray(quizService.findAllQuizes());
  }

}
