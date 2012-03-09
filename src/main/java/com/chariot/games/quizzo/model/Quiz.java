package com.chariot.games.quizzo.model;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@RooJavaBean
@RooToString
@RooJpaEntity
@RooJson
public class Quiz {

  @NotNull
  @Size(max = 200)
  private String title;

  @NotNull
  @Size(max = 500)
  private String description;

  @NotNull
  private BigDecimal defaultPointValue = new BigDecimal("100.0");

  @OneToMany(mappedBy = "quiz",
      cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
  private Set<Question> questions = new HashSet<Question>();

  @OneToMany(mappedBy = "quiz", cascade = CascadeType.REMOVE)
  private Set<QuizRun> quizRuns;
}
