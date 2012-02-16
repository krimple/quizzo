package com.chariot.games.quizzo.model.quiz;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertNotSame;

public class QuestionBuilderTest {

  @Test
  public void testBuildQuestionWithBooleanAnswer() throws CloneNotSupportedException {

    Question q = new QuestionBuilder()
                  .title("Studies say people are crazy.")
                  .choice(new ChoiceBuilder()
                          .booleanChoice(true)
                          .text("They are crazy")
                          .asChoice()
                  ).asQuestion();

      assertEquals("Studies say people are crazy", q.getTitle());
      assertEquals(1, q.getChoices().size());
      assertEquals("They are crazy", q.getChoices().get(1).getAnswerText());

      assertEquals(BooleanChoice.class, );

      Question q2 = q.clone();
      assertEquals(q2, q);
      assertNotSame(q2, q);



  }
}
