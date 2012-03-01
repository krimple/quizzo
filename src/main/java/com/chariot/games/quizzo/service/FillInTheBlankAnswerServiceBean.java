package com.chariot.games.quizzo.service;


import com.chariot.games.quizzo.model.FillInTheBlankAnswer;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class FillInTheBlankAnswerServiceBean implements FillInTheBlankAnswerService {
  public FillInTheBlankAnswer findFillInTheBlankAnswerByQuestionId(Long questionId) {
    FillInTheBlankAnswer answer = fillInTheBlankAnswerRepository.findOne(
        new Specification<FillInTheBlankAnswer>() {
          @Override
          public Predicate toPredicate(Root<FillInTheBlankAnswer> fillInTheBlankAnswerRoot, CriteriaQuery<?> query, CriteriaBuilder cb) {
              cb.equal(fillInTheBlankAnswerRoot.get("question_id"));
//            fillInTheBlankAnswerRoot
          }
        }
    )
  }
}
