// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.chariot.games.quizzo.web.flow;

import com.chariot.games.quizzo.web.flow.VotingForm;

privileged aspect VotingForm_Roo_JavaBean {
    
    public Long VotingForm.getQuestionId() {
        return this.questionId;
    }
    
    public void VotingForm.setQuestionId(Long questionId) {
        this.questionId = questionId;
    }
    
    public Long VotingForm.getChoiceId() {
        return this.choiceId;
    }
    
    public void VotingForm.setChoiceId(Long choiceId) {
        this.choiceId = choiceId;
    }
    
    public Boolean VotingForm.getSelected() {
        return this.selected;
    }
    
    public void VotingForm.setSelected(Boolean selected) {
        this.selected = selected;
    }
    
}
