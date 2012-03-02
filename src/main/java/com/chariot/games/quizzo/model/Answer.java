package com.chariot.games.quizzo.model;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@RooJavaBean
@RooToString
@RooJpaEntity
@RooJson
public class Answer {

  @NotNull
  @ManyToOne(optional = false)
  @JoinColumn(name = "team_id")
  private Team team;

  @NotNull
  @ManyToOne(optional = false)
  @JoinColumn(name = "question_id")
  private Question question;

  @ManyToMany
  @JoinTable(name = "selected_choice",
      inverseJoinColumns = { @JoinColumn(name = "choice_id")},
      joinColumns = { @JoinColumn(name = "answer_id")}
  )
  private Set<Choice> selectedChoices = new HashSet<Choice>();

  private String fillInAnswer;

  @DecimalMin("-100.0")
  @DecimalMax("100.0")
  protected BigDecimal bonusPoints = new BigDecimal("0.0");

  @Transient
  public BigDecimal calculateScore() {
    BigDecimal score = new BigDecimal("0.0");
    for (Choice choice : selectedChoices) {
      if (choice.isCorrect()) {
        score = score.add(choice.getPointValue());
      }
    }
    score = score.add(bonusPoints);
    return score;
  }
}
