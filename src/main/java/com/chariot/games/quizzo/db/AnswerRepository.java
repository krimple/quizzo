package com.chariot.games.quizzo.db;

import com.chariot.games.quizzo.model.Answer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

import java.util.List;

@RooJpaRepository(domainType = Answer.class)
public interface AnswerRepository {

  @Query("SELECT a FROM Answer AS a WHERE a.team.id = ?1 AND a.choice.question.id = ?2")
  public List<Answer> getAnswersByTeamIdAndQuestionId(Long teamId, Long questionId);

  // TODO - debug this query.
  @Query("delete from Answer AS a WHERE a.choice.question.id = ?1 and a.team.id = ?2")
  public void deleteAllAnswersByQuestionIdAndTeamId(Long questionId, Long teamId);
}

