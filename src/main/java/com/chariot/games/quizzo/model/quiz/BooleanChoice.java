package com.chariot.games.quizzo.model.quiz;

import org.springframework.roo.addon.equals.RooEquals;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooEquals
public class BooleanChoice extends Choice {

  public BooleanChoice(boolean correctValue) {
    this.correctValue = correctValue;
  }

  boolean correctValue;
  Boolean result;

  public BooleanChoice clone() throws CloneNotSupportedException {
    return (BooleanChoice) super.clone();
  }
}
