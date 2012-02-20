package com.chariot.games.quizzo.engine;

import com.chariot.games.quizzo.model.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.ws.RespectBinding;
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
    runState.startQuiz(quiz.getId());
    runState.nextQuestion();

  }
}
