package com.chariot.games.quizzo.db;

import com.chariot.games.quizzo.model.QuestionWithFillInTheBlankAnswers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = QuestionWithFillInTheBlankAnswers.class)
public interface QuestionWithFillInTheBlankAnswersRepository
    extends JpaRepository<QuestionWithFillInTheBlankAnswers, Long>,
    JpaSpecificationExecutor<QuestionWithFillInTheBlankAnswers> {
}
