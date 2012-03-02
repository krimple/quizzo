package com.chariot.games.quizzo.web.form;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJson
public class ChoiceForm {
  private long id;
  private String choiceText;
  private boolean checked;

}
