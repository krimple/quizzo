// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.chariot.games.quizzo.model;

import com.chariot.games.quizzo.model.Choice;
import com.chariot.games.quizzo.model.Question;

privileged aspect Choice_Roo_JavaBean {
    
    public String Choice.getText() {
        return this.text;
    }
    
    public void Choice.setText(String text) {
        this.text = text;
    }
    
    public Boolean Choice.getCorrect() {
        return this.correct;
    }
    
    public void Choice.setCorrect(Boolean correct) {
        this.correct = correct;
    }
    
    public Question Choice.getQuestion() {
        return this.question;
    }
    
    public void Choice.setQuestion(Question question) {
        this.question = question;
    }
    
}
