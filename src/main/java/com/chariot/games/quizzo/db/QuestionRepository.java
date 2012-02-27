package com.chariot.games.quizzo.db;

import com.chariot.games.quizzo.model.Question;
import org.springframework.data.jpa.repository.Query;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

import java.util.List;

@RooJpaRepository(domainType = Question.class)
public interface QuestionRepository {
  @Query("select q from Question q where q.quiz.id = ?1 order by q.sortOrder")
  public List<Question> getQuestionsByQuizIdOrderByQuestionOrder(Long quizId);
}
