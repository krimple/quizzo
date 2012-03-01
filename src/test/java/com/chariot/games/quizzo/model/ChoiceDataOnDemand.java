package com.chariot.games.quizzo.model;

import org.springframework.roo.addon.dod.RooDataOnDemand;

@RooDataOnDemand(entity = Choice.class)
public class ChoiceDataOnDemand {

  public void setAnswer(Choice obj, int index) {
    AnswerByChoice answer = null;
    obj.setAnswer(answer);
  }


  public void setQuestion(Choice obj, int index) {
    QuestionWithChoices question = null;
    obj.setQuestion(question);
  }
}
