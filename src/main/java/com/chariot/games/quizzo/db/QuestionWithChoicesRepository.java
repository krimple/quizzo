package com.chariot.games.quizzo.db;

import com.chariot.games.quizzo.model.QuestionWithChoices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = QuestionWithChoices.class)
public interface QuestionWithChoicesRepository
    extends JpaRepository<QuestionWithChoices, Long>,
    JpaSpecificationExecutor<QuestionWithChoices> {
}
