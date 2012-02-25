package com.chariot.games.quizzo.db;

import com.chariot.games.quizzo.model.Choice;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = Choice.class)
public interface ChoiceRepository {
}
