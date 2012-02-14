package com.chariot.games.quizzo.db;

import com.chariot.games.quizzo.model.Question;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = Question.class)
public interface QuestionRepository {
}
