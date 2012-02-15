package com.chariot.games.quizzo.model;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.tostring.RooToString;

import javax.persistence.Entity;

@RooJpaEntity
@RooToString
@RooJavaBean
public class TrueFalseQuestion extends Question {

}
