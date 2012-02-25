// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.chariot.games.quizzo.model;

import com.chariot.games.quizzo.db.QuestionRepository;
import com.chariot.games.quizzo.model.Question;
import com.chariot.games.quizzo.model.QuestionDataOnDemand;
import com.chariot.games.quizzo.model.Quiz;
import com.chariot.games.quizzo.service.QuestionService;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

privileged aspect QuestionDataOnDemand_Roo_DataOnDemand {
    
    declare @type: QuestionDataOnDemand: @Component;
    
    private Random QuestionDataOnDemand.rnd = new SecureRandom();
    
    private List<Question> QuestionDataOnDemand.data;
    
    @Autowired
    QuestionService QuestionDataOnDemand.questionService;
    
    @Autowired
    QuestionRepository QuestionDataOnDemand.questionRepository;
    
    public Question QuestionDataOnDemand.getNewTransientQuestion(int index) {
        Question obj = new Question();
        setQuiz(obj, index);
        setSortOrder(obj, index);
        setText(obj, index);
        return obj;
    }
    
    public void QuestionDataOnDemand.setQuiz(Question obj, int index) {
        Quiz quiz = null;
        obj.setQuiz(quiz);
    }
    
    public void QuestionDataOnDemand.setSortOrder(Question obj, int index) {
        short sortOrder = new Integer(index).shortValue();
        obj.setSortOrder(sortOrder);
    }
    
    public void QuestionDataOnDemand.setText(Question obj, int index) {
        String text = "text_" + index;
        if (text.length() > 300) {
            text = text.substring(0, 300);
        }
        obj.setText(text);
    }
    
    public Question QuestionDataOnDemand.getSpecificQuestion(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        Question obj = data.get(index);
        Long id = obj.getId();
        return questionService.findQuestion(id);
    }
    
    public Question QuestionDataOnDemand.getRandomQuestion() {
        init();
        Question obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return questionService.findQuestion(id);
    }
    
    public boolean QuestionDataOnDemand.modifyQuestion(Question obj) {
        return false;
    }
    
    public void QuestionDataOnDemand.init() {
        int from = 0;
        int to = 10;
        data = questionService.findQuestionEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'Question' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<Question>();
        for (int i = 0; i < 10; i++) {
            Question obj = getNewTransientQuestion(i);
            try {
                questionService.saveQuestion(obj);
            } catch (ConstraintViolationException e) {
                StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getConstraintDescriptor()).append(":").append(cv.getMessage()).append("=").append(cv.getInvalidValue()).append("]");
                }
                throw new RuntimeException(msg.toString(), e);
            }
            questionRepository.flush();
            data.add(obj);
        }
    }
    
}
