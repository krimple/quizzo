package com.chariot.games.quizzo.model;

import org.hibernate.validator.constraints.Length;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.tostring.RooToString;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@RooJavaBean
@RooToString
@RooJpaEntity
public class Answer {

    @ManyToOne
    private Question question;

    @NotNull
    @Length(max = 200)
    String answerText;

    @NotNull
    @Column(name = "question_order")
    private Integer questionOrder;

    @NotNull
    @Column(name = "correct_answer")
    private Boolean isCorrectAnswer;

}
