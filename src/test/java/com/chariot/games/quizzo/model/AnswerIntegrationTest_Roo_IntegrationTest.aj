// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.chariot.games.quizzo.model;

import com.chariot.games.quizzo.db.AnswerRepository;
import com.chariot.games.quizzo.model.AnswerDataOnDemand;
import com.chariot.games.quizzo.model.AnswerIntegrationTest;
import com.chariot.games.quizzo.service.AnswerService;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

privileged aspect AnswerIntegrationTest_Roo_IntegrationTest {
    
    declare @type: AnswerIntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    declare @type: AnswerIntegrationTest: @ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext*.xml");
    
    declare @type: AnswerIntegrationTest: @Transactional;
    
    @Autowired
    private AnswerDataOnDemand AnswerIntegrationTest.dod;
    
    @Autowired
    AnswerService AnswerIntegrationTest.answerService;
    
    @Autowired
    AnswerRepository AnswerIntegrationTest.answerRepository;
    
    @Test
    public void AnswerIntegrationTest.testCountAllAnswers() {
        Assert.assertNotNull("Data on demand for 'Answer' failed to initialize correctly", dod.getRandomAnswer());
        long count = answerService.countAllAnswers();
        Assert.assertTrue("Counter for 'Answer' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void AnswerIntegrationTest.testFindAnswer() {
        Answer obj = dod.getRandomAnswer();
        Assert.assertNotNull("Data on demand for 'Answer' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Answer' failed to provide an identifier", id);
        obj = answerService.findAnswer(id);
        Assert.assertNotNull("Find method for 'Answer' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'Answer' returned the incorrect identifier", id, obj.getId());
    }
    
    @Test
    public void AnswerIntegrationTest.testFindAllAnswers() {
        Assert.assertNotNull("Data on demand for 'Answer' failed to initialize correctly", dod.getRandomAnswer());
        long count = answerService.countAllAnswers();
        Assert.assertTrue("Too expensive to perform a find all test for 'Answer', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<Answer> result = answerService.findAllAnswers();
        Assert.assertNotNull("Find all method for 'Answer' illegally returned null", result);
        Assert.assertTrue("Find all method for 'Answer' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void AnswerIntegrationTest.testFindAnswerEntries() {
        Assert.assertNotNull("Data on demand for 'Answer' failed to initialize correctly", dod.getRandomAnswer());
        long count = answerService.countAllAnswers();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<Answer> result = answerService.findAnswerEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'Answer' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'Answer' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    public void AnswerIntegrationTest.testFlush() {
        Answer obj = dod.getRandomAnswer();
        Assert.assertNotNull("Data on demand for 'Answer' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Answer' failed to provide an identifier", id);
        obj = answerService.findAnswer(id);
        Assert.assertNotNull("Find method for 'Answer' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyAnswer(obj);
        Integer currentVersion = obj.getVersion();
        answerRepository.flush();
        Assert.assertTrue("Version for 'Answer' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void AnswerIntegrationTest.testUpdateAnswerUpdate() {
        Answer obj = dod.getRandomAnswer();
        Assert.assertNotNull("Data on demand for 'Answer' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Answer' failed to provide an identifier", id);
        obj = answerService.findAnswer(id);
        boolean modified =  dod.modifyAnswer(obj);
        Integer currentVersion = obj.getVersion();
        Answer merged = answerService.updateAnswer(obj);
        answerRepository.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'Answer' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void AnswerIntegrationTest.testSaveAnswer() {
        Assert.assertNotNull("Data on demand for 'Answer' failed to initialize correctly", dod.getRandomAnswer());
        Answer obj = dod.getNewTransientAnswer(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'Answer' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'Answer' identifier to be null", obj.getId());
        answerService.saveAnswer(obj);
        answerRepository.flush();
        Assert.assertNotNull("Expected 'Answer' identifier to no longer be null", obj.getId());
    }
    
    @Test
    public void AnswerIntegrationTest.testDeleteAnswer() {
        Answer obj = dod.getRandomAnswer();
        Assert.assertNotNull("Data on demand for 'Answer' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Answer' failed to provide an identifier", id);
        obj = answerService.findAnswer(id);
        answerService.deleteAnswer(obj);
        answerRepository.flush();
        Assert.assertNull("Failed to remove 'Answer' with identifier '" + id + "'", answerService.findAnswer(id));
    }
    
}
