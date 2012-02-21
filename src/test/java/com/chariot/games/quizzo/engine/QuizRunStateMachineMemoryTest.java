package com.chariot.games.quizzo.engine;

import com.chariot.games.quizzo.model.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.TreeSet;

@ContextConfiguration(locations = { "classpath*:META-INF/spring/applicationContext*.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class QuizRunStateMachineMemoryTest {

  Set<Question> mockQuestions;

  @Before
  public void setup() {
    mockQuestions = new TreeSet<Question>();
  }

  @Test
  @Transactional
  public void testLoadQuestions() {
    QuizRunStateMachine runState = new QuizRunStateMachineInMemory();
    QuizDataOnDemand dod = new QuizDataOnDemand();
    Quiz quiz = dod.getRandomQuiz();
    QuestionDataOnDemand qdod = new QuestionDataOnDemand();
    for (int i = 0; i < 5; i++) {
      ChoiceDataOnDemand cdod = new ChoiceDataOnDemand();
      Question q = qdod.getNewTransientQuestion(i);
      q.setQuiz(quiz);
      quiz.getQuestions().add(q);
      for (int j = 0; j < 5; j++) {
        Choice c = cdod.getNewTransientChoice(i);
        c.setQuestion(q);
        q.getChoices().add(c);

      }
    }
    quiz.merge();
    runState.startQuiz(quiz.getId());
    runState.nextQuestion();

  }
}
