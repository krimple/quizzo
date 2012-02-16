package com.chariot.games.quizzo.model.quiz;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooToString
@RooJavaBean
public class ChoiceBuilder {
  
  private Choice choice;
  
  public ChoiceBuilder() {
  }
  
  public ChoiceBuilder booleanChoice(boolean correctValue) {
    choice = new BooleanChoice(correctValue);
    return this;
  }
  
  public ChoiceBuilder text(String text) {
    choice.setAnswerText(text);
    return this;
  }
  
  public Choice asChoice() {
    assert (choice != null);
    return choice;
  }
}
