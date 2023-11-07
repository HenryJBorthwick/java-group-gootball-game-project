package game.core;

import java.util.Random;

/**
 * The GenAthlete class is responsible for generating random Athlete objects with random characteristics.
 * Characteristics of an Athlete include name, offensive skill, defensive skill, stamina, and position.
 * This class can be used to create a variety of Athletes with varying degrees of skill and different positions.
 */
public class GenAthlete {

    private String name;
    private int offense;
    private int defense;
    private int stamina;
    private String position;

    private static final String[] FIRST_NAMES = {"James", "John", "Robert", "Mich", "Willy", "David", "Richy", "Joe", "Ton", "Chad", 
            "Mary", "Jenn", "Linda", "Pat", "Lizzo", "Susan", "Jess", "Sarah", "Karen", "Nancy"};
    private static final String[] LAST_NAMES = {"Smith", "John", "Dow", "Brown", "Jones", "Miller", "Davis", "Garcia", "Musk", "Wilson"};

    private static final String[] POSITIONS = {"Forward", "Defender"};
    private static final int QUALITY = 2;
    private static final int MIN_STAT = 20 + QUALITY * 4;
    private static final int MAX_STAT = 100;

    /**
     * Default constructor for the GenAthlete class. 
     * It initializes the instance variables with random values.
     */
    public GenAthlete() {
        this.name = generateRandomName();
        this.offense = generateRandomStat(QUALITY);
        this.defense = generateRandomStat(QUALITY);
        this.stamina = generateRandomStat(QUALITY);
        this.position = generateRandomPosition();
    }

    /**
     * Generates a random name from a pre-defined list of first and last names.
     * @return A String representing a randomly generated name.
     */
    private String generateRandomName() {
        Random random = new Random();
        int firstNameIndex = random.nextInt(FIRST_NAMES.length);
        int lastNameIndex = random.nextInt(LAST_NAMES.length);
        return FIRST_NAMES[firstNameIndex] + " " + LAST_NAMES[lastNameIndex];
    }

    /**
     * Generates a random stat between MIN_STAT and MAX_STAT.
     * @param difficulty A multiplier for the random stat generation.
     * @return An integer representing the randomly generated stat.
     */
    private int generateRandomStat(int difficulty) {
        Random random = new Random();
        return random.nextInt(MAX_STAT - MIN_STAT + 1) + MIN_STAT;
    }

    /**
     * Generates a random position from a pre-defined list of positions.
     * @return A String representing a randomly generated position.
     */
    private String generateRandomPosition() {
        Random random = new Random();
        int positionIndex = random.nextInt(POSITIONS.length);
        return POSITIONS[positionIndex];
    }

    /**
     * Generates a new Athlete object with specified difficulty and position.
     * @param difficulty A multiplier for the random stat generation.
     * @param position The desired position for the Athlete.
     * @return A new Athlete object with random characteristics.
     */
    public Athlete generateAthlete(int difficulty, String position) {
        String name = generateRandomName();
        int offense = generateRandomStat(difficulty);
        int defense = generateRandomStat(difficulty);
        int stamina = generateRandomStat(difficulty);
        return new Athlete(name, offense, defense, stamina, position);
    }

    /**
     * Generates a new Athlete object using the instance variables of this class.
     * @return A new Athlete object with the same characteristics as this GenAthlete.
     */
    public Athlete makeAthlete() {
        return new Athlete(name, offense, defense, stamina, position);
    }

    /**
     * Generates a new Athlete object with specified difficulty.
     * @param difficulty A multiplier for the random stat generation.
     * @return A new Athlete object with random characteristics.
     */
    public Athlete generateAthlete(int difficulty) {
        String name = generateRandomName();
        int offense = generateRandomStat(difficulty);
        int defense = generateRandomStat(difficulty);
        int stamina = generateRandomStat(difficulty);
        String position = generateRandomPosition();
        return new Athlete(name, offense, defense, stamina, position);
    }
}
