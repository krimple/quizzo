package com.chariot.games.quizzo.service;

import com.chariot.games.quizzo.model.Question;
import org.springframework.roo.addon.layers.service.RooService;

import java.util.List;

@RooService(domainTypes = { com.chariot.games.quizzo.model.Question.class })
public interface QuestionService {
  public List<Question> getQuestionsByQuizId(Long quizId);
}
