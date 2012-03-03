package com.chariot.games.quizzo.web.ajax.response;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

import java.util.List;

@RooJavaBean
@RooJson
@RooToString
public class QuestionChoiceResponse {
  public QuestionChoiceResponse() {
    super();
  }

  public QuestionChoiceResponse(short order, boolean isCorrect, String text, long choiceId) {
    this.order = order;
    this.isCorrect = isCorrect;
    this.text = text;
    this.choiceId = choiceId;
  }

  private boolean isCorrect;
  private String text;
  private long choiceId;
  private short order;
}
