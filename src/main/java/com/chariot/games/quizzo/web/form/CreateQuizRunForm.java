package com.chariot.games.quizzo.web.form;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJson
public class CreateQuizRunForm {
  private long quizId;
  private String text;
}
