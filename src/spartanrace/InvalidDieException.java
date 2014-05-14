/*
Name: Carson Huang (7007310) & Eric Lam (6958141)
Assignment: #3
Due Date: March 21 , 2014
*/
package spartanrace;

/**
 * This class is used for any Dice Exceptions that may occur.
 */
public class InvalidDieException extends Exception{
    public InvalidDieException(){
        super("Error: the die can only be between 1-6.");
    }
    
    public InvalidDieException(String msg){
        super(msg);
    }
    
    public String getMessage(){
        return super.getMessage().toUpperCase();
    }
}
