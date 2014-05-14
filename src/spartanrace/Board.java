/*
Name: Carson Huang (7007310) & Eric Lam (6958141)
Assignment: #3
Due Date: March 21 , 2014
*/
package spartanrace;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * This is the Board class, that generates the skeleton of the map. It'll set the map, update the positions of the players
 * and so on.
 * @author Carson Huang (7007310)
 */
public class Board{
    //private String [][] board = new String [6][30];
    private Cell [] b1 = new Cell[90];
    private String [] b2 = new String[90];
    
    /**
     * Sets the board up.
     */
    public Board(){
        for(int i = 0; i < 90; i++){
            b1[i] = new NormalCell();
            b2[i] = "  ";
        }
        
        b2[9] = b2[29] = b2[49] = b2[69] = "d ";
        b2[19] = b2[54] = "s ";
        b2[39] = b2[59] = b2[79] = "w ";
        b2[16] = b2[36] = b2[56] = "? ";

        b1[9] = b1[29] = b1[49] = b1[69] = new DeckCell();
        b1[19] = b1[54] = new SkipCell();
        b1[39] = b1[59] = b1[79] = new WheelCell();
        b1[16] = b1[36] = b1[56] = new JokerCell();
    }
    
    /**
     * A getter for the String board. Method is not used in this program.
     * @return Returns the board
     */
    public Cell[] getBoard(){
        return b1;
    }
    
    /**
     * A setter for the String board. Method is not used in this program.
     * @param board 
     */
    public void setBoard(Cell b1[]){
        this.b1 = b1;
    }
    
    /**
     * Method used to get the string associated to the position on the board.
     * @param position The position on the board of the string required.
     * @return Returns the string associated to the position on the board.
     */
    public String getString(int position){

        if(position == 0 || position > 90){
            return "";
        }else{
            return b1[position-1].getBoardString();
        }
    }
    
    /**
     * Method used to set or reset the board.
     */
    public void resetBoard(){
        for(int i = 0; i < 90; i++){
            b1[i].setBoardString("_ ");
        }
        
    }
    
    /**
     * Method used to update the strings on the board, according to the position of the players on the board.
     * @param position Integer value that indicates the position where the string needs an update.
     * @param numbOfPlayers String that will be updated on the map that shows the number of players on that position.
     */
    public void updateBoard(int position, String numbOfPlayers){
        
        if(position == 0 || position > 90){
        }else{
            b1[position-1].setBoardString(numbOfPlayers);
        }
    }
    
    /**
     * Method that verifies if the current position of the player rolling fell on an obstacle or not.
     * @param position The new position of the current player.
     * @return Returns the obstacle type. 0 = Deck of Fortune, 1 = Jump to 68, 2 = Wheel of Fortune, 3 = Choice between 1 and 2 and 4 = no obstacle.
     */
    public void isPlayerOnObstacle(int position, int i){
        if(position == 0 || position > 90){
        }else{
            b1[position-1].action(i);
        }
    }
    
    /**
     * Method to print out the board.
     */
    public void printBoard(){
        
        Driver.output.println();
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 30; j++){
                Driver.output.print(b1[30*i + j].getBoardString());
            }
            Driver.output.println();
            for(int k = 0; k < 30; k++){
                Driver.output.print(b2[30*i + k]);
            }
            Driver.output.println();
        }
        
    }
}
