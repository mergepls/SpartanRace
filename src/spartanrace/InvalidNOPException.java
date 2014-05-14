/*
Name: Carson Huang (7007310) & Eric Lam (6958141)
Assignment: #3
Due Date: March 21 , 2014
*/
package spartanrace;


/**
 * This class is used for any Number of Players Exceptions that may occur.
 */
public class InvalidNOPException extends Exception{
    public InvalidNOPException(){
        super("Error: the number of players can be only between 2-4.");
    }
    
    public InvalidNOPException(String msg){
        super(msg);
    }
    
    public String getMessage(){
        return super.getMessage().toUpperCase();
    }
}
