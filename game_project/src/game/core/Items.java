package game.core;

import java.util.ArrayList;
import java.util.Random;

/**
 * The Items class represents an item that an athlete could purchase or use. 
 * Each item has a name, effect, effect size, and price. 
 * This class provides functionality for generating random items and item characteristics.
 */
public class Items {
    private String name;
    private String effect;
    private int effectSize;
    private int price;

    private static final String[] ITEM_NAMES = {
    	    "E-Gel", "Weights", "G-FUEL", 
    	    "Shades", "juice", "UpAndGo", 
    	    "IceTub", "Shake", "Multivits"
    	};


    private static final String[] ITEM_EFFECTS = {"Offence", "Stamina", "Defence"};

    /**
     * Default constructor for the Items class. 
     * It initializes the instance variables with random values.
     */
    public Items() {
        this.name = generateRandomName();
        this.effect = generateRandomEffect();
        this.effectSize = generateRandomEffectSize(5, 15);
        this.price = generateRandomPrice(100, 300);
    }

    /**
     * Constructor for the Items class with parameters.
     * It initializes the instance variables with the provided values.
     *
     * @param name       The name of the item.
     * @param effect     The effect of the item.
     * @param effectSize The size of the item's effect.
     * @param price      The price of the item.
     */
    public Items(String name, String effect, int effectSize, int price) {
        this.name = name;
        this.effect = effect;
        this.effectSize = effectSize;
        this.price = price;
    }

    /**
     * @return The name of the item.
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return The effect of the item.
     */
    public String getEffect() {
        return this.effect;
    }

    /**
     * @return The size of the item's effect.
     */
    public int getEffectSize() {
        return this.effectSize;
    }

    /**
     * @return The price of the item.
     */
    public int getPrice() {
        return this.price;
    }

    /**
     * Generates a list of 5 random items.
     * @return An ArrayList of randomly generated Items objects.
     */
    public ArrayList<Items> generateItems() {
        ArrayList<Items> tempMarketItems = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            String tempName = generateRandomName();
            String tempEffect = generateRandomEffect();
            int tempEffectSize = generateRandomEffectSize(5, 15);
            int tempPrice = generateRandomPrice(20, 50);

            Items randItem = new Items(tempName, tempEffect, tempEffectSize, tempPrice);
            tempMarketItems.add(randItem);
        }
        return tempMarketItems;
    }

    /**
     * Generates a random name from the ITEM_NAMES array.
     * @return A String representing a randomly generated name.
     */
    private String generateRandomName() {
        Random random = new Random();
        int index = random.nextInt(ITEM_NAMES.length);
        return ITEM_NAMES[index];
    }

    /**
     * Generates a random effect from the ITEM_EFFECTS array.
     * @return A String representing a randomly generated effect.
     */
    private String generateRandomEffect() {
        Random random = new Random();
        int index = random.nextInt(ITEM_EFFECTS.length);
        return ITEM_EFFECTS[index];
    }
    
    /**
     * Generates a random effect size within the provided range.
     * @param min The minimum effect size.
     * @param max The maximum effect size.
     * @return An integer representing a randomly generated effect size.
     */
    private int generateRandomEffectSize(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    /**
     * Generates a random price within the provided range.
     * @param min The minimum price.
     * @param max The maximum price.
     * @return An integer representing a randomly generated price.
     */
    private int generateRandomPrice(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
}
