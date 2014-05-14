/*
Name: Carson Huang (7007310) & Eric Lam (6958141)
Assignment: #3
Due Date: March 21 , 2014
*/
package spartanrace;

/**
 * Class that creates a Spartiate player
 */
public class Spartiate extends Player{
    public Spartiate(){
        energy = 100;
        position = 20;
    }
    
    public void setName(String name){
        this.name = name;
    }
}
