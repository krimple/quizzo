// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.chariot.games.quizzo.model;

import com.chariot.games.quizzo.model.Quiz;
import com.chariot.games.quizzo.model.QuizRun;
import com.chariot.games.quizzo.model.Team;
import java.util.Set;

privileged aspect QuizRun_Roo_JavaBean {
    
    public Quiz QuizRun.getQuiz() {
        return this.quiz;
    }
    
    public void QuizRun.setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
    
    public Set<Team> QuizRun.getTeams() {
        return this.teams;
    }
    
    public void QuizRun.setTeams(Set<Team> teams) {
        this.teams = teams;
    }
    
    public String QuizRun.getText() {
        return this.text;
    }
    
    public void QuizRun.setText(String text) {
        this.text = text;
    }
    
}
