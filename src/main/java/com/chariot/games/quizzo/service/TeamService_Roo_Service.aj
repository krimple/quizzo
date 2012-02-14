// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.chariot.games.quizzo.service;

import com.chariot.games.quizzo.model.Team;
import com.chariot.games.quizzo.service.TeamService;
import java.util.List;

privileged aspect TeamService_Roo_Service {
    
    public abstract long TeamService.countAllTeams();    
    public abstract void TeamService.deleteTeam(Team team);    
    public abstract Team TeamService.findTeam(Long id);    
    public abstract List<Team> TeamService.findAllTeams();    
    public abstract List<Team> TeamService.findTeamEntries(int firstResult, int maxResults);    
    public abstract void TeamService.saveTeam(Team team);    
    public abstract Team TeamService.updateTeam(Team team);    
}
