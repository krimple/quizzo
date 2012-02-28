package com.chariot.games.quizzo.model;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.serializable.RooSerializable;
import org.springframework.roo.addon.tostring.RooToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RooJavaBean
@RooToString
@RooJpaEntity
@RooSerializable
public class Team {

  @NotNull
  @Size(max = 80)
  private String name;

  @NotNull
  private String mission;

  @ElementCollection
  @JoinTable(name = "team_members")
  @JoinColumn(name="team_id")
  private List<TeamMember> teamMembers = new ArrayList<TeamMember>();

  @OneToMany(mappedBy = "team")
  private Set<Answer> answers = new HashSet<Answer>();

  @ManyToOne
  @JoinColumn(name = "quiz_run_id")
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
