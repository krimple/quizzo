package com.chariot.games.quizzo.web.flow;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.serializable.RooSerializable;
import org.springframework.roo.addon.tostring.RooToString;

import javax.validation.constraints.NotNull;

@RooJavaBean
@RooSerializable
@RooToString
public class VotingForm {
  @NotNull
  private Long questionId;

  @NotNull
  private Long choiceId;

  private Boolean selected;
}
