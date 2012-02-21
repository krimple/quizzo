package com.chariot.games.quizzo.model;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.serializable.RooSerializable;
import org.springframework.roo.addon.tostring.RooToString;

import javax.persistence.ElementCollection;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
@RooSerializable
public class Team {

  @NotNull
  @Size(max = 80)
  private String name;

  @NotNull
  private String mission;

  @ElementCollection
  private List<TeamMember> teamMembers = new ArrayList<TeamMember>();

  @OneToMany
  private Set<Answer> answers = new HashSet<Answer>();

  @NotNull
  @ManyToOne(optional = false)
  private QuizRun quizRun;

  @Transient
  public BigDecimal calculateTotalScore() {
    BigDecimal score = new BigDecimal("0.0");
    for (Answer answer: answers) {
      score = score.add(answer.calculateScore());
    }
    return score;
  }

}
