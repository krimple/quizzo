// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.chariot.games.quizzo.model;

import com.chariot.games.quizzo.model.Answer;
import com.chariot.games.quizzo.model.Question;
import com.chariot.games.quizzo.model.Team;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

privileged aspect Answer_Roo_Finder {
    
    public static TypedQuery<Answer> Answer.findAnswersByTeamAndQuestion(Team team, Question question) {
        if (team == null) throw new IllegalArgumentException("The team argument is required");
        if (question == null) throw new IllegalArgumentException("The question argument is required");
        EntityManager em = Answer.entityManager();
        TypedQuery<Answer> q = em.createQuery("SELECT o FROM Answer AS o WHERE o.team = :team AND o.question = :question", Answer.class);
        q.setParameter("team", team);
        q.setParameter("question", question);
        return q;
    }
    
}
