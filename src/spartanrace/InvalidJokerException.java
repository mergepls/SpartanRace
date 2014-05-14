/*
Name: Carson Huang (7007310) & Eric Lam (6958141)
Assignment: #3
Due Date: March 21 , 2014
*/
package spartanrace;


/**
 * This class is used for any Joker Exceptions that may occur.
 */
public class InvalidJokerException extends Exception {
    public InvalidJokerException(){
        super("Error: the joker choice can only be 'w' or 'c'.");
    }
    
    public InvalidJokerException(String msg){
        super(msg);
    }
    
    public String getMessage(){
        return super.getMessage().toUpperCase();
    }
}
