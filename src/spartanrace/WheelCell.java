/*
Name: Carson Huang (7007310) & Eric Lam (6958141)
Assignment: #3
Due Date: March 21 , 2014
*/
package spartanrace;

/**
 * Cell that represents a Wheel of Fortune Cell
 */
public class WheelCell extends Cell{
    
    private static int returnValue, wheelChoice;
    
    private String boardString = "_ ";
    private final int ENERGY_BONUS = 2;       
    
    /**
     * Setter for returnValue
     * @param returnValue 
     */
    public void setReturnValue(int returnValue){
        this.returnValue = returnValue;
    }
    /**
     * Getter for returnValue
     * @return returnValue
     */
    public static int getReturnValue(){
        return returnValue;
    }
    
    public static void setWheelChoice(int wheelChoice){
        WheelCell.wheelChoice = wheelChoice;
    }
    
    public static int getWheelChoice(){
        return WheelCell.wheelChoice;
    }

     /**
     * Enum class.
     * These are constants that are used to print out the obstacle from the Wheel of Fortune.
     */
    public enum OutputString {        
        ONE("go forward 1 case!\n"), TWO("go forward 2 cases!\n"), THREE("go back to case 0!\n"), FOUR("go back 4 cases!\n"), FIVE("go back 6 cases!\n"), 
        SIX("go back 7 cases!\n"), SEVEN("go back 8 cases!\n"), EIGHT("go back 9 cases!\n");
        
        private String output;

        private OutputString(String output){
            this.output = output;
        }
    };

    /**
     * Action to be taken when user lands on a wheel of fortune cell
     * @param i Current player in the array
     */
    public void action(int i){
        wheelChoice = (int)(Math.random()*8+1);
        System.out.print("He arrived on a case where he will spin the Wheel of Forturne. He spinned a " + wheelChoice + " and he must now ");
        changePosition(Driver.players[i].getPosition());
        Driver.players[i].setPosition(Driver.players[i].getPosition() + returnValue);
        Driver.players[i].setEnergy(updateEnergy(i));
        System.out.println("Player " + Driver.players[i].getName() + " is therefore at position " + Driver.players[i].getPosition() + " now. Energy remaining: " + Driver.players[i].getEnergy());
    }
    /**
     * Updates the energy of user
     * @param i Current player in the array
     * @return current energy after changes
     */
    public int updateEnergy(int i){
        return (Driver.players[i].getEnergy() - Math.abs(returnValue))*ENERGY_BONUS;
    }

    /**
     * Method used to return the value that must be changed from the position where the player is because of the obstacle from the Wheel of Fortune.
     * @param currentPosition This integer is used whenever the player has to go back to case 0. The return value would be -currentPosition.
     * @return Returns the number of cases the user must go forward or go back.
     */
    public static void changePosition(int currentPosition){
        OutputString outputString = null;
        
        switch(wheelChoice-1){
            case 0: outputString = OutputString.ONE;
                    returnValue = 1;
                    break;
            case 1: outputString = OutputString.TWO;
                    returnValue = 2; 
                    break;
            case 2: outputString = OutputString.THREE;
                    returnValue = -currentPosition;
                    break;
            case 3: outputString = OutputString.FOUR;
                    returnValue = -4;
                    break;
            case 4: outputString = OutputString.FIVE;
                    returnValue = -6;
                    break;
            case 5: outputString = OutputString.SIX;
                    returnValue = -7;
                    break;
            case 6: outputString = OutputString.SEVEN;
                    returnValue = -8;
                    break;
            case 7: outputString = OutputString.EIGHT;
                    returnValue = -9;
                    break;
            default: returnValue = 0;
        }
        
        System.out.print(outputString.output);
    }
    
    
    
}
