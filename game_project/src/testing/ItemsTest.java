package testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import game.core.Items;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class ItemsTest {
    private Items items;

    @BeforeEach
    public void setUp() {
        items = new Items("Energy Gel", "Stamina", 10, 200);
    }

    // Testing constructor
    @Test
    public void testConstructor() {
        assertEquals("Energy Gel", items.getName());
        assertEquals("Stamina", items.getEffect());
        assertEquals(10, items.getEffectSize());
        assertEquals(200, items.getPrice());
    }

    // Testing getters
    @Test
    public void testGetters() {
        assertEquals("Energy Gel", items.getName());
        assertEquals("Stamina", items.getEffect());
        assertEquals(10, items.getEffectSize());
        assertEquals(200, items.getPrice());
    }

    // Testing generateItems method
    @Test
    public void testGenerateItems() {
        ArrayList<Items> generatedItems = items.generateItems();

        assertEquals(5, generatedItems.size());

        Set<String> possibleNames = new HashSet<>(Arrays.asList(
        		"E-Gel", "Weights", "G-FUEL", 
        	    "Shades", "juice", "UpAndGo", 
        	    "IceTub", "Shake", "Multivits"
        ));

        Set<String> possibleEffects = new HashSet<>(Arrays.asList(
            "Offence", "Stamina", "Defence"
        ));

        for (Items item : generatedItems) {
            assertTrue(possibleNames.contains(item.getName()));
            assertTrue(possibleEffects.contains(item.getEffect()));
            assertTrue(item.getEffectSize() >= 5 && item.getEffectSize() <= 15);
            assertTrue(item.getPrice() >= 20 && item.getPrice() <= 50);
        }
    }
}
