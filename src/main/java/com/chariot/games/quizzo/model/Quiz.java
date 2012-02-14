package com.chariot.games.quizzo.model;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.tostring.RooToString;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import java.util.ArrayList;
import java.util.List;

@RooJavaBean
@RooToString
@RooJpaEntity
public class Quiz {

    private String name;

    @OneToMany
    @JoinColumn(name = "quiz_id")
    @OrderColumn(name = "question_order")
    private List<Question> questions = new ArrayList<Question>();

}
