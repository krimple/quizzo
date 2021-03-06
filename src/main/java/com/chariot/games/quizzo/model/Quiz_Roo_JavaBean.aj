// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.chariot.games.quizzo.model;

import com.chariot.games.quizzo.model.Question;
import com.chariot.games.quizzo.model.Quiz;
import com.chariot.games.quizzo.model.QuizRun;
import java.math.BigDecimal;
import java.util.Set;

privileged aspect Quiz_Roo_JavaBean {
    
    public String Quiz.getTitle() {
        return this.title;
    }
    
    public void Quiz.setTitle(String title) {
        this.title = title;
    }
    
    public String Quiz.getDescription() {
        return this.description;
    }
    
    public void Quiz.setDescription(String description) {
        this.description = description;
    }
    
    public BigDecimal Quiz.getDefaultPointValue() {
        return this.defaultPointValue;
    }
    
    public void Quiz.setDefaultPointValue(BigDecimal defaultPointValue) {
        this.defaultPointValue = defaultPointValue;
    }
    
    public Set<Question> Quiz.getQuestions() {
        return this.questions;
    }
    
    public void Quiz.setQuestions(Set<Question> questions) {
        this.questions = questions;
    }
    
    public Set<QuizRun> Quiz.getQuizRuns() {
        return this.quizRuns;
    }
    
    public void Quiz.setQuizRuns(Set<QuizRun> quizRuns) {
        this.quizRuns = quizRuns;
    }
    
}
