package com.chariot.games.quizzo.model;

import org.hibernate.validator.constraints.Length;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.tostring.RooToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.TreeSet;

@RooJavaBean
@RooToString
@RooJpaEntity
public class Question {

  @ManyToOne(optional = false, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinColumn(name = "quiz_id")
  @NotNull
  private Quiz quiz;

  @Length(max = 500)
  @NotNull
  private String questionText;

  @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
  @OrderBy("answerOrder ASC")
  private Set<Answer> answers = new TreeSet<Answer>();

}
