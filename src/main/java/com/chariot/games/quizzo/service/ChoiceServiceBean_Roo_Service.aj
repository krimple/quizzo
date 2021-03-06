// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.chariot.games.quizzo.service;

import com.chariot.games.quizzo.db.ChoiceRepository;
import com.chariot.games.quizzo.model.Choice;
import com.chariot.games.quizzo.service.ChoiceServiceBean;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

privileged aspect ChoiceServiceBean_Roo_Service {
    
    declare @type: ChoiceServiceBean: @Service;
    
    declare @type: ChoiceServiceBean: @Transactional;
    
    @Autowired
    ChoiceRepository ChoiceServiceBean.choiceRepository;
    
    public long ChoiceServiceBean.countAllChoices() {
        return choiceRepository.count();
    }
    
    public void ChoiceServiceBean.deleteChoice(Choice choice) {
        choiceRepository.delete(choice);
    }
    
    public Choice ChoiceServiceBean.findChoice(Long id) {
        return choiceRepository.findOne(id);
    }
    
    public List<Choice> ChoiceServiceBean.findAllChoices() {
        return choiceRepository.findAll();
    }
    
    public List<Choice> ChoiceServiceBean.findChoiceEntries(int firstResult, int maxResults) {
        return choiceRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }
    
    public void ChoiceServiceBean.saveChoice(Choice choice) {
        choiceRepository.save(choice);
    }
    
    public Choice ChoiceServiceBean.updateChoice(Choice choice) {
        return choiceRepository.save(choice);
    }
    
}
