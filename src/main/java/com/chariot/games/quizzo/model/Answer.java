package com.chariot.games.quizzo.model;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@RooJavaBean
@RooToString
@Entity
@RooJson
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "answer_type", length = 1)
public abstract class Answer {

  @NotNull
  @ManyToOne(optional = false)
  @JoinColumn(name = "team_id")
  private Team team;

  @NotNull
  @ManyToOne(optional = false)
  @JoinColumn(name = "question_id")
  private Question question;


  @DecimalMin("-100.0")
  @DecimalMax("100.0")
  protected BigDecimal bonusPoints = new BigDecimal("0.0");

  @Transient
  public abstract BigDecimal calculateScore();

}
