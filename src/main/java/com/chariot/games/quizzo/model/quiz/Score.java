package com.chariot.games.quizzo.model.quiz;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

import java.math.BigDecimal;

@RooJavaBean
@RooToString
public class Score {
  private BigDecimal value;
}
