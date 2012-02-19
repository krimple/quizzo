package com.chariot.games.quizzo.model;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import javax.persistence.ElementCollection;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Team {

    @NotNull
    @Size(max = 80)
    private String name;

    @NotNull
    private String mission;

    @ElementCollection
    private List<TeamMember> teamMembers;
}
