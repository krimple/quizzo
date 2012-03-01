package com.chariot.games.quizzo.service;

import com.chariot.games.quizzo.model.FillInTheBlankAnswer;
import org.springframework.roo.addon.layers.service.RooService;

@RooService(domainTypes = { com.chariot.games.quizzo.model.FillInTheBlankAnswer.class })
public interface FillInTheBlankAnswerService {
  FillInTheBlankAnswer findFillInTheBlankAnswerByQuestionId(Long questionId);
}
