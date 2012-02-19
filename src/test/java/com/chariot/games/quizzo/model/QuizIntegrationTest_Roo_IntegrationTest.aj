// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.chariot.games.quizzo.model;

import com.chariot.games.quizzo.model.Quiz;
import com.chariot.games.quizzo.model.QuizDataOnDemand;
import com.chariot.games.quizzo.model.QuizIntegrationTest;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

privileged aspect QuizIntegrationTest_Roo_IntegrationTest {
    
    declare @type: QuizIntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    declare @type: QuizIntegrationTest: @ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext*.xml");
    
    declare @type: QuizIntegrationTest: @Transactional;
    
    @Autowired
    private QuizDataOnDemand QuizIntegrationTest.dod;
    
    @Test
    public void QuizIntegrationTest.testCountQuizes() {
        Assert.assertNotNull("Data on demand for 'Quiz' failed to initialize correctly", dod.getRandomQuiz());
        long count = Quiz.countQuizes();
        Assert.assertTrue("Counter for 'Quiz' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void QuizIntegrationTest.testFindQuiz() {
        Quiz obj = dod.getRandomQuiz();
        Assert.assertNotNull("Data on demand for 'Quiz' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Quiz' failed to provide an identifier", id);
        obj = Quiz.findQuiz(id);
        Assert.assertNotNull("Find method for 'Quiz' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'Quiz' returned the incorrect identifier", id, obj.getId());
    }
    
    @Test
    public void QuizIntegrationTest.testFindAllQuizes() {
        Assert.assertNotNull("Data on demand for 'Quiz' failed to initialize correctly", dod.getRandomQuiz());
        long count = Quiz.countQuizes();
        Assert.assertTrue("Too expensive to perform a find all test for 'Quiz', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<Quiz> result = Quiz.findAllQuizes();
        Assert.assertNotNull("Find all method for 'Quiz' illegally returned null", result);
        Assert.assertTrue("Find all method for 'Quiz' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void QuizIntegrationTest.testFindQuizEntries() {
        Assert.assertNotNull("Data on demand for 'Quiz' failed to initialize correctly", dod.getRandomQuiz());
        long count = Quiz.countQuizes();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<Quiz> result = Quiz.findQuizEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'Quiz' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'Quiz' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    public void QuizIntegrationTest.testFlush() {
        Quiz obj = dod.getRandomQuiz();
        Assert.assertNotNull("Data on demand for 'Quiz' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Quiz' failed to provide an identifier", id);
        obj = Quiz.findQuiz(id);
        Assert.assertNotNull("Find method for 'Quiz' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyQuiz(obj);
        Integer currentVersion = obj.getVersion();
        obj.flush();
        Assert.assertTrue("Version for 'Quiz' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void QuizIntegrationTest.testMergeUpdate() {
        Quiz obj = dod.getRandomQuiz();
        Assert.assertNotNull("Data on demand for 'Quiz' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Quiz' failed to provide an identifier", id);
        obj = Quiz.findQuiz(id);
        boolean modified =  dod.modifyQuiz(obj);
        Integer currentVersion = obj.getVersion();
        Quiz merged = obj.merge();
        obj.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'Quiz' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void QuizIntegrationTest.testPersist() {
        Assert.assertNotNull("Data on demand for 'Quiz' failed to initialize correctly", dod.getRandomQuiz());
        Quiz obj = dod.getNewTransientQuiz(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'Quiz' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'Quiz' identifier to be null", obj.getId());
        obj.persist();
        obj.flush();
        Assert.assertNotNull("Expected 'Quiz' identifier to no longer be null", obj.getId());
    }
    
    @Test
    public void QuizIntegrationTest.testRemove() {
        Quiz obj = dod.getRandomQuiz();
        Assert.assertNotNull("Data on demand for 'Quiz' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Quiz' failed to provide an identifier", id);
        obj = Quiz.findQuiz(id);
        obj.remove();
        obj.flush();
        Assert.assertNull("Failed to remove 'Quiz' with identifier '" + id + "'", Quiz.findQuiz(id));
    }
    
}