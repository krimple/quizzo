package com.chariot.games.quizzo.db;

import com.chariot.games.quizzo.model.TeamMember;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = TeamMember.class)
public interface TeamMemberRepository {
}
