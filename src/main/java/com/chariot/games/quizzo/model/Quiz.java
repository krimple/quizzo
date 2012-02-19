package com.chariot.games.quizzo.model;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Quiz {

    @NotNull
    @Size(max = 200)
    private String title;

    @NotNull
    @Size(max = 500)
    private String description;

}
