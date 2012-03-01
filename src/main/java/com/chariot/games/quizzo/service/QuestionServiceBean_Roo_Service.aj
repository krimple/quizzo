// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.chariot.games.quizzo.service;

import com.chariot.games.quizzo.db.QuestionRepository;
import com.chariot.games.quizzo.model.Question;
import com.chariot.games.quizzo.service.QuestionServiceBean;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

privileged aspect QuestionServiceBean_Roo_Service {
    
    declare @type: QuestionServiceBean: @Service;
    
    declare @type: QuestionServiceBean: @Transactional;
    
    @Autowired
    QuestionRepository QuestionServiceBean.questionRepository;
    
    public long QuestionServiceBean.countAllQuestions() {
        return questionRepository.count();
    }
    
    public void QuestionServiceBean.deleteQuestion(Question question) {
        questionRepository.delete(question);
    }
    
    public Question QuestionServiceBean.findQuestion(Long id) {
        return questionRepository.findOne(id);
    }
    
    public List<Question> QuestionServiceBean.findAllQuestions() {
        return questionRepository.findAll();
    }
    
    public List<Question> QuestionServiceBean.findQuestionEntries(int firstResult, int maxResults) {
        return questionRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }
    
    public void QuestionServiceBean.saveQuestion(Question question) {
        questionRepository.save(question);
    }
    
    public Question QuestionServiceBean.updateQuestion(Question question) {
        return questionRepository.save(question);
    }
    
}