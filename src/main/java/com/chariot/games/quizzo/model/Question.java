package com.chariot.games.quizzo.model;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Question {

  @NotNull
  @OrderColumn(name = "sort_order")
  private short sortOrder;

  @ManyToOne
  @JoinColumn(name = "quiz_id")
  private Quiz quiz;

  @NotNull
  @Size(max = 300)
  private String text;

  /**
   * Normally not something I'd do, but when taking a quiz question, we always load
   * the question and the potential choices together, so as to avoid a missing
   * JPA context when accessing the collection.
   */
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private Set<Choice> choices = new HashSet<Choice>();
}
