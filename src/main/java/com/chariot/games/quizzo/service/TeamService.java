package com.chariot.games.quizzo.service;

import org.springframework.roo.addon.layers.service.RooService;

import java.math.BigDecimal;
import java.util.Map;

@RooService(domainTypes = { com.chariot.games.quizzo.model.Team.class })
public interface TeamService {

  public Map<String, BigDecimal> calcScoresByQuizRun(Long quizRunId);


}