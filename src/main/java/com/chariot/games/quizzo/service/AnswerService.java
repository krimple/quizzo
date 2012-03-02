package com.chariot.games.quizzo.service;

import com.chariot.games.quizzo.model.Answer;
import org.springframework.roo.addon.layers.service.RooService;

import java.util.List;

@RooService(domainTypes = { com.chariot.games.quizzo.model.Answer.class })
public interface AnswerService {
  List<Answer> getAnswersByTeamIdAndQuestionId(Long teamId, Long questionId);

}
