package testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import game.core.Athlete;
import game.core.Items;

import static org.junit.jupiter.api.Assertions.*;

public class AthleteTest {
    private Athlete athlete;
    private Items offenceItem;
    private Items staminaItem;
    private Items defenceItem;
    
    @BeforeEach
    public void setUp() {
        athlete = new Athlete("John", 50, 50, 50, "Striker");
        // Assuming Items class has a constructor Items(String effect, int effectSize)
        offenceItem = new Items("TestItem", "Offence", 10, 1);
        staminaItem = new Items("TestItem", "Stamina", 10, 1);
        defenceItem = new Items("TestItem", "Defence", 10, 1);
    }

    // Testing constructor
    @Test
    public void testConstructor() {
        assertEquals("John", athlete.getName());
        assertEquals(50, athlete.getOffence());
        assertEquals(50, athlete.getDefence());
        assertEquals(50, athlete.getStamina());
        assertEquals("Striker", athlete.getPosition());
        assertEquals(150, athlete.getPrice());  // 50 + 50 + 50
        assertFalse(athlete.isInjured());
        assertFalse(athlete.getBeenInjured());
    }

    // Testing loseStamina method
    @Test
    public void testLoseStamina() {
        athlete.loseStamina(10);
        assertEquals(40, athlete.getStamina());
        assertFalse(athlete.isInjured());
    }

    @Test
    public void testLoseStaminaResultingInInjury() {
        athlete.loseStamina(50);
        assertEquals(0, athlete.getStamina());
        assertTrue(athlete.isInjured());
    }

    // Testing train method
    @Test
    public void testTrain() {
        athlete.train();
        assertEquals(80, athlete.getOffence());  // 50 + 30 but capped at 80
        assertEquals(80, athlete.getDefence());  // 50 + 30 but capped at 80
        assertEquals(100, athlete.getStamina());
        assertEquals(250, athlete.getPrice());  // 50 + 50 + 50 + 100
        assertFalse(athlete.isInjured());
    }

    // Testing useItem method
    @Test
    public void testUseOffenceItem() {
        athlete.useItem(offenceItem);
        assertEquals(60, athlete.getOffence());  // 50 + 10
    }

    @Test
    public void testUseStaminaItem() {
        athlete.loseStamina(30);
        athlete.useItem(staminaItem);
        assertEquals(30, athlete.getStamina());  // 20 + 10
    }

    @Test
    public void testUseDefenceItem() {
        athlete.useItem(defenceItem);
        assertEquals(60, athlete.getDefence());  // 50 + 10
    }

    // Testing toString method
    @Test
    public void testToString() {
        String expectedString = "Athlete {" +
            "name='John'" +
            ", offence=50" +
            ", defence=50" +
            ", stamina=50" +
            ", position='Striker'" +
            ", price=150" +  // 50 + 50 + 50
            ", isInjured=false" +
            "}";
        assertEquals(expectedString, athlete.toString());
    }
    
    // Testing setters
    @Test
    public void testSetters() {
        athlete.setName("Mike");
        assertEquals("Mike", athlete.getName());
        
        athlete.setOffence(60);
        assertEquals(60, athlete.getOffence());

        athlete.setDefence(60);
        assertEquals(60, athlete.getDefence());

        athlete.setStamina(60);
        assertEquals(60, athlete.getStamina());

        athlete.setPosition("Defender");
        assertEquals("Defender", athlete.getPosition());

        athlete.setBeenInjured();
        assertTrue(athlete.getBeenInjured());

        athlete.setIsInjured();
        assertFalse(athlete.isInjured());
    }
}
