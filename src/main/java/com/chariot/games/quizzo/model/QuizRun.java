package com.chariot.games.quizzo.model;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.serializable.RooSerializable;
import org.springframework.roo.addon.tostring.RooToString;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@RooJavaBean
@RooToString
@RooJpaEntity
@RooSerializable
public class QuizRun {

  @NotNull
  @ManyToOne
  @JoinColumn(name = "quiz_id")
  private Quiz quiz;

  @NotNull
  @Size(max = 300)
  private String text;

  @Transient
  private QuizRunState runState;

}
