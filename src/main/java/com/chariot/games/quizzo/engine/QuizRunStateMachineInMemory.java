package com.chariot.games.quizzo.engine;

import com.chariot.games.quizzo.model.Answer;
import com.chariot.games.quizzo.model.Quiz;
import com.chariot.games.quizzo.model.QuizRun;
import com.chariot.games.quizzo.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RooJavaBean
@Component(value = "quizStateMachine")
public class QuizRunStateMachineInMemory implements QuizRunStateMachine {

  private JdbcTemplate jdbcTemplate;

  @Autowired
  private void setDataSource(DataSource datasource) {
      jdbcTemplate = new JdbcTemplate(datasource);
  }

  private QuizRun quizRun;
  private Long currentQuestionId;

  @OneToMany
  private Map<Team, Set<Answer>> answersByTeam = new HashMap<Team, Set<Answer>>();

  @Override
  @Transactional
  public void startQuiz(Long quizId) {
    Quiz quiz = Quiz.findQuiz(quizId);
    quizRun = new QuizRun();
    quizRun.setQuiz(quiz);
    quizRun.setText("It's a new day");
    quizRun.persist();
    quizRun.flush();

    setupFirstQuestion(quizRun);
  }

  private void setupFirstQuestion() {
    currentQuestionId = jdbcTemplate.queryForLong("select question_id from questions q" +
        " where quiz_id = ? and " +
        " sort_order = ( " +
        "   select min(sort_order)" +
        "   from questions q2 " +
        "   where q2.quiz_id = q.quiz_id)", quizRun.getQuiz().getId());
  }

  @Override
  public void nextQuestion() {
    currentQuestionId = jdbcTemplate.queryForLong("select question_id from questions q" +
            " where quiz_id = ? and " +
            " sort_order = ( " +
            "   select min(sort_order)" +
            "   from questions q2 " +
            "   where q2.quiz_id = q.quiz_id " +
        "             and q2.sort_order > ?)", quizRun.getQuiz().getId(), currentQuestionId);
  }

  @Override
  public void getScores() {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public Long getCurrentQuestionId() {
    return currentQuestionId;
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
