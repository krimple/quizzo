package com.chariot.games.quizzo.service;


import com.chariot.games.quizzo.db.AnswerByChoiceRepository;
import com.chariot.games.quizzo.model.Answer;
import com.chariot.games.quizzo.model.AnswerByChoice;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;



public class AnswerByChoiceServiceBean implements AnswerByChoiceService {

  public List<AnswerByChoice> getAnswersByTeamIdAndQuestionId(Long teamId, Long questionId){
    return answerByChoiceRepository.getAnswersByTeamIdAndQuestionId(teamId, questionId);
  }
}
