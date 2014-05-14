/*
Name: Carson Huang (7007310) & Eric Lam (6958141)
Assignment: #3
Due Date: March 21 , 2014
*/
package spartanrace;

/**
 * Cell that represents a Deck Cell
 */
public class DeckCell extends Cell{

    private final int ENERGY_BONUS = 30;
    
    /**
     * Action taken by the program when user lands on this cell
     * @param i Current player in the array
     */
    public void action(int i){       
        System.out.print("He arrived on a case where he must pick a card from the Deck of Forturne. Because of the card's obstacle, he must now ");
        Driver.players[i].setPosition(Driver.players[i].getPosition() + changePosition(Driver.players[i].getPosition()));
        Driver.players[i].setEnergy(updateEnergy(i));
        System.out.println("Player " + Driver.players[i].getName() + " is therefore at position " + Driver.players[i].getPosition() + " now. Energy remaining: " + Driver.players[i].getEnergy());
    }
    
    /**
     * Updates players energy
     * @param i Current player in the array
     * @return Energy level
     */
    public int updateEnergy(int i){
        return (Driver.players[i].getEnergy() - Math.abs(returnValue) + ENERGY_BONUS);
    }






    private static int currentCard, returnValue;
    
    /**
     * Enum class.
     * These are constants that are used to print out the obstacle of the card picked from the Deck of Fortune.
     */
    public enum Outputs {        
        ONE("go back 9 cases!"), TWO("go back to case 0!"), THREE("go back 3 cases!"), FOUR("go back 8 cases!"), FIVE("go forward 2 cases!"), 
        SIX("go forward 1 case!"), SEVEN("go forward 3 cases!"), EIGHT("go back to case 0!"), NINE("go back 4 cases!"), TEN("go forward 6 cases!");
        
        private String outputString;


        private Outputs(String outputString){
            this.outputString = outputString;
        }
    };
    
    /**
     * A setter for the current card that is on top of the deck. Method is not used in this program.
     * @param currentCard The current card number.
     */
    public void setCurrentCard(int currentCard){
        this.currentCard = currentCard;
    }
    
    public void setReturnValue(int returnValue){
        this.returnValue = returnValue;
    }
    
    public static int getReturnValue(){
        return returnValue;
    }
     
    /**
     * Method to reset the deck of its original form.
     */
    public static void resetDeck(){
        currentCard = 0;
    }
    
    /**
     * Method used to print out the card's obstacle and returns the obstacle's value.
     * @param currentPosition This integer is used whenever the player has to go back to case 0. The return value would be -currentPosition.
     * @return Returns the number of cases the user must go forward or go back.
     */
    public static int changePosition(int currentPosition){
        Outputs output = null;
        
        switch(currentCard){
            case 0: output = Outputs.ONE;
                    returnValue = -9;
                break;
            case 1: output = Outputs.TWO;
                    returnValue = -currentPosition; 
                break;
            case 2: output = Outputs.THREE;
                    returnValue = -3;
                break;
            case 3: output = Outputs.FOUR;
                    returnValue = -8;
                break;
            case 4: output = Outputs.FIVE;
                    returnValue = 2;
                break;
            case 5: output = Outputs.SIX;
                    returnValue = 1;
                break;
            case 6: output = Outputs.SEVEN;
                    returnValue = 3;
                break;
            case 7: output = Outputs.EIGHT;
                    returnValue = -currentPosition;
                break;
            case 8: output = Outputs.NINE;
                    returnValue = -4;
                break;
            case 9: output = Outputs.TEN;
                    currentCard = -1; returnValue = 6;
                break;
            default: returnValue = 0;
        }

        if(currentCard == 9){
            resetDeck();
        }else{
            currentCard++;
        }
        System.out.println(output.outputString);
        return returnValue;
    }
    
}
