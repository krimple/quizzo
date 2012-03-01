package com.chariot.games.quizzo.db;

import com.chariot.games.quizzo.model.Team;
import org.springframework.data.jpa.repository.Query;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

import java.util.List;

@RooJpaRepository(domainType = Team.class)
public interface TeamRepository extends FlushableAbstractRepository {

  @Query("select t from Team t where t.quizRun.id = ?1")
  public List<Team> teamsByQuizRun(Long quizRunId);
}
