package testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import game.core.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class GameEnvironmentTest {
    private GameEnvironment gameEnvironment;

    @BeforeEach
    public void setUp() {
        gameEnvironment = new GameEnvironment();
    }

    @Test
    public void testSetTeamName() {
        gameEnvironment.setTeamName("Team1");
        assertEquals("Team1", gameEnvironment.getTeamName());
    }

    @Test
    public void testUpdateEnemyTeams() {
        Team originalEnemyTeam1 = gameEnvironment.getEnemyTeam1();
        gameEnvironment.updateEnemyTeams();
        assertNotSame(originalEnemyTeam1, gameEnvironment.getEnemyTeam1());
    }

    @Test
    public void testPurchaseAthlete() {
        gameEnvironment.getPlayer().setMoney(5000);
        Athlete athlete = gameEnvironment.getRandAthlete();
        boolean result = gameEnvironment.purchase(athlete, 2000, "Main");
        assertTrue(result);
        assertEquals(3000, gameEnvironment.getPlayer().getMoney());
    }

    @Test
    public void testPurchaseAthleteInsufficientFunds() {
        // Assuming we have a method to set player's money
        gameEnvironment.getPlayer().setMoney(1000);
        Athlete athlete = gameEnvironment.getRandAthlete();
        boolean result = gameEnvironment.purchase(athlete, 2000, "Main");
        assertFalse(result);
        assertEquals(1000, gameEnvironment.getPlayer().getMoney());
    }

    @Test
    public void testPurchaseItem() {
        gameEnvironment.getPlayer().setMoney(5000);
        Items item = gameEnvironment.getCurrentMarketItems().get(0);
        boolean result = gameEnvironment.purchase(item, 2000);
        assertTrue(result);
        assertEquals(3000, gameEnvironment.getPlayer().getMoney());
    }

    @Test
    public void testPurchaseItemInsufficientFunds() {
        // Assuming we have a method to set player's money
        gameEnvironment.getPlayer().setMoney(1000);
        Items item = gameEnvironment.getCurrentMarketItems().get(0);
        boolean result = gameEnvironment.purchase(item, 2000);
        assertFalse(result);
        assertEquals(1000, gameEnvironment.getPlayer().getMoney());
    }


    @Test
    public void testUpdateMarketItems() {
        ArrayList<Items> originalMarketItems = gameEnvironment.getCurrentMarketItems();
        gameEnvironment.updateMarketItems();
        assertNotSame(originalMarketItems, gameEnvironment.getCurrentMarketItems());
    }

    @Test
    public void testUpdateRandTeam() {
        Team originalMarketTeam = gameEnvironment.getCurrentRandTeam();
        gameEnvironment.updateRandTeam();
        assertNotSame(originalMarketTeam, gameEnvironment.getCurrentRandTeam());
    }

    @Test
    public void testUpdateEnemyTeamsWithWeek() {
        Team originalEnemyTeam1 = gameEnvironment.getEnemyTeam1();
        gameEnvironment.updateEnemyTeams(1);
        assertNotSame(originalEnemyTeam1, gameEnvironment.getEnemyTeam1());
    }

  
   
    @Test
    public void testGetRandomEventQuitFull() {
        GameEnvironment game = new GameEnvironment();
        Athlete dave = new Athlete("Athlete 3", 70, 70, 90, "Forward");
        
        game.getPlayer().getTeam().addPlayer(dave);
        game.getPlayer().getTeam().addPlayer(dave);
        game.getPlayer().getTeam().addPlayer(dave);
        game.getPlayer().getTeam().addPlayer(dave);
        game.getPlayer().getTeam().addPlayer(dave);

        boolean foundQuit = false;

        for (int i = 0; i < 1000; i++) {
            String result = game.getRandomEvent();

            if (result != null && result.contains("quit")) {
                foundQuit = true;
                break;
            }
        }

        assertTrue(foundQuit);
    }

    @Test
    public void testGetRandomEventBoostFull() {
        GameEnvironment gameEnvironment = new GameEnvironment();
        Athlete dave = new Athlete("Athlete 3", 70, 70, 90, "Forward");

        gameEnvironment.getPlayer().getTeam().addPlayer(dave);
        gameEnvironment.getPlayer().getTeam().addPlayer(dave);
        gameEnvironment.getPlayer().getTeam().addPlayer(dave);
        gameEnvironment.getPlayer().getTeam().addPlayer(dave);
        gameEnvironment.getPlayer().getTeam().addPlayer(dave);

        boolean foundBoost = false;

        for (int i = 0; i < 1000; i++) {
            String result = gameEnvironment.getRandomEvent();

            if (result != null && result.contains("increased by 20")) {
                foundBoost = true;
                break;
            }
        }

        assertTrue(foundBoost);
    }
    
    @Test
    public void testGetRandomEventBoostNotFullOrEmpty() {
        GameEnvironment gameEnvironment = new GameEnvironment();
        Athlete dave = new Athlete("Athlete 3", 70, 70, 90, "Forward");

        gameEnvironment.getPlayer().getTeam().addPlayer(dave);
        gameEnvironment.getPlayer().getTeam().addPlayer(dave);
        gameEnvironment.getPlayer().getTeam().addPlayer(dave);
        

        boolean foundBoost = false;

        for (int i = 0; i < 1000; i++) {
            String result = gameEnvironment.getRandomEvent();

            if (result != null && result.contains("increased by 20")) {
                foundBoost = true;
                break;
            }
        }

        assertTrue(foundBoost);
    }
    
    @Test
    public void testGetRandomEventQuitNotFullOrEmpty() {
    	
        GameEnvironment gameEnvironment = new GameEnvironment();
        Athlete dave = new Athlete("Athlete 3", 70, 70, 90, "Forward");

        gameEnvironment.getPlayer().getTeam().addPlayer(dave);
        gameEnvironment.getPlayer().getTeam().addPlayer(dave);
        gameEnvironment.getPlayer().getTeam().addPlayer(dave);
        gameEnvironment.getPlayer().getTeam().addPlayer(dave);
        

        boolean foundBoost = false;

        for (int i = 0; i < 1000; i++) {
            String result = gameEnvironment.getRandomEvent();

            if (result != null && result.contains("quit")) { // pass if quit is in the output strings 
                foundBoost = true;
                break;
            }
        }

        assertTrue(foundBoost);
    }

    
    @Test
    public void testGetPoints_Win() {
        gameEnvironment.setDifficulty(2); // set the difficulty
        gameEnvironment.getPlayer().setCurrentWeek(2); // set the current week

        int points = gameEnvironment.getPoints("Win");
        assertEquals(200, points); // 2(currentWeek) * 50 * 2(difficulty) = 200
        assertEquals(200, gameEnvironment.getPlayer().getPoints()); // player should have the same points
    }

    @Test
    public void testGetPoints_Loss() {
        gameEnvironment.setDifficulty(2); 
        gameEnvironment.getPlayer().setCurrentWeek(2); 

        int points = gameEnvironment.getPoints("Loss");
        assertEquals(40, points); // 2(currentWeek) * 10 * 2(difficulty) = 40
        assertEquals(40, gameEnvironment.getPlayer().getPoints()); 
    }

    @Test
    public void testGetMoney_Win() {
        gameEnvironment.setDifficulty(2); 
        gameEnvironment.getPlayer().setMoney(0);
        gameEnvironment.getPlayer().setCurrentWeek(2); 

        int money = gameEnvironment.getMoney("Win");
        assertEquals(20, money); // 2(currentWeek) * 20 / 2(difficulty) = 20
        assertEquals(20, gameEnvironment.getPlayer().getMoney()); 
    }

    @Test
    public void testGetMoney_Loss() {
        gameEnvironment.setDifficulty(2); 
        gameEnvironment.getPlayer().setMoney(0);
        gameEnvironment.getPlayer().setCurrentWeek(2); 

        int money = gameEnvironment.getMoney("Loss");
        assertEquals(10, money); // 2(currentWeek) * 10 / 2(difficulty) = 10
        assertEquals(10, gameEnvironment.getPlayer().getMoney()); 
    }

    
}
