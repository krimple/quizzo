package com.chariot.games.quizzo.engine;



import com.chariot.games.quizzo.model.*;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.stereotype.Component;

import java.util.Set;

@RooJavaBean
@Component(value = "quizStateMachine")
public class QuizRunStateMachineInMemory implements QuizRunStateMachine {

  private QuizRun quizRun;
  private Set<Question> questions;
  private int currentQuestionIndex = -1;

  @Override
  public void startQuiz(Long quizId) {
    Quiz quiz = Quiz.findQuiz(quizId);
    quizRun = new QuizRun();
    quizRun.setQuiz(quiz);
    quizRun.setText("It's a new day");
    quizRun.persist();
    questions = quiz.getQuestions();
  }

  @Override
  public void nextQuestion() {
    currentQuestionIndex++;
    assert currentQuestionIndex < (questions.size() - 1);
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
