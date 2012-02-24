package com.chariot.games.quizzo.web.flow;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.serializable.RooSerializable;
import org.springframework.roo.addon.tostring.RooToString;

import java.util.ArrayList;
import java.util.List;

@RooJavaBean
@RooToString
@RooSerializable
public class TeamSetupForm {
  private String name;
  private String message;
  private List<String> teamMemberNames = new ArrayList<String>();

  public TeamSetupForm() {
    for (int i = 0; i < 4; i++) {
      teamMemberNames.add("Player " + (i+1));
    }
  }

}
