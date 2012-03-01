package com.chariot.games.quizzo.model;

import org.springframework.roo.addon.jpa.entity.RooJpaEntity;

import javax.persistence.DiscriminatorValue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@RooJpaEntity
@DiscriminatorValue("F")
public class FillInTheBlankAnswer extends Answer {

  @Size(max = 100)
  private String choiceText;

  @NotNull
  private Boolean textCorrect = Boolean.FALSE;

  @NotNull
  private BigDecimal bonusPoints = new BigDecimal("0.0");

  @Override
  public BigDecimal calculateScore() {
    if (textCorrect) {
      return null;
    }
    return null;
  }
}
