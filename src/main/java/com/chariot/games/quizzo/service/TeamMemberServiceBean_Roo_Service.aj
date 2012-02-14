// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.chariot.games.quizzo.service;

import com.chariot.games.quizzo.db.TeamMemberRepository;
import com.chariot.games.quizzo.model.TeamMember;
import com.chariot.games.quizzo.service.TeamMemberServiceBean;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

privileged aspect TeamMemberServiceBean_Roo_Service {
    
    declare @type: TeamMemberServiceBean: @Service;
    
    declare @type: TeamMemberServiceBean: @Transactional;
    
    @Autowired
    TeamMemberRepository TeamMemberServiceBean.teamMemberRepository;
    
    public long TeamMemberServiceBean.countAllTeamMembers() {
        return teamMemberRepository.count();
    }
    
    public void TeamMemberServiceBean.deleteTeamMember(TeamMember teamMember) {
        teamMemberRepository.delete(teamMember);
    }
    
    public TeamMember TeamMemberServiceBean.findTeamMember(Long id) {
        return teamMemberRepository.findOne(id);
    }
    
    public List<TeamMember> TeamMemberServiceBean.findAllTeamMembers() {
        return teamMemberRepository.findAll();
    }
    
    public List<TeamMember> TeamMemberServiceBean.findTeamMemberEntries(int firstResult, int maxResults) {
        return teamMemberRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }
    
    public void TeamMemberServiceBean.saveTeamMember(TeamMember teamMember) {
        teamMemberRepository.save(teamMember);
    }
    
    public TeamMember TeamMemberServiceBean.updateTeamMember(TeamMember teamMember) {
        return teamMemberRepository.save(teamMember);
    }
    
}
