package com.chariot.games.quizzo.model.quiz;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;

public class ChoiceBuilderTest {

  @Test
  public void testBuildChoice() {
    Choice choice = new ChoiceBuilder()
                      .booleanChoice(true)
                      .text("All dogs are equal")
                      .asChoice();
    
    assertNotNull(choice);
    assertEquals(BooleanChoice.class, choice.getClass());
    assertEquals("All dogs are equal", choice.getAnswerText());
    assertEquals(true, ((BooleanChoice)choice).correctValue);
  }
  
  @Test
  public void testCloneChoice() throws CloneNotSupportedException {
    Choice choice = new ChoiceBuilder()
                          .booleanChoice(true)
                          .text("All dogs are equal")
                          .asChoice();
    Choice choiceClone = choice.clone();
    assertFalse(choiceClone == choice);
    assertEquals(choiceClone, choice);
    assertNotSame(choiceClone, choice);
  }
}
