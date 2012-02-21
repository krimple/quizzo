package com.chariot.games.quizzo.model;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class QuizRun {

  @NotNull
  @ManyToOne
  private Quiz quiz;

  @NotNull
  @Size(max = 300)
  private String text;

  @NotNull
  @Enumerated
  private QuizRunState runState;
}
