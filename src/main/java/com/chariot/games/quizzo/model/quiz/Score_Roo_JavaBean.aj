// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.chariot.games.quizzo.model.quiz;

import com.chariot.games.quizzo.model.quiz.Score;
import java.math.BigDecimal;

privileged aspect Score_Roo_JavaBean {
    
    public BigDecimal Score.getValue() {
        return this.value;
    }
    
    public void Score.setValue(BigDecimal value) {
        this.value = value;
    }
    
}
