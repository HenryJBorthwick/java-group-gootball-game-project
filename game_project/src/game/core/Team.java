package game.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class represents a team, which contains a list of athletes.
 */
public class Team {
    private String name;
    private ArrayList<Athlete> athletes;

    /**
     * Default constructor. Initializes the athletes list.
     */
    public Team() {
        this.athletes = new ArrayList<>();
    }

    /**
     * Constructor with team name. Initializes the athletes list.
     *
     * @param tempName Name of the team.
     */
    public Team(String tempName) {
        this.name = tempName;
        this.athletes = new ArrayList<>();
    }

    /**
     * Returns the list of athletes in the team.
     *
     * @return The list of athletes.
     */
    public ArrayList<Athlete> getAthletes() {
        return this.athletes;
    }

    /**
     * Returns the number of athletes in the team.
     *
     * @return The number of athletes.
     */
    public int getNumberOfAthletes() {
        return athletes.size();
    }

    /**
     * Adds a new athlete to the team.
     *
     * @param athlete The athlete to be added.
     */
    public void addPlayer(Athlete athlete) {
        athletes.add(athlete);
    }

    /**
     * Counts and returns the number of injured athletes in the team.
     *
     * @return The number of injured athletes.
     */
    public int getNumberInjured() {
        int count = 0;
        for (Athlete athlete : athletes) {
            if (athlete.isInjured()) {
                count++;
            }
        }
        return count;
    }

    /**
     * Restores stamina to all athletes and set their injury status.
     */
    public void fullStamina() {
        for (Athlete athlete : athletes) {
            athlete.setStamina(100);
            athlete.setIsInjured();
        }
    }

    /**
     * Changes the stamina of each team member by a specified amount.
     *
     * @param amount The amount to change the stamina by.
     */
    public void changeTeamStamina(int amount) {
        for (Athlete athlete : this.athletes)
            athlete.loseStamina(amount);
    }

    /**
     * Checks if the team is full.
     *
     * @return True if the team is full (5 athletes), false otherwise.
     */
    public boolean teamFull() {
        return athletes.size() == 5;
    }

    /**
     * Checks if all athletes in the team are not injured.
     *
     * @return True if all athletes are not injured, false otherwise.
     */
    public boolean teamNotInjured() {
        for (Athlete athlete : this.athletes) {
            if (athlete.isInjured()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the team is full.
     *
     * @return True if the team is full (5 athletes), false otherwise.
     */
    public boolean isTeamFull() {
        return this.getNumberOfAthletes() == 5;
    }

    /**
     * Sets the injury status for each injured athlete in the team.
     */
    public void setWasInjured() {
        for (Athlete athlete : athletes) {
            if (athlete.isInjured()) {
                athlete.setBeenInjured();
            }
        }
    }

    /**
     * Returns a String representation of the team, including the name and the list of athletes.
     *
     * @return A string representing the team.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Team Name: ").append("\n").append(name).append("\n");
        for (Athlete athlete : athletes) {
            sb.append(athlete.toString()).append("\n");
        }
        return sb.toString();
    }
    
    /**
     * Provides a string representation of the team's name and the names of its athletes.
     *
     * @return A formatted string containing the team's name and its athletes' names.
     */
    public String toDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append("Team Name:\n").append(name).append("\n");
        for (Athlete athlete : athletes) {
            sb.append(athlete.getName()).append("\n");
        }
        return sb.toString();
    }

    /**
     * Displays the first four athletes of the team, or fewer if the team has less than four members.
     *
     * @return A string representation of up to the first four athletes of the team.
     */
    public String showTeam() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5 && i < athletes.size(); i++) {
            sb.append((i + 1)).append(". ").append(athletes.get(i).toString()).append("\n");
        }
        return sb.toString();
    }

    /**
     * Retrieves a list of all athletes in the team who are not injured.
     *
     * @return A list of active athletes.
     */
    public List<Athlete> getActiveAthletes() {
        List<Athlete> activeAthletes = new ArrayList<>();
        for (Athlete athlete : athletes) {
            if (!athlete.isInjured()) {
                activeAthletes.add(athlete);
            }
        }
        return activeAthletes;
    }

