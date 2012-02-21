package com.chariot.games.quizzo.model;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import java.util.Set;
import java.util.TreeSet;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Answer {

  private Team team;
  private Question question;
  private Set<Choice> choices = new TreeSet<Choice>();

}
