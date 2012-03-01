package com.chariot.games.quizzo.model;

import org.springframework.roo.addon.javabean.RooJavaBean;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue("F")
@RooJavaBean
public class QuestionWithFillInTheBlankAnswers extends Question {

  @NotNull
  @Size(max = 300)
  private String text;

  @NotNull
  private BigDecimal points;

}
