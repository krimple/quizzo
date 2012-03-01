package com.chariot.games.quizzo.web.ajax.request;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

import java.util.ArrayList;
import java.util.List;

@RooJavaBean
@RooToString
@RooJson
public class NewTeamRequest {
  private long quizRunId;
  private String teamName;
  private String mission;
  private List<String> teamMembers = new ArrayList<String>();
}
