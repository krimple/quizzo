// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.chariot.games.quizzo.model;

import com.chariot.games.quizzo.model.Question;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

privileged aspect Question_Roo_Jpa_Entity {
    
    declare @type: Question: @Entity;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long Question.id;
    
    @Version
    @Column(name = "version")
    private Integer Question.version;
    
    public Long Question.getId() {
        return this.id;
    }
    
    public void Question.setId(Long id) {
        this.id = id;
    }
    
    public Integer Question.getVersion() {
        return this.version;
    }
    
    public void Question.setVersion(Integer version) {
        this.version = version;
    }
    
}
