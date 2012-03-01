package com.chariot.games.quizzo.model;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderColumn;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@RooJavaBean
@RooToString
@RooJpaEntity
@RooJson
public class Choice {

  @ManyToOne
  @JoinColumn(name = "answer_id")
  private AnswerByChoice answer;

  @NotNull
  @Size(max = 300)
  private String text;

  @NotNull
  @DecimalMin("-1000")
  @DecimalMax("1000")
  protected BigDecimal pointValue;

  @NotNull
  @ManyToOne(optional = false)
  @JoinColumn(name = "question_id")
  private QuestionWithChoices question;

  @OrderColumn(name = "sort_order")
  private short sortOrder;

  @NotNull
  private boolean correct;

  // TODO: JIRA for fix Roo's stubbornness around super/subs
  public AnswerByChoice getAnswer() {
    return this.answer;
  }

  public void setAnswer(AnswerByChoice answer) {
    this.answer = answer;
  }

}
