package com.chariot.games.quizzo.web.ajax.response;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

import java.util.ArrayList;
import java.util.List;

@RooJavaBean
@RooToString
@RooJson(deepSerialize = true)
public class QuestionResponse {

  private int questionNumber;
  private String text;

  public QuestionResponse() {
    super();
  }

  public QuestionResponse(int questionNumber, String text) {
    this.questionNumber = questionNumber;
    this.text = text;
  }

  private List<QuestionChoiceResponse> responses =
      new ArrayList<QuestionChoiceResponse>();

  public void addChoiceResponse(short order, boolean isCorrect, String text, long choiceId) {
    responses.add(new QuestionChoiceResponse(order, isCorrect, text, choiceId));
  }
}
