// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.chariot.games.quizzo.model;

import com.chariot.games.quizzo.db.ChoiceRepository;
import com.chariot.games.quizzo.model.Choice;
import com.chariot.games.quizzo.model.ChoiceDataOnDemand;
import com.chariot.games.quizzo.model.Question;
import com.chariot.games.quizzo.model.QuestionDataOnDemand;
import com.chariot.games.quizzo.service.ChoiceService;
import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

privileged aspect ChoiceDataOnDemand_Roo_DataOnDemand {
    
    declare @type: ChoiceDataOnDemand: @Component;
    
    private Random ChoiceDataOnDemand.rnd = new SecureRandom();
    
    private List<Choice> ChoiceDataOnDemand.data;
    
    @Autowired
    private QuestionDataOnDemand ChoiceDataOnDemand.questionDataOnDemand;
    
    @Autowired
    ChoiceService ChoiceDataOnDemand.choiceService;
    
    @Autowired
    ChoiceRepository ChoiceDataOnDemand.choiceRepository;
    
    public Choice ChoiceDataOnDemand.getNewTransientChoice(int index) {
        Choice obj = new Choice();
        setCorrect(obj, index);
        setPointValue(obj, index);
        setQuestion(obj, index);
        setSortOrder(obj, index);
        setText(obj, index);
        return obj;
    }
    
    public void ChoiceDataOnDemand.setCorrect(Choice obj, int index) {
        Boolean correct = Boolean.TRUE;
        obj.setCorrect(correct);
    }
    
    public void ChoiceDataOnDemand.setPointValue(Choice obj, int index) {
        BigDecimal pointValue = BigDecimal.valueOf(index);
        if (pointValue.compareTo(new BigDecimal("-1000")) == -1 || pointValue.compareTo(new BigDecimal("1000")) == 1) {
            pointValue = new BigDecimal("1000");
        }
        obj.setPointValue(pointValue);
    }
    
    public void ChoiceDataOnDemand.setQuestion(Choice obj, int index) {
        Question question = questionDataOnDemand.getRandomQuestion();
        obj.setQuestion(question);
    }
    
    public void ChoiceDataOnDemand.setSortOrder(Choice obj, int index) {
        short sortOrder = new Integer(index).shortValue();
        obj.setSortOrder(sortOrder);
    }
    
    public void ChoiceDataOnDemand.setText(Choice obj, int index) {
        String text = "text_" + index;
        if (text.length() > 300) {
            text = text.substring(0, 300);
        }
        obj.setText(text);
    }
    
    public Choice ChoiceDataOnDemand.getSpecificChoice(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        Choice obj = data.get(index);
        Long id = obj.getId();
        return choiceService.findChoice(id);
    }
    
    public Choice ChoiceDataOnDemand.getRandomChoice() {
        init();
        Choice obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return choiceService.findChoice(id);
    }
    
    public boolean ChoiceDataOnDemand.modifyChoice(Choice obj) {
        return false;
    }
    
    public void ChoiceDataOnDemand.init() {
        int from = 0;
        int to = 10;
        data = choiceService.findChoiceEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'Choice' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<Choice>();
        for (int i = 0; i < 10; i++) {
            Choice obj = getNewTransientChoice(i);
            try {
                choiceService.saveChoice(obj);
            } catch (ConstraintViolationException e) {
                StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getConstraintDescriptor()).append(":").append(cv.getMessage()).append("=").append(cv.getInvalidValue()).append("]");
                }
                throw new RuntimeException(msg.toString(), e);
            }
            choiceRepository.flush();
            data.add(obj);
        }
    }
    
}
