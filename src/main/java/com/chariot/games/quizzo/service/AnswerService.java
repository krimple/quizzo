package com.chariot.games.quizzo.service;

import com.chariot.games.quizzo.model.Answer;
import com.chariot.games.quizzo.model.Choice;
import com.chariot.games.quizzo.model.Question;
import com.chariot.games.quizzo.model.Team;
import org.springframework.roo.addon.layers.service.RooService;

import java.util.List;

@RooService(domainTypes = { com.chariot.games.quizzo.model.Answer.class })

public interface AnswerService {
  List<Answer> getAnswersByTeamIdAndQuestionId(Long teamId, Long questionId);
  void replaceAnswers(Team team, Question question, Choice choice);

}
