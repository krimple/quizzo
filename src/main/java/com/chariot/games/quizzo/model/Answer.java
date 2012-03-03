package com.chariot.games.quizzo.model;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@RooJavaBean
@RooToString
@RooJpaEntity
@RooJson
public class Answer {

  @NotNull
  @ManyToOne(optional = false)
  @JoinColumn(name = "team_id")
  private Team team;

  @ManyToOne
  @JoinColumn(name = "answer_id")
  private Choice choice;


  @Transient
  public BigDecimal calculateScore() {
    return (choice.getCorrect() ? new BigDecimal("1.0") : BigDecimal.ZERO);
  }
}
