package com.chariot.games.quizzo.db;

import com.chariot.games.quizzo.model.FillInTheBlankAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = FillInTheBlankAnswer.class)
public interface FillInTheBlankAnswerRepository
   extends JpaRepository<FillInTheBlankAnswer, Long>,
    JpaSpecificationExecutor<FillInTheBlankAnswer> {
}
