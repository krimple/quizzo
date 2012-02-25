package com.chariot.games.quizzo.model;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.tostring.RooToString;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@RooJavaBean
@RooToString
@RooJpaEntity
public class Quiz {

    @NotNull
    @Size(max = 200)
    private String title;

    @NotNull
    @Size(max = 500)
    private String description;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Question> questions = new HashSet<Question>();

}
