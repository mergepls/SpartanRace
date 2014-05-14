/*
Name: Carson Huang (7007310) & Eric Lam (6958141)
Assignment: #3
Due Date: March 21 , 2014
*/
package spartanrace;

/**
 * Cell that represents a Skip to 68 Cell
 */
public class SkipCell extends Cell{
    
    /**
     * Action taken when user lands on this cell
     * @param i 
     */
    public void action(int i){
        System.out.print("He arrived on a case where he will jump to case 68!\n");
        Driver.players[i].setPosition(68);
        Driver.players[i].setEnergy(updateEnergy(i));
        System.out.println("Player " + Driver.players[i].getName() + " is therefore at position " + Driver.players[i].getPosition() + " now. Energy remaining: " + Driver.players[i].getEnergy());
    }
    /**
     * Updates the energy of player
     * @param i
     * @return energy after landing on this cell
     */
    public int updateEnergy(int i){
        return Driver.players[i].getEnergy();
    }
}
