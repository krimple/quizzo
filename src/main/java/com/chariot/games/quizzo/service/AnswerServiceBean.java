package com.chariot.games.quizzo.service;


import com.chariot.games.quizzo.model.Answer;

import java.util.List;

public class AnswerServiceBean implements AnswerService {
  @Override
  public List<Answer> getAnswersByTeamIdAndQuestionId(Long teamId, Long questionId) {
    return answerRepository.getAnswersByTeamIdAndQuestionId(teamId, questionId);
  }
}
