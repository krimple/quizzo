// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.chariot.games.quizzo.model;

import com.chariot.games.quizzo.db.TeamRepository;
import com.chariot.games.quizzo.model.TeamDataOnDemand;
import com.chariot.games.quizzo.model.TeamIntegrationTest;
import com.chariot.games.quizzo.service.TeamService;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

privileged aspect TeamIntegrationTest_Roo_IntegrationTest {
    
    declare @type: TeamIntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    declare @type: TeamIntegrationTest: @ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext*.xml");
    
    declare @type: TeamIntegrationTest: @Transactional;
    
    @Autowired
    private TeamDataOnDemand TeamIntegrationTest.dod;
    
    @Autowired
    TeamService TeamIntegrationTest.teamService;
    
    @Autowired
    TeamRepository TeamIntegrationTest.teamRepository;
    
    @Test
    public void TeamIntegrationTest.testCountAllTeams() {
        Assert.assertNotNull("Data on demand for 'Team' failed to initialize correctly", dod.getRandomTeam());
        long count = teamService.countAllTeams();
        Assert.assertTrue("Counter for 'Team' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void TeamIntegrationTest.testFindTeam() {
        Team obj = dod.getRandomTeam();
        Assert.assertNotNull("Data on demand for 'Team' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Team' failed to provide an identifier", id);
        obj = teamService.findTeam(id);
        Assert.assertNotNull("Find method for 'Team' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'Team' returned the incorrect identifier", id, obj.getId());
    }
    
    @Test
    public void TeamIntegrationTest.testFindAllTeams() {
        Assert.assertNotNull("Data on demand for 'Team' failed to initialize correctly", dod.getRandomTeam());
        long count = teamService.countAllTeams();
        Assert.assertTrue("Too expensive to perform a find all test for 'Team', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<Team> result = teamService.findAllTeams();
        Assert.assertNotNull("Find all method for 'Team' illegally returned null", result);
        Assert.assertTrue("Find all method for 'Team' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void TeamIntegrationTest.testFindTeamEntries() {
        Assert.assertNotNull("Data on demand for 'Team' failed to initialize correctly", dod.getRandomTeam());
        long count = teamService.countAllTeams();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<Team> result = teamService.findTeamEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'Team' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'Team' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    public void TeamIntegrationTest.testFlush() {
        Team obj = dod.getRandomTeam();
        Assert.assertNotNull("Data on demand for 'Team' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Team' failed to provide an identifier", id);
        obj = teamService.findTeam(id);
        Assert.assertNotNull("Find method for 'Team' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyTeam(obj);
        Integer currentVersion = obj.getVersion();
        teamRepository.flush();
        Assert.assertTrue("Version for 'Team' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void TeamIntegrationTest.testUpdateTeamUpdate() {
        Team obj = dod.getRandomTeam();
        Assert.assertNotNull("Data on demand for 'Team' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Team' failed to provide an identifier", id);
        obj = teamService.findTeam(id);
        boolean modified =  dod.modifyTeam(obj);
        Integer currentVersion = obj.getVersion();
        Team merged = teamService.updateTeam(obj);
        teamRepository.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'Team' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void TeamIntegrationTest.testSaveTeam() {
        Assert.assertNotNull("Data on demand for 'Team' failed to initialize correctly", dod.getRandomTeam());
        Team obj = dod.getNewTransientTeam(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'Team' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'Team' identifier to be null", obj.getId());
        teamService.saveTeam(obj);
        teamRepository.flush();
        Assert.assertNotNull("Expected 'Team' identifier to no longer be null", obj.getId());
    }
    
    @Test
    public void TeamIntegrationTest.testDeleteTeam() {
        Team obj = dod.getRandomTeam();
        Assert.assertNotNull("Data on demand for 'Team' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Team' failed to provide an identifier", id);
        obj = teamService.findTeam(id);
        teamService.deleteTeam(obj);
        teamRepository.flush();
        Assert.assertNull("Failed to remove 'Team' with identifier '" + id + "'", teamService.findTeam(id));
    }
    
}
