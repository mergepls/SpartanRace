/*
Name: Carson Huang (7007310) & Eric Lam (6958141)
Assignment: #3
Due Date: March 21 , 2014
*/
package spartanrace;

/**
 * Cell that represents a Normal Cell
 */
public class NormalCell extends Cell{
    /**
     * Action for normal cell. Since it is a normal cell, there is no special action.
     * @param i Current player in the array
     */
    public void action(int i){}
    
    /**
     * updates the energy of player
     * @param i Current player in the array
     * @return  
     */
    public int updateEnergy(int i){
        return 0;
    }
}
