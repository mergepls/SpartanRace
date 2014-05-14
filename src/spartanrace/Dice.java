/*
Name: Carson Huang (7007310) & Eric Lam (6958141)
Assignment: #3
Due Date: March 21 , 2014
*/
package spartanrace;

/**
 * The Dice class will create two dice and send back the sum of the two dices.
 */
public class Dice {
    
    //Dice = plurial, die = singular
    private int die1, die2;
    
    /**
     * Method to set the first die. Method not used.
     * @param die1 
     */
    public void setDie1(int die1){
        this.die1 = die1;
    }
    
    /**
     * Method to set the second die. Method not used.
     * @param die2
     */
    public void setDie2(int die2){
        this.die2 = die2;
    }
    
    /**
     * Method to return the first die. 
     */
    public int getDie1(){
        return die1;
    }
    
    /**
     * Method to return the second die. 
     */
    public int getDie2(){
        return die2;
    }
    
    /**
     * Set both dice to 0.
     */
    public Dice(){
        die1 = 0;
        die2 = 0;
    }
    
    /**
     * Method used to randomize the value of both dice. 
     * @return Returns the sum of the two dice.
     */
    public int throwDice(){
        die1 = (int)(Math.random()*6 + 1);   
        die2 = (int)(Math.random()*6 + 1);
        return (die1 + die2);
    }
}