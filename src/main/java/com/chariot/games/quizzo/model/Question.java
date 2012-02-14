package com.chariot.games.quizzo.model;

import org.hibernate.annotations.ManyToAny;
import org.hibernate.validator.constraints.Length;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.tostring.RooToString;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RooJavaBean
@RooToString
@RooJpaEntity
public class Question {
    @ManyToOne
    @NotNull
    private Quiz quiz;

    @NotNull
    private int relativeOrder;

    @Length(max = 500)
    @NotNull
    private String questionText;

    @OneToMany
    @JoinColumn(name = "question_id")
    @OrderColumn(name = "question_order")
    private List<Answer> answers = new ArrayList<Answer>();

}
