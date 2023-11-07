package game.core;

import java.util.ArrayList;

/**
 * Represents a player in the game.
 */
public class Player {
    private static final int MAX_TEAM_SIZE = 5;
    
    private int money;
    private int totalMoney;
    private int currentWeek;
    private int weeksRemaining;
    private ArrayList<Items> inventory;
    private Team team;
    private Team reserves;
    private int points;

    /**
     * Constructor for the Player class.
     * Initializes the player with default values.
     */
    public Player() {
        // Initialize member variables
        this.money = 1500;
        this.currentWeek = 1;
        this.weeksRemaining = 10;
        this.inventory = new ArrayList<Items>();
        this.team = new Team("main");
        this.reserves = new Team("reserves");
        this.points = 0;
        this.totalMoney = 0;
    }

    /**
     * Retrieves the amount of money the player currently has.
     *
     * @return The amount of money the player has.
     */
    public int getMoney() {
        return money;
    }
    
    /**
     * Retrieves the total amount of money the player has earned.
     *
     * @return The total amount of money earned by the player.
     */
    public int getTotalMoney() {
        return this.totalMoney;
    }
    
    /**
     * Retrieves the current week in the game for the player.
     *
     * @return The current week.
     */
    public int getCurrentWeek() {
        return this.currentWeek;
    }

    /**
     * Retrieves the number of weeks remaining in the game for the player.
     *
     * @return The number of weeks remaining.
     */
    public int getWeeksRemaining() {
        return this.weeksRemaining;
    }
    
    /**
     * Retrieves the inventory of items owned by the player.
     *
     * @return The inventory of items.
     */
    public ArrayList<Items> getInventory() {
        return inventory;
    }
    
    /**
     * Retrieves the size of the player's inventory.
     *
     * @return The size of the inventory.
     */
    public int getInventorySize() {
        return inventory.size();
    }

    /**
     * Retrieves the player's main team.
     *
     * @return The main team of the player.
     */
    public Team getTeam() {
        return this.team;
    }
    
    /**
     * Retrieves the player's reserve team.
     *
     * @return The reserve team of the player.
     */
    public Team getReserveTeam() {
        return this.reserves;
    }

    /**
     * Retrieves the number of points earned by the player.
     *
     * @return The number of points earned.
     */
    public int getPoints() {
        return this.points;
    }

    /**
     * Sets the amount of money the player has.
     *
     * @param money The new amount of money.
     */
    public void setMoney(int money) {
        this.money = money;
    }
    
    /**
     * Sets the current week in the game for the player.
     *
     * @param week The current week.
     */
    public void setCurrentWeek(int week) {
        this.currentWeek = week;
    }
    
    /**
     * Sets the number of weeks remaining in the game for the player.
     *
     * @param weeks The number of weeks remaining.
     */
    public void setRemainingWeeks(int weeks) {
        this.weeksRemaining = weeks;
    }

    /**
     * Updates the player's points by the specified amount.
     *
     * @param amount The amount by which to update the points.
     */
    public void updatePoints(int amount) {
        this.points += amount;
    }

    /**
     * Updates the player's money by the specified amount.
     * If the amount is positive, it also increases the total money earned.
     *
     * @param amount The amount by which to update the money.
     */
    public void updateMoney(int amount) {
        this.money += amount;
        if (amount > 0) {
            this.totalMoney += amount;
        }
    }

    /**
     * Updates the player's team with a new team.
     *
     * @param newTeam The new team.
     */
    public void updateTeam(Team newTeam) {
        this.team = newTeam;
    }

    /**
     * Deducts the specified amount of money from the player's total.
     *
     * @param amount The amount to deduct.
     */
    public void deductMoney(int amount) {
        this.money -= amount;
    }
    
    /**
     * Adds the specified item to the player's inventory.
     *
     * @param item The item to add.
     */
    public void addToInventory(Items item) {
        this.inventory.add(item);
    }

    /**
     * Adds the specified athlete to the player's main team.
     *
     * @param athlete The athlete to add.
     */
    public void addToTeam(Athlete athlete) {
        team.addPlayer(athlete);
    }
    
    /**
     * Retrieves the size of the player's main team.
     *
     * @return The size of the main team.
     */
    public int getMainTeamSize() {
        return this.team.getNumberOfAthletes();
    }

    /**
     * Retrieves the size of the player's reserve team.
     *
     * @return The size of the reserve team.
     */
    public int getReserveTeamSize() {
        return this.reserves.getNumberOfAthletes();
    }

    /**
     * Adds the specified athlete to the player's main team.
     * If the main team is already at the maximum size, the last athlete is moved to the reserve team.
     *
     * @param selectedAthlete The athlete to add to the main team.
     */
    public void addToMain(Athlete selectedAthlete) {
        if (team.getNumberOfAthletes() >= MAX_TEAM_SIZE) {
            Athlete removedAthlete = team.popLastAthlete();
            reserves.getAthletes().add(removedAthlete);
        }
        team.getAthletes().add(selectedAthlete);
    }

    /**
     * Adds the specified athlete to the player's reserve team.
     *
     * @param selectedAthlete The athlete to add to the reserve team.
     */
    public void addToReserve(Athlete selectedAthlete) {
        reserves.getAthletes().add(selectedAthlete);
    }

    /**
     * Swaps the athletes at the specified positions between the main team and the reserve team.
     *
     * @param indexMain The index of the athlete in the main team.
     * @param indexRes  The index of the athlete in the reserve team.
     */
    public void swapAthletes(int indexMain, int indexRes) {
        Athlete athleteFromMain = this.getTeam().getAthletes().get(indexMain);
        Athlete athleteFromRes = this.getReserveTeam().getAthletes().get(indexRes);

        this.getTeam().getAthletes().set(indexMain, athleteFromRes);
        this.getReserveTeam().getAthletes().set(indexRes, athleteFromMain);
    }

    /**
     * Uses the specified item on the specified player in either the main team or the reserve team.
     *
     * @param indexPlayer The index of the player (athlete) to use the item on.
     * @param indexTeam   Specifies whether the player is in the main team (1) or the reserve team (2).
     * @param itemIndex   The index of the item to use.
     */
    public void useItem(int indexPlayer, int indexTeam, int itemIndex) {
        Items selectedItem = this.getInventory().get(itemIndex);

        Athlete selectedPlayer;
        if (indexTeam == 1) {
            selectedPlayer = this.getTeam().getAthletes().get(indexPlayer);
        } else {
            selectedPlayer = this.getReserveTeam().getAthletes().get(indexPlayer);
        }
        selectedPlayer.useItem(selectedItem);
    }
}
