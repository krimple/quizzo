// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.chariot.games.quizzo.model.quiz;

privileged aspect Choice_Roo_JavaBean {
    
    public boolean Choice.isIsCorrect() {
        return this.isCorrect;
    }
    
    public void Choice.setIsCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }
    
    public String Choice.getAnswerText() {
        return this.answerText;
    }
    
    public void Choice.setAnswerText(String answerText) {
        this.answerText = answerText;
    }
    
}
