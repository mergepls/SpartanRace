/*
Name: Carson Huang (7007310) & Eric Lam (6958141)
Assignment: #3
Due Date: March 21 , 2014
*/
package spartanrace;

/**
 * Class that creates a Perioikoi player
 */
public class Perioikoi extends Player{
    public Perioikoi(){
        energy = 130;
        position = 0;
    }
    
    public void setName(String name){
        this.name = "p_" + name;
    }
}
