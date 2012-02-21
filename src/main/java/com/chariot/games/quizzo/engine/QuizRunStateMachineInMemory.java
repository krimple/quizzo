package com.chariot.games.quizzo.engine;

import com.chariot.games.quizzo.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.*;
import com.chariot.games.quizzo.model.QuizRun;
import com.chariot.games.quizzo.model.QuizRunState;

@RooJavaBean
@Component(value = "quizStateMachine")
public class QuizRunStateMachineInMemory implements QuizRunStateMachine {

  private JdbcTemplate jdbcTemplate;

  @Autowired
  private void setDataSource(DataSource datasource) {
      jdbcTemplate = new JdbcTemplate(datasource);
  }

  /**
   * The quiz under execution by this instance
   */
  private QuizRun quizRun;

  /**
   * Our key to the current question ID. All questions are sorted by a sort order.
   */
  private Long currentQuestionId;

  @Override
  @Transactional
  public void startQuiz(Long quizId) {
    Quiz quiz = Quiz.findQuiz(quizId);
    quizRun = new QuizRun();
    quizRun.setQuiz(quiz);
    quizRun.setText("It's a new day");
    quizRun.persist();
    quizRun.flush();

    setupFirstQuestion();
  }

  @Transactional
  private void setupFirstQuestion() {
    assert quizRun.getRunState() == QuizRunState.NOT_STARTED;
    quizRun.setRunState(QuizRunState.IN_PROGRESS);
    currentQuestionId = jdbcTemplate.queryForLong("select question_id from questions q" +
        " where quiz_id = ? and " +
        " sort_order = ( " +
        "   select min(sort_order)" +
        "   from questions q2 " +
        "   where q2.quiz_id = q.quiz_id)", quizRun.getQuiz().getId());

  }

  @Override
  @Transactional(readOnly = true)
  public boolean nextQuestion() {

    assert quizRun.getRunState() == QuizRunState.IN_PROGRESS;
    currentQuestionId = jdbcTemplate.queryForLong("select question_id from questions q" +
            " where quiz_id = ? and " +
            " sort_order = ( " +
            "   select min(sort_order)" +
            "   from questions q2 " +
            "   where q2.quiz_id = q.quiz_id " +
        "             and q2.sort_order > ?)", quizRun.getQuiz().getId(), currentQuestionId);
    if (currentQuestionId == null) {
      quizRun.setRunState(QuizRunState.COMPLETE);
      return false;
    }

    return true;
  }

  @Override
  public Map<String, BigDecimal> getScores() {
    Map<String, BigDecimal> scores = new HashMap<String, BigDecimal>();
    Query query = Team.entityManager().createQuery("select t from Teams t where " +
        " t.quizRun.id = :id").setParameter("id", quizRun.getId());
    List<Team> teams = query.getResultList();
    Iterator<Team> itTeams = teams.iterator();
    while (itTeams.hasNext()) {
      Team team = itTeams.next();

    }

    return null;
  }

  @Override
  public Long getCurrentQuestionId() {
    return currentQuestionId;
  }

  @Override
  @Transactional
  public boolean submitAnswer(Team team, Answer answer) {
    // voting over sucker, you are hosed...
    if (currentQuestionId != answer.getQuestion().getId()) return false;

    // otherwise, wipe existing answer & save new one
    List<Answer> existingAnswer = Answer.findAnswersByTeamAndQuestion(team, answer.getQuestion()).getResultList();
    // remove current answer to overwrite with new one
    if (existingAnswer.size() == 1) {
      existingAnswer.get(0).remove();
    }
    answer.persist();
    return true;
  }

  @Override
  public void endQuiz() {

  }
}
