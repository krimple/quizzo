// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.chariot.games.quizzo.db;

import com.chariot.games.quizzo.db.QuizRepository;
import com.chariot.games.quizzo.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

privileged aspect QuizRepository_Roo_Jpa_Repository {
    
    declare parents: QuizRepository extends JpaRepository<Quiz, Long>;
    
    declare parents: QuizRepository extends JpaSpecificationExecutor<Quiz>;
    
    declare @type: QuizRepository: @Repository;
    
}
