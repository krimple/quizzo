package com.chariot.games.quizzo.model.quiz;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

import java.util.ArrayList;
import java.util.List;

@RooJavaBean
@RooToString
public class Team {
  public Team(String teamName, String description) {
    this.teamName = teamName;
    this.description = description;
    this.score = new Score();
    this.teamMembers = new ArrayList<String>();
  }

  private String teamName;
  private String description;
  private Score score;
  private List<String> teamMembers;
}
