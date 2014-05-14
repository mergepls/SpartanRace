/*
Name: Carson Huang (7007310) & Eric Lam (6958141)
Assignment: #3
Due Date: March 21 , 2014
*/
package spartanrace;

/**
 * The Player class is used to create the player's profile. It'll keep track of its name and position. All methods are not abstract, 
 * because they are used as is in the child classes. The only abstract method is the setName, which differs from child class to child class.
 * @author Carson Huang (7007310)
 */
public abstract class Player {
    protected String name;
    protected int position, energy, oldPosition;
    
    /**
     * Sets the name of the player
     * @param Name of the player
     */
    public abstract void setName(String name);
    
    /**
     * Getter for oldPosition
     * @return oldPosition
     */
    public int getOldPosition() {
        return oldPosition;
    }
    /**
     * Setter for oldPosition
     * @param oldPosition 
     */
    public void setOldPosition(int oldPosition) {
        this.oldPosition = oldPosition;
    }
    
    /**
     * Gets the name of the player. Method not used.
     * @return Returns the name of the current player.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the position of the player. 
     * @return Returns the position of the current player.
     */
    public int getPosition() {
        return position;
    }
    /**
     * Setter for position
     * @param position 
     */
    /**
     * 
     * Sets the position of the player. 
     * @param position Sets current player's position.
     */
    public void setPosition(int position) {
        this.position = position;
    }
    
    /**
     * @return the energy
     */
    public int getEnergy() {
        return energy;
    }

    /**
     * @param energy the energy to set
     */
    public void setEnergy(int energy) {
        this.energy = energy;
    }
    /**
     * Default constructor
     */
    public Player(){
        name = null;
        position = 0;
        energy = 0;
    }
    public int debugDisplacement(){
        return Math.abs(oldPosition-position);
    }
    /**
     * Updates the position of the player.
     * @param diceValue  Value of the sum of the dice
     */
    public void move(int diceValue){
        position += diceValue;
    }
    

    
}