    /**
     * Retrieves a list of all athletes in the team who are injured.
     *
     * @return A list of reserve athletes.
     */
    public List<Athlete> getReserveAthletes() {
        List<Athlete> reserveAthletes = new ArrayList<>();
        for (Athlete athlete : athletes) {
            if (athlete.isInjured()) {
                reserveAthletes.add(athlete);
            }
        }
        return reserveAthletes;
    }

    /**
     * Creates a string representation of all injured athletes' names in the team.
     *
     * @return A string containing the names of injured athletes.
     */
    public String getInjured() {
        StringBuilder result = new StringBuilder();
        for (Athlete athlete : this.athletes) {
            if (athlete.isInjured()) {
                result.append(athlete.getName()).append("\n");
            }
        }
        return result.toString();
    }

    /**
     * Generates a random team name.
     *
     * @return A string containing a randomly chosen team name.
     */
    public String generateEnemyTeamName() {
        
    	 String[] teamNames = {
    		        "Falcons",
    		        "Wolves",
    		        "Tigers",
    		        "Hawks",
    		        "Pumas",
    		        "Sharks",
    		        "Ravens",
    		        "Jaguars",
    		        "Panthers",
    		        "Lions",
    		        "Eagles",
    		        "Bears",
    		        "Dragons",
    		        "Stallions",
    		        "Cobras",
    		        "Thunderbolts",
    		        "Vipers",
    		        "Pythons",
    		        "Gladiators",
    		        "Spartans"
    		    };

        Random random = new Random();
        int teamNameIndex = random.nextInt( teamNames.length);

        return  teamNames[teamNameIndex];
    }
    
    /**
     * Generates an enemy team with specific requirements for player positions.
     *
     * @param week The current week, which affects athlete generation.
     * @return The generated enemy team.
     */
    public Team generateEnemyTeam(int week) {
        String enemyTeamName = generateEnemyTeamName();
        Team enemyTeam = new Team(enemyTeamName);

        GenAthlete athleteGenerator = new GenAthlete();
        
        
        int defenceCount = 0;

        while (enemyTeam.getNumberOfAthletes() < 5) {
            String requiredPosition = (defenceCount < 3) ? "Defender" : "Forward";
            Athlete athleteObject = athleteGenerator.generateAthlete(week, requiredPosition);

            if (athleteObject.getPosition().equals("Defender")) {
                defenceCount++;
            } 
            enemyTeam.addPlayer(athleteObject);
        }

        return enemyTeam;
    }
    
    /**
     * Generates a new team without specific requirements for player positions.
     *
     * @return The generated team.
     */
    public Team generateTeam() {
        String enemyTeamName = generateEnemyTeamName();
        Team enemyTeam = new Team(enemyTeamName);

        while (enemyTeam.getNumberOfAthletes() < 5) {
            GenAthlete athleteGenerator = new GenAthlete();
            Athlete athleteObject = athleteGenerator.makeAthlete();
            enemyTeam.addPlayer(athleteObject);
        }
        return enemyTeam;
    }
    
    /**
     * Retrieves a list of all athletes in the team who are forwards.
     *
     * @return A list of forward athletes.
     */
    public ArrayList<Athlete> getForwardAthletes() {
        ArrayList<Athlete> forwards = new ArrayList<>();
        for (Athlete athlete : this.athletes) {
            if (athlete.getPosition().equals("Forward")) {
                forwards.add(athlete);
            }
        }
        return forwards;
    }
    
    /**
     * Retrieves a list of all athletes in the team who are defenders.
     *
     * @return A list of defender athletes.
     */
    public ArrayList<Athlete> getDefenceAthletes() {
        ArrayList<Athlete> defenders = new ArrayList<>();
        for (Athlete athlete : this.athletes) {
            if (athlete.getPosition().equals("Defender")) {
                defenders.add(athlete);
            }
        }
        return defenders;
    }
    
