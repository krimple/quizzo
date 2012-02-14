package com.chariot.games.quizzo.db;

import com.chariot.games.quizzo.model.Team;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = Team.class)
public interface TeamRepository {
}
