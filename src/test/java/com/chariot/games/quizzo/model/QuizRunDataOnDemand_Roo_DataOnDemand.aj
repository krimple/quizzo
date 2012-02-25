// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.chariot.games.quizzo.model;

import com.chariot.games.quizzo.db.QuizRunRepository;
import com.chariot.games.quizzo.model.Quiz;
import com.chariot.games.quizzo.model.QuizDataOnDemand;
import com.chariot.games.quizzo.model.QuizRun;
import com.chariot.games.quizzo.model.QuizRunDataOnDemand;
import com.chariot.games.quizzo.model.QuizRunState;
import com.chariot.games.quizzo.service.QuizRunService;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

privileged aspect QuizRunDataOnDemand_Roo_DataOnDemand {
    
    declare @type: QuizRunDataOnDemand: @Component;
    
    private Random QuizRunDataOnDemand.rnd = new SecureRandom();
    
    private List<QuizRun> QuizRunDataOnDemand.data;
    
    @Autowired
    private QuizDataOnDemand QuizRunDataOnDemand.quizDataOnDemand;
    
    @Autowired
    QuizRunService QuizRunDataOnDemand.quizRunService;
    
    @Autowired
    QuizRunRepository QuizRunDataOnDemand.quizRunRepository;
    
    public QuizRun QuizRunDataOnDemand.getNewTransientQuizRun(int index) {
        QuizRun obj = new QuizRun();
        setQuiz(obj, index);
        setRunState(obj, index);
        setText(obj, index);
        return obj;
    }
    
    public void QuizRunDataOnDemand.setQuiz(QuizRun obj, int index) {
        Quiz quiz = quizDataOnDemand.getRandomQuiz();
        obj.setQuiz(quiz);
    }
    
    public void QuizRunDataOnDemand.setRunState(QuizRun obj, int index) {
        QuizRunState runState = QuizRunState.class.getEnumConstants()[0];
        obj.setRunState(runState);
    }
    
    public void QuizRunDataOnDemand.setText(QuizRun obj, int index) {
        String text = "text_" + index;
        if (text.length() > 300) {
            text = text.substring(0, 300);
        }
        obj.setText(text);
    }
    
    public QuizRun QuizRunDataOnDemand.getSpecificQuizRun(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        QuizRun obj = data.get(index);
        Long id = obj.getId();
        return quizRunService.findQuizRun(id);
    }
    
    public QuizRun QuizRunDataOnDemand.getRandomQuizRun() {
        init();
        QuizRun obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return quizRunService.findQuizRun(id);
    }
    
    public boolean QuizRunDataOnDemand.modifyQuizRun(QuizRun obj) {
        return false;
    }
    
    public void QuizRunDataOnDemand.init() {
        int from = 0;
        int to = 10;
        data = quizRunService.findQuizRunEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'QuizRun' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<QuizRun>();
        for (int i = 0; i < 10; i++) {
            QuizRun obj = getNewTransientQuizRun(i);
            try {
                quizRunService.saveQuizRun(obj);
            } catch (ConstraintViolationException e) {
                StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getConstraintDescriptor()).append(":").append(cv.getMessage()).append("=").append(cv.getInvalidValue()).append("]");
                }
                throw new RuntimeException(msg.toString(), e);
            }
            quizRunRepository.flush();
            data.add(obj);
        }
    }
    
}
