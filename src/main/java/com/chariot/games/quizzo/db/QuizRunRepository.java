package com.chariot.games.quizzo.db;

import com.chariot.games.quizzo.model.QuizRun;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = QuizRun.class)
public interface QuizRunRepository extends FlushableAbstractRepository {
}
