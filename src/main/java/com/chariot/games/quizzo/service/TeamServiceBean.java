package com.chariot.games.quizzo.service;


import com.chariot.games.quizzo.model.Team;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class TeamServiceBean implements TeamService {

  @Transactional(readOnly = true)
  public Map<String, BigDecimal> calcScoresByQuizRun(Long quizRunId) {

    Map<String, BigDecimal> scores = new HashMap<String, BigDecimal>();
    List<Team> teams = teamRepository.teamsByQuizRun(quizRunId);
    Iterator<Team> itTeams = teams.iterator();
          while (itTeams.hasNext()) {
            Team team = itTeams.next();
            scores.put(team.getName(), team.calculateTotalScore());
          }
          return scores;
  }
}
