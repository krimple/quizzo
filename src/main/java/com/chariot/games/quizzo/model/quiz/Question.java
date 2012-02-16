package com.chariot.games.quizzo.model.quiz;

import org.springframework.roo.addon.equals.RooEquals;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

import java.util.ArrayList;
import java.util.List;

@RooJavaBean
@RooToString
@RooEquals
public class Question implements Cloneable {

  public Question () {
    choices = new ArrayList<Choice>();
  }

  private String title;
  private List<Choice> choices;
  private Choice answer;

  public Question clone() throws CloneNotSupportedException {
      return (Question) super.clone();
  }

}
