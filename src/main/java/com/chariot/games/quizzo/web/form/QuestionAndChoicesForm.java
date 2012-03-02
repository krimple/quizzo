package com.chariot.games.quizzo.web.form;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

import java.util.ArrayList;
import java.util.List;

@RooJavaBean
@RooToString
@RooJson
public class QuestionAndChoicesForm {
  //TODO put this in the session to keep spoofing out
  private Long teamId;

  private Long questionId;
  private String questionText;
  private List<ChoiceForm> choices = new ArrayList<ChoiceForm>();

}
