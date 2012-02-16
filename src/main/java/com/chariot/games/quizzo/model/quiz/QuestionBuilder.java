package com.chariot.games.quizzo.model.quiz;

public class QuestionBuilder {
  
  Question question;
  public QuestionBuilder() {
    question = new Question();
  }
  
  public QuestionBuilder title(String title) {
    question.setTitle(title);
    return this;
  }
  
  public QuestionBuilder choice(Choice choice) {
    question.getChoices().add(choice);
    return this;
  }
  
  public Question asQuestion() {
    return question;
  }
}
