package game.core;

/**
 * Athlete class for the game project.
 * It represents an athlete in the game, with properties like name, offence, defence,
 * stamina, position, price, injury status etc.
 */
public class Athlete {
    // Instance Variables
    private String name;
    private int offence;
    private int defence;
    private int stamina;
    private String position;
    private int price;
    private boolean isInjured;
    private boolean beenInjured;

    /**
     * Default constructor for the Athlete class.
     * It initializes the instance variables with default values.
     */
    public Athlete() {
    }

    /**
     * Constructor for the Athlete class with parameters.
     * It initializes the instance variables with the provided values.
     *
     * @param name     The name of the athlete.
     * @param offence  The offence attribute of the athlete.
     * @param defence  The defence attribute of the athlete.
     * @param stamina  The stamina attribute of the athlete.
     * @param position The position of the athlete in the game.
     */
    public Athlete(String name, int offence, int defence, int stamina, String position) {
        this.name = name;
        this.offence = offence;
        this.defence = defence;
        this.stamina = stamina;
        this.position = position;
        this.price = (offence + defence + stamina);
        this.isInjured = false;
        this.beenInjured = false;
    }

    // Getter and Setter methods

    /**
     * @return The name of the athlete.
     */
    public String getName() {
        return name;
    }

    /**
     * @return The offence attribute of the athlete.
     */
    public int getOffence() {
        return offence;
    }

    /**
     * @return The defence attribute of the athlete.
     */
    public int getDefence() {
        return defence;
    }

    /**
     * @return The stamina attribute of the athlete.
     */
    public int getStamina() {
        return stamina;
    }

    /**
     * @return The position of the athlete in the game.
     */
    public String getPosition() {
        return position;
    }

    /**
     * @return The price of the athlete.
     */
    public int getPrice() {
        return price;
    }

    /**
     * @return The injured status of the athlete.
     */
    public boolean isInjured() {
        return isInjured;
    }

    /**
     * @return The beenInjured status of the athlete.
     */
    public boolean getBeenInjured() {
        return beenInjured;
    }

    /**
     * Set the name of the athlete.
     *
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set the offence attribute of the athlete.
     *
     * @param offence The offence attribute to set.
     */
    public void setOffence(int offence) {
        this.offence = offence;
    }

    /**
     * Set the defence attribute of the athlete.
     *
     * @param defence The defence attribute to set.
     */
    public void setDefence(int defence) {
        this.defence = defence;
    }

    /**
     * Set the stamina attribute of the athlete.
     *
     * @param stamina The stamina attribute to set.
     */
    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    /**
     * Set the position of the athlete in the game.
     *
     * @param position The position to set.
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * Reset the injured status of the athlete to false.
     */
    public void setIsInjured() {
        this.isInjured = false;
    }

    /**
     * Set the beenInjured status of the athlete to true.
     */
    public void setBeenInjured() {
        this.beenInjured = true;
    }

    // Public methods

    /**
     * Reduce the stamina of the athlete by the specified amount.
     * If the stamina reaches zero, the athlete gets injured.
     *
     * @param amount The amount of stamina to lose.
     */
    public void loseStamina(int amount) {
        stamina -= amount;
        if (stamina <= 0) {
            stamina = 0;
            isInjured = true;
        }
    }

    /**
     * Train the athlete, improving their offence and defence attributes,
     * restoring stamina, removing any injury status, and increasing price.
     */
    public void train() {
        this.offence += 30;
        if (this.offence > 100) {
            this.offence = 100;
        }

        this.defence += 30;
        if (this.defence > 100) {
            this.defence = 100;
        }

        this.stamina = 100;
        this.isInjured = false;
        this.price += 100;
    }

    /**
     * Use an item on the athlete, improving their attributes based on the item's effect.
     *
     * @param selectedItem The item to use.
     */
    public void useItem(Items selectedItem) {
        String itemType = selectedItem.getEffect();

        switch (itemType) {
            case "Offence":
                this.setOffence(Math.min(100, selectedItem.getEffectSize() + this.getOffence()));
                break;
            case "Stamina":
                this.setStamina(Math.min(100, selectedItem.getEffectSize() + this.getStamina()));
                break;
            case "Defence":
                this.setDefence(Math.min(100, selectedItem.getEffectSize() + this.getDefence()));
                break;
        }
    }

    // Override methods from java.lang.Object

    /**
     * Returns a string representation of the Athlete object.
     *
     * @return A string representing the athlete.
     */
    @Override
    public String toString() {
        return "Athlete {" +
            "name='" + name + '\'' +
            ", offence=" + offence +
            ", defence=" + defence +
            ", stamina=" + stamina +
            ", position='" + position + '\'' +
            ", price=" + price +
            ", isInjured=" + isInjured +
            '}';
    }
}
