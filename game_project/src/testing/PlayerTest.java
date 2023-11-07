package testing;
import org.junit.jupiter.api.Test;

import game.core.Athlete;
import game.core.GameEnvironment;
import game.core.Items;
import game.core.Player;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    @Test
    public void testPlayerInitialization() {
        Player player = new Player();
        assertEquals(1500, player.getMoney());
        assertEquals(1, player.getCurrentWeek());
        assertEquals(10, player.getWeeksRemaining());
        assertEquals(0, player.getPoints());
        assertEquals(0, player.getTotalMoney());
        assertEquals(0, player.getInventorySize());
        assertNotNull(player.getTeam());
        assertNotNull(player.getReserveTeam());
    }

    @Test
    public void testPlayerUpdateMoney() {
        Player player = new Player();
        player.updateMoney(2000);
        assertEquals(3500, player.getMoney());
        assertEquals(2000, player.getTotalMoney());

        player.updateMoney(-1000);
        assertEquals(2500, player.getMoney());
        assertEquals(2000, player.getTotalMoney());
    }

    @Test
    public void testPlayerUpdatePoints() {
        Player player = new Player();
        player.updatePoints(5);
        assertEquals(5, player.getPoints());
    }

    @Test
    public void testPlayerDeductMoney() {
        Player player = new Player();
        player.deductMoney(500);
        assertEquals(1000, player.getMoney());
    }

    @Test
    public void testPlayerAddToInventory() {
        Player player = new Player();
        Items item = new Items();
        player.addToInventory(item);
        assertEquals(1, player.getInventorySize());
    }

    @Test
    public void testPlayerAddToTeam() {
        Player player = new Player();
        Athlete athlete = new Athlete();
        player.addToTeam(athlete);
        assertEquals(1, player.getMainTeamSize());
    }

    @Test
    public void testPlayerAddToMain() {
        Player player = new Player();
        for (int i = 0; i < 6; i++) {
            Athlete athlete = new Athlete();
            player.addToMain(athlete);
        }
        assertEquals(5, player.getMainTeamSize());
        assertEquals(1, player.getReserveTeamSize());
    }
    
    @Test
    public void testPlayerAddToReserve() {
        Player player = new Player();
        Athlete athlete = new Athlete();
        player.addToReserve(athlete);
        assertEquals(1, player.getReserveTeamSize());
    }

    @Test
    public void testPlayerSwapAthletes() {
        Player player = new Player();
        Athlete athlete1 = new Athlete();
        Athlete athlete2 = new Athlete();
        player.addToMain(athlete1);
        player.addToReserve(athlete2);
        player.swapAthletes(0, 0);
        assertEquals(athlete2, player.getTeam().getAthletes().get(0));
        assertEquals(athlete1, player.getReserveTeam().getAthletes().get(0));
    }

    @Test
    public void testPlayerUseItem() {
    	GameEnvironment gameEnvironment = new GameEnvironment();
    	Items item = new Items("Sword", "Offence", 10, 100);
    	gameEnvironment .getPlayer().addToInventory(item);
        Athlete athleteBefore = new Athlete("Athlete 1", 50, 50, 50, "Forward");
        gameEnvironment .getPlayer().addToMain(athleteBefore);
        gameEnvironment .getPlayer().useItem(0, 1, 0);
        Athlete athleteAfter = gameEnvironment.getPlayer().getTeam().getAthletes().get(0);
        int change  = athleteAfter.getOffence();
        
        assertEquals(60, change);
    }
    
    
    @Test
    public void testPlayerGetReserveTeamSize() {
        Player player = new Player();
        assertEquals(0, player.getReserveTeamSize());
    }

}
