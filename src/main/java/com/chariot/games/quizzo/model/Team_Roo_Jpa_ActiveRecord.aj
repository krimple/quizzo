// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.chariot.games.quizzo.model;

import com.chariot.games.quizzo.model.Team;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect Team_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager Team.entityManager;
    
    public static final EntityManager Team.entityManager() {
        EntityManager em = new Team().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long Team.countTeams() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Team o", Long.class).getSingleResult();
    }
    
    public static List<Team> Team.findAllTeams() {
        return entityManager().createQuery("SELECT o FROM Team o", Team.class).getResultList();
    }
    
    public static Team Team.findTeam(Long id) {
        if (id == null) return null;
        return entityManager().find(Team.class, id);
    }
    
    public static List<Team> Team.findTeamEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Team o", Team.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void Team.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void Team.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Team attached = Team.findTeam(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void Team.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void Team.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public Team Team.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Team merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
