// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.chariot.games.quizzo.web.ajax.response;

import com.chariot.games.quizzo.web.ajax.response.QuestionChoiceResponse;
import com.chariot.games.quizzo.web.ajax.response.QuestionResponse;
import java.util.List;

privileged aspect QuestionResponse_Roo_JavaBean {
    
    public int QuestionResponse.getQuestionNumber() {
        return this.questionNumber;
    }
    
    public void QuestionResponse.setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }
    
    public String QuestionResponse.getText() {
        return this.text;
    }
    
    public void QuestionResponse.setText(String text) {
        this.text = text;
    }
    
    public List<QuestionChoiceResponse> QuestionResponse.getResponses() {
        return this.responses;
    }
    
    public void QuestionResponse.setResponses(List<QuestionChoiceResponse> responses) {
        this.responses = responses;
    }
    
}