package testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import game.core.Athlete;
import game.core.Team;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TeamTest {
    private Team team;

    @BeforeEach
    public void setUp() {
        team = new Team("Test Team");
        Athlete athlete1 = new Athlete("Athlete 1", 80, 60, 70, "Forward");
        Athlete athlete2 = new Athlete("Athlete 2", 75, 65, 80, "Defender");
        team.addPlayer(athlete1);
        team.addPlayer(athlete2);
    }

    @Test
    public void testAddPlayer() {
        Athlete athlete3 = new Athlete("Athlete 3", 70, 70, 90, "Forward");
        team.addPlayer(athlete3);
        assertEquals(3, team.getNumberOfAthletes());
    }

    @Test
    public void testGetNumberInjured() {
        assertEquals(0, team.getNumberInjured());
        team.getAthletes().get(0).loseStamina(100);
        assertEquals(1, team.getNumberInjured());
    }

    @Test
    public void testFullStamina() {
        team.getAthletes().get(0).loseStamina(30);
        team.fullStamina();
        for (Athlete athlete : team.getAthletes()) {
            assertEquals(100, athlete.getStamina());
        }
    }

    @Test
    public void testChangeTeamStamina() {
    	team = new Team("Test Team");
    	team.addPlayer(new Athlete("Athlete 1", 50, 50, 100, "Defender"));
        team.changeTeamStamina(20);
        for (Athlete athlete : team.getAthletes()) {
            assertEquals(80, athlete.getStamina());
        }
    }

    @Test
    public void testTeamFull() {
        assertFalse(team.teamFull());
        Athlete athlete3 = new Athlete("Athlete 3", 70, 70, 90, "Forward");
        Athlete athlete4 = new Athlete("Athlete 4", 70, 70, 90, "Defender");
        Athlete athlete5 = new Athlete("Athlete 5", 70, 70, 90, "Forward");
        team.addPlayer(athlete3);
        team.addPlayer(athlete4);
        team.addPlayer(athlete5);
        assertTrue(team.teamFull());
    }

    @Test
    public void testTeamNotInjured() {
        assertTrue(team.teamNotInjured());
        team.getAthletes().get(0).loseStamina(100);
        assertFalse(team.teamNotInjured());
    }
    
    @Test
    public void testGetActiveAthletes() {
        List<Athlete> activeAthletes = team.getActiveAthletes();
        assertEquals(2, activeAthletes.size());
        team.getAthletes().get(0).loseStamina(100);
        activeAthletes = team.getActiveAthletes();
        assertEquals(1, activeAthletes.size());
    }

    @Test
    public void testGetReserveAthletes() {
        List<Athlete> reserveAthletes = team.getReserveAthletes();
        assertEquals(0, reserveAthletes.size());
        team.getAthletes().get(0).loseStamina(100);
        reserveAthletes = team.getReserveAthletes();
        assertEquals(1, reserveAthletes.size());
    }

    @Test
    public void testGenerateEnemyTeam() {
        Team enemyTeam = team.generateEnemyTeam(1);
        assertNotNull(enemyTeam);
        assertEquals(5, enemyTeam.getNumberOfAthletes());
    }

    @Test
    public void testGenerateTeam() {
        Team newTeam = team.generateTeam();
        assertNotNull(newTeam);
        assertEquals(5, newTeam.getNumberOfAthletes());
    }

    @Test
    public void testPopLastAthlete() {
        Athlete lastAthlete = team.popLastAthlete();
        assertEquals("Athlete 2", lastAthlete.getName());
        assertEquals(1, team.getNumberOfAthletes());
    }

    @Test
    public void testRandomStatBoost() {
        String result = team.randomStatBoost();
        assertNotNull(result);
        assertTrue(result.contains("increased by 20."));
    }

    @Test
    public void testRandomQuit() {
        String result = team.randomQuit();
        assertNotNull(result);
        assertTrue(result.contains("has quit the team."));
        assertEquals(1, team.getNumberOfAthletes());
    }

    @Test
    public void testRandomJoin() {
        String result = team.randomJoin();
        assertNotNull(result);
        assertTrue(result.contains("has joined the team."));
        assertEquals(3, team.getNumberOfAthletes());
    }
    
    @Test
    public void testGetForwardAthletes() {
        List<Athlete> forwards = team.getForwardAthletes();
        assertNotNull(forwards);
        // Assume there is at least one "Forward" position player in the team
        assertTrue(forwards.size() > 0);
        assertEquals("Forward", forwards.get(0).getPosition());
    }

    @Test
    public void testGetDefenceAthletes() {
        List<Athlete> defenders = team.getDefenceAthletes();
        assertNotNull(defenders);
        // Assume there is at least one "Defender" position player in the team
        assertTrue(defenders.size() > 0);
        assertEquals("Defender", defenders.get(0).getPosition());
    }
    
    @Test
    public void testCompareTeamsUserWin() {
    	
    	
    	Team userTeam = new Team("User");
        userTeam.addPlayer(new Athlete("User Athlete 1", 90, 90, 90, "Forward"));
        userTeam.addPlayer(new Athlete("User Athlete 2", 90, 90, 90, "Forward"));
        userTeam.addPlayer(new Athlete("User Athlete 3", 90, 90, 90, "Defender"));
        userTeam.addPlayer(new Athlete("User Athlete 4", 90, 90, 90, "Defender"));
        userTeam.addPlayer(new Athlete("User Athlete 5", 90, 90, 90, "Defender"));
        
        Team uTeam = new Team("main");
        uTeam.addPlayer(new Athlete("Athlete 1", 50, 50, 50, "Forward"));
        uTeam.addPlayer(new Athlete("Athlete 2", 50, 50, 50, "Forward"));
        uTeam.addPlayer(new Athlete("Athlete 3", 50, 50, 50, "Defender"));
        uTeam.addPlayer(new Athlete("Athlete 4", 50, 50, 50, "Defender"));
        uTeam.addPlayer(new Athlete("Athlete 5", 50, 50, 50, "Defender"));
    	
    	 String result = userTeam.compareTeams(userTeam, uTeam);
    	 
         assertEquals("Win", result);
    }
    
    @Test
    public void testCompareTeamsStaminaLossForward() {
    	
    	
    	Team userTeam = new Team("User");
        userTeam.addPlayer(new Athlete("User Athlete 1", 90, 90, 90, "Defender"));
        userTeam.addPlayer(new Athlete("User Athlete 2", 90, 90, 90, "Defender"));
        userTeam.addPlayer(new Athlete("User Athlete 3", 10, 10, 10, "Forward"));
        userTeam.addPlayer(new Athlete("User Athlete 4", 10, 10, 10,  "Forward"));
        userTeam.addPlayer(new Athlete("User Athlete 5", 90, 90, 90, "Defender"));
        
        Team uTeam = new Team("main");
        uTeam.addPlayer(new Athlete("Athlete 1", 50, 50, 50, "Forward"));
        uTeam.addPlayer(new Athlete("Athlete 2", 50, 50, 50, "Forward"));
        uTeam.addPlayer(new Athlete("Athlete 3", 50, 50, 50, "Forward"));
        uTeam.addPlayer(new Athlete("Athlete 4", 50, 50, 50, "Defender"));
        uTeam.addPlayer(new Athlete("Athlete 5", 50, 50, 50, "Defender"));
    
    	
    
    	 String result = userTeam.compareTeams(userTeam, uTeam);
    	 
         assertEquals("Win", result);
    }
    
    @Test
    public void testCompareTeamsStaminaLossDefender() {
    	
    	
    	Team userTeam = new Team("User");
        userTeam.addPlayer(new Athlete("User Athlete 1", 90, 90, 90, "Forward"));
        userTeam.addPlayer(new Athlete("User Athlete 2", 10, 10, 10, "Defender"));
        userTeam.addPlayer(new Athlete("User Athlete 3", 10, 10, 10, "Defender"));
        userTeam.addPlayer(new Athlete("User Athlete 4", 90, 90, 90,  "Forward"));
        userTeam.addPlayer(new Athlete("User Athlete 5", 90, 90, 90, "Defender"));
        
        Team uTeam = new Team("main");
        uTeam.addPlayer(new Athlete("Athlete 1", 50, 50, 50, "Forward"));
        uTeam.addPlayer(new Athlete("Athlete 2", 50, 50, 50, "Forward"));
        uTeam.addPlayer(new Athlete("Athlete 3", 50, 50, 50, "Forward"));
        uTeam.addPlayer(new Athlete("Athlete 4", 50, 50, 50, "Defender"));
        uTeam.addPlayer(new Athlete("Athlete 5", 50, 50, 50, "Defender"));
    
    	 String result = userTeam.compareTeams(userTeam, uTeam);
    	 
         assertEquals("Win", result);
    }
    

    
    @Test
    public void testCompareTeamsStaminaLossBoth() {
    	
    	
    	Team userTeam = new Team("User");
        userTeam.addPlayer(new Athlete("User Athlete 1", 20, 20, 20, "Forward"));
        userTeam.addPlayer(new Athlete("User Athlete 2", 90, 90, 90, "Forward"));
        userTeam.addPlayer(new Athlete("User Athlete 3", 90, 90, 90, "Forward"));
        userTeam.addPlayer(new Athlete("User Athlete 4", 90, 90, 90, "Forward"));
        userTeam.addPlayer(new Athlete("User Athlete 5", 20, 20, 20, "Defender"));
        
        Team uTeam = new Team("main");
        uTeam.addPlayer(new Athlete("Athlete 1", 50, 50, 50, "Forward"));
        uTeam.addPlayer(new Athlete("Athlete 2", 50, 50, 50, "Forward"));
        uTeam.addPlayer(new Athlete("Athlete 3", 50, 50, 50, "Defender"));
        uTeam.addPlayer(new Athlete("Athlete 4", 50, 50, 50, "Defender"));
        uTeam.addPlayer(new Athlete("Athlete 5", 50, 50, 50, "Defender"));
    
    	
    
    	 String result = userTeam.compareTeams(userTeam, uTeam);
    	 
         assertEquals("Win", result);
    }
    
    @Test
    public void testCompareTeamsWinWithHeadOnLoss() {
    	
    	
    	Team userTeam = new Team("User");
        userTeam.addPlayer(new Athlete("User Athlete 1", 90, 90, 90, "Defender"));
        userTeam.addPlayer(new Athlete("User Athlete 2", 90, 90, 90, "Defender"));
        userTeam.addPlayer(new Athlete("User Athlete 3", 30, 30, 30, "Forward"));
        userTeam.addPlayer(new Athlete("User Athlete 4", 90, 90, 90, "Forward"));
        userTeam.addPlayer(new Athlete("User Athlete 5", 90, 90, 90, "Defender"));
        
        Team uTeam = new Team("main");
        uTeam.addPlayer(new Athlete("Athlete 1", 50, 50, 50, "Forward"));
        uTeam.addPlayer(new Athlete("Athlete 2", 50, 50, 50, "Forward"));
        uTeam.addPlayer(new Athlete("Athlete 3", 50, 50, 50, "Defender"));
        uTeam.addPlayer(new Athlete("Athlete 4", 50, 50, 50, "Defender"));
        uTeam.addPlayer(new Athlete("Athlete 5", 50, 50, 50, "Defender"));

    	 String result = userTeam.compareTeams(userTeam, uTeam);
    	 
    	 boolean full = uTeam.isTeamFull();
    	 
    	 assertTrue(full);
    	 
         assertEquals("Win", result);
    }
        @Test
        public void testCompareTeamsLose() {
            Team enemyTeam = new Team("Enemy");
            enemyTeam.addPlayer(new Athlete("Athlete 1", 100, 100, 100, "Offence"));
            enemyTeam.addPlayer(new Athlete("Athlete 2", 100, 100, 100, "Offence"));
            enemyTeam.addPlayer(new Athlete("Athlete 3", 100, 100, 100, "Defence"));
            enemyTeam.addPlayer(new Athlete("Athlete 4", 100, 100, 100, "Defence"));
            enemyTeam.addPlayer(new Athlete("Athlete 5", 100, 100, 100, "Defence"));
            
            Team team = new Team("main");
            team.addPlayer(new Athlete("Athlete 1", 50, 50, 50, "Offence"));
            team.addPlayer(new Athlete("Athlete 2", 50, 50, 50, "Offence"));
            team.addPlayer(new Athlete("Athlete 3", 50, 50, 50, "Offence"));
            team.addPlayer(new Athlete("Athlete 4", 50, 50, 50, "Offence"));
            team.addPlayer(new Athlete("Athlete 5", 50, 50, 50, "Offence"));
            
            String result = team.compareTeams(team, enemyTeam);
            assertEquals("Loss", result);
    }
        
        
        @Test
        public void testCompareTeamsLoseWithHeadOnWIn() {
            Team enemyTeam = new Team("Enemy");
            enemyTeam.addPlayer(new Athlete("Athlete 1", 100, 100, 100, "Offence"));
            enemyTeam.addPlayer(new Athlete("Athlete 2", 100, 100, 100, "Offence"));
            enemyTeam.addPlayer(new Athlete("Athlete 3", 100, 100, 100, "Defence"));
            enemyTeam.addPlayer(new Athlete("Athlete 4", 100, 100, 100, "Defence"));
            enemyTeam.addPlayer(new Athlete("Athlete 5", 30, 30, 30,   "Defence"));
            
            Team team = new Team("main");
            team.addPlayer(new Athlete("Athlete 1", 50, 50, 50, "Offence"));
            team.addPlayer(new Athlete("Athlete 2", 50, 50, 50, "Offence"));
            team.addPlayer(new Athlete("Athlete 3", 50, 50, 50, "Offence"));
            team.addPlayer(new Athlete("Athlete 4", 50, 50, 50, "Offence"));
            team.addPlayer(new Athlete("Athlete 5", 50, 50, 50, "Offence"));
            
            String result = team.compareTeams(team, enemyTeam);
            assertEquals("Loss", result);
    }
        
    @Test void testStrings() {
    	Team team = new Team("main");
        team.addPlayer(new Athlete("Athlete 1", 50, 50, 50, "Offence"));
        team.addPlayer(new Athlete("Athlete 2", 50, 50, 50, "Offence"));
        team.addPlayer(new Athlete("Athlete 3", 50, 50, 50, "Offence"));
        team.addPlayer(new Athlete("Athlete 4", 50, 50, 60, "Offence"));
        team.addPlayer(new Athlete("Athlete 5", 50, 50, 50, "Offence"));
        
        String toString = team.toString();
        
        assertTrue(
                toString.contains("main") &&
                toString.contains("Athlete 1") &&
                toString.contains("Athlete 5") &&
                toString.contains("60"));

        String toDisplay = team.toDisplay();

        // Assert that toDisplay contains "main" and the names of all athletes
        assertTrue(
                toDisplay.contains("main") &&
                toDisplay.contains("Athlete 1") &&
                toDisplay.contains("Athlete 2") &&
                toDisplay.contains("Athlete 3") &&
                toDisplay.contains("Athlete 4") &&
                toDisplay.contains("Athlete 5"));
        
        
        String showTeam = team.showTeam();
        
        assertTrue(
        		showTeam .contains("Athlete 1") &&
        		showTeam .contains("Athlete 2") &&
        		showTeam .contains("60") &&
        		showTeam .contains("Athlete 5"));

        
    }
    

    	
    	
    

    @Test
    public void testGenerateEnemyTeamName() {
        String name = team.generateEnemyTeamName();
        assertNotNull(name);
        assertTrue(name.length() > 0);
    }
}

