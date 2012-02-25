package com.chariot.games.quizzo.service;


import com.chariot.games.quizzo.model.Question;

import java.util.List;

public class QuestionServiceBean implements QuestionService {
  public List<Question> getQuestionsByQuizId(Long quizId){
    return questionRepository.getQuestionsByQuizId(quizId);
  }
}
