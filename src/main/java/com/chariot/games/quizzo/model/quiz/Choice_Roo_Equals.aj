// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.chariot.games.quizzo.model.quiz;

import com.chariot.games.quizzo.model.quiz.Choice;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

privileged aspect Choice_Roo_Equals {
    
    public boolean Choice.equals(Object obj) {
        if (!(obj instanceof Choice)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        Choice rhs = (Choice) obj;
        return new EqualsBuilder().append(answerText, rhs.answerText).append(isCorrect, rhs.isCorrect).isEquals();
    }
    
    public int Choice.hashCode() {
        return new HashCodeBuilder().append(answerText).append(isCorrect).toHashCode();
    }
    
}
