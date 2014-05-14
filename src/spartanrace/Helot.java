/*
Name: Carson Huang (7007310) & Eric Lam (6958141)
Assignment: #3
Due Date: March 21 , 2014
*/
package spartanrace;

/**
 * Class that creates a Helot player
 */
public class Helot extends Player{
    public Helot(){
        energy = 60;
        position = 40;
    }
    
    public void setName(String name){
        this.name = "h_" + name;
    }
}
