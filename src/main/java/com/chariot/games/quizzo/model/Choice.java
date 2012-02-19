package com.chariot.games.quizzo.model;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Choice {

    @NotNull
    @Size(max = 300)
    private String text;

    @NotNull
    private Boolean correct;

    @NotNull
    @ManyToOne
    private Question question;
}