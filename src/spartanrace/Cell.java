/*
Name: Carson Huang (7007310) & Eric Lam (6958141)
Assignment: #3
Due Date: March 21 , 2014
*/
package spartanrace;

/**
 * Abstract class the Cell types
 */
public abstract class Cell{
    protected String boardString = "_ ";
    
    public abstract void action(int i);
    public abstract int updateEnergy(int i);
    
    /**
     * Sets the String of x cell on the board
     * @param boardString String of the cell
     */
    public void setBoardString(String boardString){
        this.boardString = boardString;
    }
   
    /**
     * 
     * @return Returns the string of x cell on the board
     */
    public String getBoardString(){
        return boardString;
    }
}
