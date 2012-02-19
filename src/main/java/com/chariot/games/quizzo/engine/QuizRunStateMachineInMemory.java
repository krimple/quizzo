package com.chariot.games.quizzo.engine;

import com.chariot.games.quizzo.model.Answer;
import com.chariot.games.quizzo.model.Quiz;
import com.chariot.games.quizzo.model.QuizRun;
import com.chariot.games.quizzo.model.Team;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.stereotype.Component;

@RooJavaBean
@Component(value = "quizStateMachine")
public class QuizRunStateMachineInMemory implements QuizRunStateMachine {

  private QuizRun quizRun;

  @Override
  public void startQuiz(Long quizId) {
    Quiz quiz = Quiz.findQuiz(quizId);
    quizRun.setQuiz(quiz);



    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void nextQuestion() {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void getScores() {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void getCurrentQuestionId() {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void submitAnswer(Team team, Answer answer) {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void endQuiz() {
    //To change body of implemented methods use File | Settings | File Templates.
  }
}
