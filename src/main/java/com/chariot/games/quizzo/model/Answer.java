package com.chariot.games.quizzo.model;

import java.math.BigDecimal;
import java.util.Set;
import java.util.TreeSet;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findAnswersByTeamAndQuestion" })
public class Answer {

    private Team team;

    private Question question;

    private Set<Choice> choices = new TreeSet<Choice>();

    public BigDecimal calculateScore() {
        BigDecimal score = new BigDecimal("0.0");
        for (Choice choice : choices) {
        }
        return null;
    }
}
