// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.chariot.games.quizzo.model;

import com.chariot.games.quizzo.model.AnswerByChoice;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

privileged aspect AnswerByChoice_Roo_Jpa_Entity {
    
    declare @type: AnswerByChoice: @Entity;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long AnswerByChoice.id;
    
    @Version
    @Column(name = "version")
    private Integer AnswerByChoice.version;
    
    public Long AnswerByChoice.getId() {
        return this.id;
    }
    
    public void AnswerByChoice.setId(Long id) {
        this.id = id;
    }
    
    public Integer AnswerByChoice.getVersion() {
        return this.version;
    }
    
    public void AnswerByChoice.setVersion(Integer version) {
        this.version = version;
    }
    
}