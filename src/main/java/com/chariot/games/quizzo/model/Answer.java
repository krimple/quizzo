package com.chariot.games.quizzo.model;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.tostring.RooToString;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@RooJavaBean
@RooToString
@RooJpaEntity
public class Answer {

  @NotNull
  @ManyToOne(optional = false)
  private Team team;

  @NotNull
  @ManyToOne(optional = false)
  private Question question;

  @OneToMany(cascade = CascadeType.ALL)
  private Set<Choice> choices = new HashSet<Choice>();

  @Transient
  public BigDecimal calculateScore() {
    BigDecimal score = new BigDecimal("0.0");
    for (Choice choice: this.getChoices()) {
      score = score.add(choice.getPointValue());
    }
    return score;
  }
}
