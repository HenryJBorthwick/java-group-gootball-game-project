package game.core;

import java.util.ArrayList;
import java.util.Random;

/**
 * The GameEnvironment class represents the game environment and manages various game-related functionalities.
 * It includes features such as team management, market, enemy teams, player updates, purchases, points calculation,
 * and random events.
 */
public class GameEnvironment {
    // Class fields
    private String teamName;
    private int difficulty;
    private Team marketTeam;
    private ArrayList<Items> marketItems;
    private Team enemyTeam1;
    private Team enemyTeam2;
    private Team enemyTeam3;
    private GenAthlete genAthlete;
    private Items items;
    private Team team;
    private Player player;

    /**
     * Constructs a new GameEnvironment object and initializes the game environment.
     * It generates the market team, market items, and enemy teams based on the player's current week.
     */
    public GameEnvironment() {
        genAthlete = new GenAthlete();
        items = new Items();
        team = new Team();
        player = new Player();

        this.marketTeam = team.generateTeam();
        this.marketItems = items.generateItems();

        this.enemyTeam1 = team.generateEnemyTeam(player.getCurrentWeek());
        this.enemyTeam2 = team.generateEnemyTeam(player.getCurrentWeek());
        this.enemyTeam3 = team.generateEnemyTeam(player.getCurrentWeek());
    }

    /**
     * Sets the name of the player's team.
     *
     * @param tempTeamName The name of the team.
     */
    public void setTeamName(String tempTeamName) {
        this.teamName = tempTeamName;
    }

    /**
     * Retrieves the name of the player's team.
     *
     * @return The name of the team.
     */
    public String getTeamName() {
        return this.teamName;
    }

    /**
     * Sets the difficulty level of the game.
     *
     * @param tempDifficulty The difficulty level.
     */
    public void setDifficulty(int tempDifficulty) {
        this.difficulty = tempDifficulty;
    }

    /**
     * Retrieves the player object associated with the game environment.
     *
     * @return The player object.
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Retrieves the first enemy team.
     *
     * @return The first enemy team.
     */
    public Team getEnemyTeam1() {
        return enemyTeam1;
    }

    /**
     * Retrieves the second enemy team.
     *
     * @return The second enemy team.
     */
    public Team getEnemyTeam2() {
        return enemyTeam2;
    }

    /**
     * Retrieves the third enemy team.
     *
     * @return The third enemy team.
     */
    public Team getEnemyTeam3() {
        return enemyTeam3;
    }

    /**
     * Updates the enemy teams based on the player's current week.
     */
    public void updateEnemyTeams() {
        this.enemyTeam1 = team.generateEnemyTeam(player.getCurrentWeek());
        this.enemyTeam2 = team.generateEnemyTeam(player.getCurrentWeek());
        this.enemyTeam3 = team.generateEnemyTeam(player.getCurrentWeek());
    }

    /**
     * Generates a random athlete based on the game's difficulty level.
     *
     * @return The randomly generated athlete.
     */
    public Athlete getRandAthlete() {
        return genAthlete.generateAthlete(difficulty);
    }

    /**
     * Retrieves the current market items available for purchase.
     *
     * @return The list of market items.
     */
    public ArrayList<Items> getCurrentMarketItems() {
        return this.marketItems;
    }

    /**
     * Updates the market items by generating new items.
     */
    public void updateMarketItems() {
        this.marketItems = items.generateItems();
    }

    /**
     * Retrieves the current market team available for player interactions.
     *
     * @return The market team.
     */
    public Team getCurrentRandTeam() {
        return this.marketTeam;
    }

    /**
     * Updates the market team by generating a new team.
     */
    public void updateRandTeam() {
        this.marketTeam = team.generateTeam();
    }

    /**
     * Updates the enemy teams based on the provided week.
     *
     * @param week The current week.
     */
    public void updateEnemyTeams(int week) {
        this.enemyTeam1 = enemyTeam1.generateEnemyTeam(week);
        this.enemyTeam2 = enemyTeam2.generateEnemyTeam(week);
        this.enemyTeam3 = enemyTeam3.generateEnemyTeam(week);
    }

    /**
     * Handles the purchase of an athlete from the market.
     *
     * @param selectedAthlete The athlete to be purchased.
     * @param athletePrice    The price of the athlete.
     * @param selectedTeam    The target team (Main or Reserve).
     * @return True if the purchase is successful, false otherwise.
     */
    public boolean purchase(Athlete selectedAthlete, int athletePrice, String selectedTeam) {
        if (player.getMoney() >= athletePrice) {
            marketTeam.getAthletes().remove(selectedAthlete);
            player.deductMoney(athletePrice);

            if (selectedTeam.equals("Main")) {
                player.addToMain(selectedAthlete);
            } else if (selectedTeam.equals("Reserve")) {
                player.addToReserve(selectedAthlete);
            }

            return true;
        } else {
            return false;
        }
    }

    /**
     * Handles the purchase of an item from the market.
     *
     * @param selectedItem The item to be purchased.
     * @param itemPrice    The price of the item.
     * @return True if the purchase is successful, false otherwise.
     */
    public boolean purchase(Items selectedItem, int itemPrice) {
        if (player.getMoney() >= itemPrice) {
            player.addToInventory(selectedItem);
            marketItems.remove(selectedItem);
            player.deductMoney(itemPrice);

            return true;
        } else {
            return false;
        }
    }

    /**
     * Calculates the points earned based on the result of a game.
     *
     * @param result The result of the game (Win or Loss).
     * @return The points earned.
     */
    public int getPoints(String result) {
        int points;
        if ("Win".equals(result)) {
            points = player.getCurrentWeek() * 50 * difficulty;
        } else {
            points = player.getCurrentWeek() * 10 * difficulty;
        }
        player.updatePoints(points);
        return points;
    }

    /**
     * Calculates the money earned based on the result of a game.
     *
     * @param result The result of the game (Win or Loss).
     * @return The money earned.
     */
    public int getMoney(String result) {
        int money;
        if ("Win".equals(result)) {
            money = player.getCurrentWeek() * 20 / difficulty;
        } else {
            money = player.getCurrentWeek() * 10 / difficulty;
        }
        player.updateMoney(money);
        return money;
    }

    /**
     * Generates a random event and returns the result.
     * Random events can include players joining the team, stat boosts, or players quitting the team.
     *
     * @return The result of the random event.
     */
    public String getRandomEvent() {
        
        Random rand = new Random();

        float eventChance = rand.nextFloat();

        if (eventChance <= 0.75) {
            return null;
        }

        int event;
        String result = "";

        if (this.getPlayer().getTeam().getAthletes().size() == 0) {
            result = player.getTeam().randomJoin();
        } else if (this.getPlayer().getTeam().getAthletes().size() >= 5) {
            event = rand.nextInt(10) + 1;

            if (event >= 1 && event <= 6) {
                result = player.getTeam().randomStatBoost();
            } else if (event >= 7 && event <= 10) {
                result = player.getTeam().randomQuit();
            }
        } else {
            event = rand.nextInt(player.getMainTeamSize() + 10) + 1;

            if (event >= 1 && event <= 5) {
                result = player.getTeam().randomJoin();
            } else if (event >= 6 && event <= 10) {
                result = player.getTeam().randomStatBoost();
            } else if (event >= 11 && event <= 16) {
                result = player.getTeam().randomQuit();
            }
        }

      
        return result;
    }
}
