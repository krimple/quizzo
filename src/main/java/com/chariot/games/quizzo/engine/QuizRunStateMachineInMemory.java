package com.chariot.games.quizzo.engine;

import com.chariot.games.quizzo.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.*;

@RooJavaBean
@Component(value = "quizStateMachine")
public class QuizRunStateMachineInMemory implements QuizRunStateMachine {

  private JdbcTemplate jdbcTemplate;
  private Question currentQuestion;

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
  private int currentQuestionIndex;

  private List<Question> questions;

  @Override
  @Transactional
  public void startQuiz(Long quizId, String text) {
    Quiz quiz = Quiz.findQuiz(quizId);
    quizRun = new QuizRun();
    quizRun.setQuiz(quiz);
    quizRun.setRunState(QuizRunState.NOT_STARTED);
    quizRun.setText(text);
    quizRun.persist();
    quizRun.flush();

    questions = Quiz.entityManager().createQuery(
        "select q from Question q " +
            "where q.quiz.id = :quizId order by q.sortOrder")
        .setParameter("quizId", quiz.getId()).getResultList();

  }

  @Transactional
  private void setupFirstQuestion() {
    // now, load up our first question...
    assert (questions != null && questions.size() > 0);
    assert quizRun.getRunState() == QuizRunState.NOT_STARTED;

    quizRun.setRunState(QuizRunState.IN_PROGRESS);
    currentQuestionIndex = 0;
    currentQuestion = questions.get(currentQuestionIndex);
  }

  @Override
  @Transactional(readOnly = true)
  public boolean nextQuestion() {
    assert quizRun.getRunState() == QuizRunState.IN_PROGRESS;

    if (currentQuestionIndex + 1 == questions.size()) {
      quizRun.setRunState(QuizRunState.COMPLETE);
      return false;
    } else {
      currentQuestionIndex++;
      currentQuestion = questions.get(currentQuestionIndex);
      return true;
    }
  }

  @Override
  public Map<String, BigDecimal> getScores() {
    Map<String, BigDecimal> scores = new HashMap<String, BigDecimal>();
    Query query = Team.entityManager().createQuery("select t from team " +
        "t where t.quizRun.id = :id").setParameter("id", quizRun.getId());
    List<Team> teams = query.getResultList();
    Iterator<Team> itTeams = teams.iterator();
    while (itTeams.hasNext()) {
      Team team = itTeams.next();

    }

    return null;
  }

  @Override
  public Long getCurrentQuestionId() {
    return currentQuestion.getId();
  }

  @Override
  @Transactional
  public boolean submitAnswer(Team team, Answer answer) {
    // voting over sucker, you are hosed...
    if (currentQuestion.getId() != answer.getQuestion().getId()) return false;

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
    quizRun.setRunState(QuizRunState.COMPLETE);
  }
}
