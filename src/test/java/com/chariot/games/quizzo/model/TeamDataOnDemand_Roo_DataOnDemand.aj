// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.chariot.games.quizzo.model;

import com.chariot.games.quizzo.model.QuizRun;
import com.chariot.games.quizzo.model.QuizRunDataOnDemand;
import com.chariot.games.quizzo.model.Team;
import com.chariot.games.quizzo.model.TeamDataOnDemand;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

privileged aspect TeamDataOnDemand_Roo_DataOnDemand {
    
    declare @type: TeamDataOnDemand: @Component;
    
    private Random TeamDataOnDemand.rnd = new SecureRandom();
    
    private List<Team> TeamDataOnDemand.data;
    
    @Autowired
    private QuizRunDataOnDemand TeamDataOnDemand.quizRunDataOnDemand;
    
    public Team TeamDataOnDemand.getNewTransientTeam(int index) {
        Team obj = new Team();
        setMission(obj, index);
        setName(obj, index);
        setQuizRun(obj, index);
        return obj;
    }
    
    public void TeamDataOnDemand.setMission(Team obj, int index) {
        String mission = "mission_" + index;
        obj.setMission(mission);
    }
    
    public void TeamDataOnDemand.setName(Team obj, int index) {
        String name = "name_" + index;
        if (name.length() > 80) {
            name = name.substring(0, 80);
        }
        obj.setName(name);
    }
    
    public void TeamDataOnDemand.setQuizRun(Team obj, int index) {
        QuizRun quizRun = quizRunDataOnDemand.getRandomQuizRun();
        obj.setQuizRun(quizRun);
    }
    
    public Team TeamDataOnDemand.getSpecificTeam(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        Team obj = data.get(index);
        Long id = obj.getId();
        return Team.findTeam(id);
    }
    
    public Team TeamDataOnDemand.getRandomTeam() {
        init();
        Team obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return Team.findTeam(id);
    }
    
    public boolean TeamDataOnDemand.modifyTeam(Team obj) {
        return false;
    }
    
    public void TeamDataOnDemand.init() {
        int from = 0;
        int to = 10;
        data = Team.findTeamEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'Team' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<Team>();
        for (int i = 0; i < 10; i++) {
            Team obj = getNewTransientTeam(i);
            try {
                obj.persist();
            } catch (ConstraintViolationException e) {
                StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getConstraintDescriptor()).append(":").append(cv.getMessage()).append("=").append(cv.getInvalidValue()).append("]");
                }
                throw new RuntimeException(msg.toString(), e);
            }
            obj.flush();
            data.add(obj);
        }
    }
    
}
