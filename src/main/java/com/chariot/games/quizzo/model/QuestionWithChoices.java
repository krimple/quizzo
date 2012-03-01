package com.chariot.games.quizzo.model;

import org.springframework.roo.addon.javabean.RooJavaBean;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@RooJavaBean
@Entity
@DiscriminatorValue(value = "C")
public class QuestionWithChoices extends Question {

  @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
  @JoinColumn(name = "question_id")
  private Set<Choice> choices = new HashSet<Choice>();
}
