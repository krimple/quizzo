package com.chariot.games.quizzo.model.quiz;

import org.junit.Test;

public class QuestionBuilderTest {
  
  @Test
  public void testBuildQuestionWithBooleanAnswer() {
    
    Question q = new QuestionBuilder()
                  .title("Studies say people are crazy.")
                  .choice(new ChoiceBuilder()
                          .booleanChoice(true)
                          .text("They are crazy")
                          .asChoice()
                  ).asQuestion();

    
  }
}
