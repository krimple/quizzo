package com.chariot.games.quizzo.db;

import com.chariot.games.quizzo.model.Answer;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = Answer.class)
public interface AnswerRepository {
}
