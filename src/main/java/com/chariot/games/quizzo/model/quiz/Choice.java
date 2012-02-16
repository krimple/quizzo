package com.chariot.games.quizzo.model.quiz;

import org.springframework.roo.addon.equals.RooEquals;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooEquals
public abstract class Choice implements Cloneable {

  private boolean isCorrect;

  private String answerText;
  
  public Choice clone() throws CloneNotSupportedException {
    return (Choice) super.clone();
  }

}
