package com.chariot.games.quizzo.model;

import org.springframework.roo.addon.jpa.entity.RooJpaEntity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@RooJpaEntity
@DiscriminatorValue(value = "C")
public class AnswerByChoice extends Answer {

  @OneToMany(mappedBy = "answer", targetEntity = Choice.class)
  private Set<Choice> choices = new HashSet<Choice>();

  @Override
  public BigDecimal calculateScore() {
    BigDecimal score = new BigDecimal("0.0");
    for (Choice choice: choices) {
      if (choice.isCorrect()) {
        score = score.add(choice.getPointValue());
      }
    }
    return score.add(bonusPoints);
  }
}