    /**
     * Compares the defensive and offensive abilities of two teams and determines the result.
     * The method calculates the score for the user's team based on the comparison and reduces stamina for underperforming athletes.
     *
     * @param userTeam   The user's team to compare.
     * @param enemyTeam  The enemy team to compare.
     * @return The result of the team comparison ("Win" or "Loss").
     */
    public String compareTeams(Team userTeam, Team enemyTeam) {
        ArrayList<Athlete> userDefenders = userTeam.getDefenceAthletes();
        ArrayList<Athlete> enemyDefenders = enemyTeam.getDefenceAthletes();

        ArrayList<Athlete> userForwards = userTeam.getForwardAthletes();
        ArrayList<Athlete> enemyForwards = enemyTeam.getForwardAthletes();

        int scoreUser = 0;
        
        // Loop through enemy defenders
        for (int i = 0; i < enemyDefenders.size(); i++) {

            // If there is a user defender to compare
            if (i < userDefenders.size()) {

                // If user's defender is better at defending
                if (userDefenders.get(i).getDefence() > enemyDefenders.get(i).getDefence()) {

                    // Increase user score
                    scoreUser++;

                } else { // If user's defender is worse at defending

                    // Decrease stamina of the user's athlete
                    int newStamina = userDefenders.get(i).getStamina() - 10;
                    newStamina = Math.max(0, newStamina);
                    userDefenders.get(i).setStamina(newStamina);
                }

            // If there is a user forward to compare
            } else if (i < userForwards.size()) {

                // If user's forward is better at Defence
                if (userForwards.get(i).getDefence() > enemyDefenders.get(i).getDefence()) {

                    // Increase user score
                    scoreUser++;

                } 
            }
        }

        // Loop through enemy forwards
        for (int i = 0; i < enemyForwards.size(); i++) {

            // If there is a user forward to compare
            if (i < userForwards.size()) {

                // If user's forward is better at offense
                if (userForwards.get(i).getOffence() > enemyForwards.get(i).getOffence()) {

                    // Increase user score
                    scoreUser++;

                } 

            // If there is a user defender to compare
            } else if (i < userDefenders.size()) {

                // If user's defender is better at defense
                if (userDefenders.get(i).getDefence() > enemyForwards.get(i).getOffence()) {

                    // Increase user score
                    scoreUser++;

                } 
            }
        }

        if (scoreUser >= 3) {
            return "Win";
        } else {
            return "Loss";
        }
    }
    
    /**
     * Removes and returns the last athlete from the team.
     *
     * @return The last athlete in the team.
     */
    public Athlete popLastAthlete() {
        return athletes.remove(athletes.size() - 1);
    }
    
    /**
     * Provides a random stat boost to a randomly selected athlete in the team.
     * The method increases either the offense or defense of the athlete by 20.
     *
     * @return A string describing the stat boost applied to the athlete.
     */
    public String randomStatBoost() {
        Random rand = new Random();
        String result = "";

        if (this.athletes.size() > 0) {
            Athlete selectedAthlete = this.athletes.get(rand.nextInt(this.athletes.size()));
            int statToBoost = rand.nextInt(2);

            switch (statToBoost) {
                case 0:
                    selectedAthlete.setOffence(selectedAthlete.getOffence() + 20);
                    result = selectedAthlete.getName() + " had their offence increased by 20.";
                    break;
                case 1:
                    selectedAthlete.setDefence(selectedAthlete.getDefence() + 20);
                    result = selectedAthlete.getName() + " had their defence increased by 20.";
                    break;
                default:
                    break;
            }
        } else {
            result = "No athletes to boost.";
        }

        return result;
    }
    
    /**
     * Randomly selects an athlete from the team and removes them from the team.
     * If the selected athlete has not been previously injured, another athlete is randomly selected until an injured athlete is found.
     *
     * @return A string describing the athlete who quit the team.
     */
    public String randomQuit() {
        Random rand = new Random();
        String result= "";

        if (this.athletes.size() > 0) {
            int athleteIndex = rand.nextInt(this.athletes.size());
            Athlete selectedAthlete = this.athletes.get(athleteIndex);

            if (!selectedAthlete.getBeenInjured()) {
                athleteIndex = rand.nextInt(this.athletes.size());
                selectedAthlete = this.athletes.get(athleteIndex);
            }

            this.athletes.remove(athleteIndex);
            result = selectedAthlete.getName() + " has quit the team.";
        } else {
            result = "No athletes to quit.";
        }

        return result;
    }
    
    /**
     * Randomly generates a new athlete and adds them to the team, if the team is not already full.
     *
     * @return A string describing the athlete who joined the team, or an error message if the team is already full.
     */
    public String randomJoin() {
        if (this.athletes.size() >= 5) {
            return "The team is full. No new athletes can join at this time.";
        }

        GenAthlete randGenAthlete = new GenAthlete();
        Athlete newAthlete = randGenAthlete.makeAthlete();
        this.athletes.add(newAthlete);

        return newAthlete.getName() + " has joined the team.";
    }
}
