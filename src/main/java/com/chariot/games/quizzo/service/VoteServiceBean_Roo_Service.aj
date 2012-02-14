// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.chariot.games.quizzo.service;

import com.chariot.games.quizzo.db.VoteRepository;
import com.chariot.games.quizzo.model.Vote;
import com.chariot.games.quizzo.service.VoteServiceBean;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

privileged aspect VoteServiceBean_Roo_Service {
    
    declare @type: VoteServiceBean: @Service;
    
    declare @type: VoteServiceBean: @Transactional;
    
    @Autowired
    VoteRepository VoteServiceBean.voteRepository;
    
    public long VoteServiceBean.countAllVotes() {
        return voteRepository.count();
    }
    
    public void VoteServiceBean.deleteVote(Vote vote) {
        voteRepository.delete(vote);
    }
    
    public Vote VoteServiceBean.findVote(Long id) {
        return voteRepository.findOne(id);
    }
    
    public List<Vote> VoteServiceBean.findAllVotes() {
        return voteRepository.findAll();
    }
    
    public List<Vote> VoteServiceBean.findVoteEntries(int firstResult, int maxResults) {
        return voteRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }
    
    public void VoteServiceBean.saveVote(Vote vote) {
        voteRepository.save(vote);
    }
    
    public Vote VoteServiceBean.updateVote(Vote vote) {
        return voteRepository.save(vote);
    }
    
}
