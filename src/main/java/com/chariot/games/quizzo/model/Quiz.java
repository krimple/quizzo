package com.chariot.games.quizzo.model;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.tostring.RooToString;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import java.util.Set;
import java.util.TreeSet;

@RooJavaBean
@RooToString
@RooJpaEntity
public class Quiz {

    private String name;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
    @OrderColumn(name = "position")
    private Set<Question> questions = new TreeSet<Question>();

}
