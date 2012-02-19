package com.chariot.games.quizzo.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Question {

    @ManyToOne
    private Quiz quiz;

    @NotNull
    @Size(max = 300)
    private String text;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Choice> choices = new HashSet<Choice>();
}
