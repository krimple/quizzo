package com.chariot.games.quizzo.model;

import org.hibernate.validator.constraints.Length;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.tostring.RooToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@RooJavaBean
@RooToString
@RooJpaEntity
public class Answer {

    @ManyToOne( cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "question_id")
    private Question question;

    @NotNull
    @Length(max = 200)
    String answerText;

    @NotNull
    @Column(name = "answer_order")
    private Integer answerOrder;

    @NotNull
    @Column(name = "correct_answer")
    private Boolean isCorrectAnswer;
  
    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "answer_id")
    private Set<Vote> answerVotes = new HashSet<Vote>();

}
