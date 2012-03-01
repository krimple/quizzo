package com.chariot.games.quizzo.db;

import com.chariot.games.quizzo.model.AnswerByChoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

import java.util.List;

@RooJpaRepository(domainType = AnswerByChoice.class)
public interface AnswerByChoiceRepository
    extends JpaRepository<AnswerByChoice, Long>,
    JpaSpecificationExecutor<AnswerByChoice> {

  @Query("SELECT a FROM AnswerByChoice AS a WHERE a.team.id = ?1 AND a.question.id = ?2")
  public List<AnswerByChoice> getAnswersByTeamIdAndQuestionId(Long teamId, Long questionId);
}
