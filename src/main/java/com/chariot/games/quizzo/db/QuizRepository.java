package com.chariot.games.quizzo.db;

import com.chariot.games.quizzo.model.Quiz;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = Quiz.class)
public interface QuizRepository {

}
