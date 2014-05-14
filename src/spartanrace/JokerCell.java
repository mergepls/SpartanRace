/*
Name: Carson Huang (7007310) & Eric Lam (6958141)
Assignment: #3
Due Date: March 21 , 2014
*/
package spartanrace;

/**
 * Cell that represents a Joker Cell
 */
public class JokerCell extends Cell{

    private int choice;

    /**
     * Getter for choice\
     * @return choice
     */
    public int getChoice() {
        return choice;
    }
    /**
     * Setter for choice
     * @param choice 
     */
    public void setChoice(int choice) {
        this.choice = choice;
    }
    /**
     * Updates players energy
     * @param i Current player in the array
     * @return Energy level
     */
    public int updateEnergy(int i){
        switch(choice){
            case 1: return (Driver.players[i].getEnergy() - Math.abs(DeckCell.getReturnValue()) + 30);
            case 2: return (Driver.players[i].getEnergy() - Math.abs(WheelCell.getReturnValue()))*2;
            default: return 0;
        }
    }
    
    /**
     * Action taken by the program when user lands on this cell
     * @param i Current player in the array
     */
    public void action(int i){
        System.out.print("\nHe arrived on a case where he will need to either spin the Wheel of Forturne or pick a card from the Deck Of Forturne. " );
        
        //Card choice
        switch(Driver.modeChoice){
            case 1: choice = Driver.getInput(1,2, "What will he choose: "
                        + "\nPress 1 to pick a card"
                        + "\nPress 2 to spin the wheel"
                        + "\nChoice: ");
                    break;
            case 2: if(Driver.jokerChoice.compareTo("c") == 0){
                        choice = 1;
                    }else{
                        choice = 2;
                    }       
        }
        
        //Results of card choice
        switch(choice){
            case 1: System.out.print("\nHe decided to pick a card from the Deck of Forturne. Because of the card's obstacle, he must now ");
                    Driver.players[i].setPosition(Driver.players[i].getPosition() + DeckCell.changePosition(Driver.players[i].getPosition()));
                    Driver.players[i].setEnergy(updateEnergy(i));
                    break;
            case 2: WheelCell.setWheelChoice((int)(Math.random()*8+1));
                    System.out.print("\nHe decided to spin the Wheel of Forturne. He spinned a " + WheelCell.getWheelChoice() + " and he must now ");
                    WheelCell.changePosition(Driver.players[i].getPosition());
                    Driver.players[i].setPosition(Driver.players[i].getPosition() + WheelCell.getReturnValue());
                    Driver.players[i].setEnergy(updateEnergy(i));
                    break;
        }
        System.out.println("Player " + Driver.players[i].getName() + " is therefore at position " + Driver.players[i].getPosition() + " now. Energy remaining: " + Driver.players[i].getEnergy());
    } 
}
