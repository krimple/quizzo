package com.chariot.games.quizzo.model;

import org.hibernate.validator.constraints.Length;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.tostring.RooToString;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@RooJavaBean
@RooToString
@RooJpaEntity
public class Team {

    @NotNull
    @Length(max = 100)
    private String teamName;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "team_id")    
    private Set<TeamMember> teamMembers = new HashSet<TeamMember>();
}
