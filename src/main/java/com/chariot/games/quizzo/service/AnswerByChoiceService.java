package com.chariot.games.quizzo.service;

import com.chariot.games.quizzo.model.Answer;
import com.chariot.games.quizzo.model.AnswerByChoice;
import org.springframework.roo.addon.layers.service.RooService;

import java.util.List;

@RooService(domainTypes = { AnswerByChoice.class })
public interface AnswerByChoiceService {
  public List<AnswerByChoice> getAnswersByTeamIdAndQuestionId(Long teamId, Long questionId);
}
