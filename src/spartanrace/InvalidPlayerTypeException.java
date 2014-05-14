/*
Name: Carson Huang (7007310) & Eric Lam (6958141)
Assignment: #3
Due Date: March 21 , 2014
*/
package spartanrace;

/**
 * This class is used for any Player Type Exceptions that may occur.
 */
public class InvalidPlayerTypeException extends Exception{
    public InvalidPlayerTypeException(){
        super("Error: Player type can only be between 1-3.");
    }
    
    public InvalidPlayerTypeException(String msg){
        super(msg);
    }
    
    public String getMessage(){
        return super.getMessage().toUpperCase();
    }
}
