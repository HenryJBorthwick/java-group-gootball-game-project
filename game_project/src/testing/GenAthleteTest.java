package testing;
import org.junit.jupiter.api.Test;

import game.core.Athlete;
import game.core.GenAthlete;

import static org.junit.jupiter.api.Assertions.*;

public class GenAthleteTest {
    
    @Test
    public void testGenAthlete() {
        GenAthlete genAthlete = new GenAthlete();
        Athlete athlete = genAthlete.makeAthlete();
        
        assertTrue(athlete.getName().contains(" "));
        assertTrue(athlete.getOffence() >= 20);
        assertTrue(athlete.getOffence() <= 100);
        assertTrue(athlete.getDefence() >= 20);
        assertTrue(athlete.getDefence() <= 100);
        assertTrue(athlete.getStamina() >= 20);
        assertTrue(athlete.getStamina() <= 100);
        assertTrue(athlete.getPosition().equals("Forward") || athlete.getPosition().equals("Defender"));
    }
    
    @Test
    public void testGenerateAthleteWithDifficultyAndPosition() {
        GenAthlete genAthlete = new GenAthlete();
        Athlete athlete = genAthlete.generateAthlete(3, "Forward");

        assertTrue(athlete.getName().contains(" "));
        assertTrue(athlete.getOffence() >= 20);
        assertTrue(athlete.getOffence() <= 100);
        assertTrue(athlete.getDefence() >= 20);
        assertTrue(athlete.getDefence() <= 100);
        assertTrue(athlete.getStamina() >= 20);
        assertTrue(athlete.getStamina() <= 100);
        assertEquals("Forward", athlete.getPosition());
    }

    @Test
    public void testGenerateAthleteWithDifficulty() {
        GenAthlete genAthlete = new GenAthlete();
        Athlete athlete = genAthlete.generateAthlete(3);

        assertTrue(athlete.getName().contains(" "));
        assertTrue(athlete.getOffence() >= 20);
        assertTrue(athlete.getOffence() <= 100);
        assertTrue(athlete.getDefence() >= 20);
        assertTrue(athlete.getDefence() <= 100);
        assertTrue(athlete.getStamina() >= 20);
        assertTrue(athlete.getStamina() <= 100);
        assertTrue(athlete.getPosition().equals("Forward") || athlete.getPosition().equals("Defender"));
    }
}
