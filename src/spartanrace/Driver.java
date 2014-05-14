/*
Name: Carson Huang (7007310) & Eric Lam (6958141)
Assignment: #3
Due Date: March 21 , 2014
*/
package spartanrace;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Driver class for this program.
 * @author Carson Huang (7007310) Eric Lam (6958141)
 * @version 3.0
 */
public class Driver {
    
    static Scanner keyboard = new Scanner(System.in);
    static int dicesValue = 0,  //Sum of the two dice
            numberOfWinners = 0,//Int that stores the number of players
            numberOfPlayers = 0,//Int that stores the number of players
            oldPosition = 0;
    static String winners[];    //String array to store the names of the winners
    static boolean valid = false; //valid is used for the loops
    static int modeChoice = 0;  //Choice: real-time or debug mode (1 or 2)
    static String jokerChoice = ""; //String used to save the value of the joker choice for debug mode
    static Scanner readFile;
    static FileReader input, input2;
    static StringPosition stringOfSaidPosition = null;  //Described in javadoc
    static Player[] players;                            //Object is described in Player class
    static Dice dice = new Dice();                      //Object is described in Dcie class
    static Board board = new Board();                   //Object is described in Board class
    static PrintWriter output;
    
    /**
     * Enum class.
     * These are constants that are used to update the position of the players on the map.
     * If there are four players on a single position, then the FOUR("4 ") will be used to update the map.
     */
    public enum StringPosition {        
        ONE("1 "), TWO("2 "), THREE("3 "), FOUR("4 ");
        
        private String currentPlayersOnPosition;

        private StringPosition(String currentPlayersOnPosition){
            this.currentPlayersOnPosition = currentPlayersOnPosition;
        }
    };

    public static void main(String[] args) throws IOException{
        boolean noline = false;
        
        try{
            output = new PrintWriter("race_out.txt");
        }
        
        catch(FileNotFoundException e){
            System.out.println("Error: The output file does not exist. Please create one called \"race_out.txt\"]");
            System.exit(0);
        }

        do{ 
            //Ask for game mode
            modeChoice = getInput(1,2,"Welcome to the Spartan Race! Who shall will become slaves today?"
                            + "\r\nGame Mode: "
                            + "\r\n1. Regular mode"
                            + "\r\n2. Debugging mode"
                            + "\r\nChoice: ");
            //Real-time mode basic info inputs
            if(modeChoice == 1){
                //Ask for number of players
                numberOfPlayers = getInput(2,4, "\r\n# Of Players (2-4): ");
                players = new Player[numberOfPlayers];
                winners = new String[numberOfPlayers];

                //Ask for players' type and name
                for(int i = 0; i < numberOfPlayers; i++){
                    switch(getInput(1,3, "Who are you: "
                            + "\r\n1. Spartiate (Position: 20, Initial Energy: 100)"
                            + "\r\n2. Helot (Position: 40, Initial Energy: 60"
                            + "\r\n3. Perioikoi (Position: 0, Initial Energy: 130"
                            + "\r\nChoice: ")){
                        case 1: players[i] = new Spartiate();
                            break;
                        case 2: players[i] = new Helot();
                            break;
                        case 3: players[i] = new Perioikoi();
                            break;
                    }

                    System.out.print("\nEnter name of Player #" + (i+1) + ": ");
                    output.println("\r\nEnter name of Player #" + (i+1) + ": ");

                    players[i].setName(keyboard.nextLine());
                    output.print(players[i].getName());
                    System.out.print("+-+-+-+-+-+-+-+-+-+-+-+-+-+-\n");
                    output.println("\r\n+-+-+-+-+-+-+-+-+-+-+-+-+-+-\r\n");
                }
            //Debug mode basic info inputs
            }else{
                try{
                    Scanner readLine;
                    String name, line;                   
                    input = new FileReader("debug_input.txt");
                    Scanner readFile = new Scanner(input);
                    line = readFile.nextLine();
                    readLine = new Scanner(line);
                    if(!checkLine(line, 1)){
                        throw new InvalidNOPException("Error: Wrong format on number of players line. Only 1 input should be on that line.");
                    }else if(!readLine.hasNextInt()){
                        throw new InvalidNOPException("Error: Incorrect type for number of players' input.");
                    }
                        numberOfPlayers = readLine.nextInt();
                        players = new Player[numberOfPlayers]; 
                        winners = new String[numberOfPlayers]; 
                        output.println("Number of Players: " + numberOfPlayers);
                    
                    if(numberOfPlayers < 2 || numberOfPlayers > 4)
                        throw new InvalidNOPException();

                    for(int i = 0; i < numberOfPlayers; i++){
                        name = readFile.nextLine();
                        output.println("Name: " + name);
                        line = readFile.nextLine();
                        readLine = new Scanner(line);
                        if(!checkLine(line, 1)){
                            throw new InvalidPlayerTypeException("Error: Wrong format on players' type line. Only 1 input should be on that line.");
                        }else if(readLine.hasNextInt()){
                            int playerType = readLine.nextInt();
                            output.println("Type: " + playerType);                               

                            if(playerType < 1 || playerType > 3){
                                throw new InvalidPlayerTypeException();
                            }

                            switch(playerType){
                                case 1: players[i] = new Spartiate();
                                    break;
                                case 2: players[i] = new Helot();
                                    break;
                                case 3: players[i] = new Perioikoi();
                                    break;
                            }
                            players[i].setName(name);
                        }else{
                            throw new InvalidPlayerTypeException("Error: Incorrect type for player's type input.");
                        }   
                    }

                    while(readFile.hasNextLine()){
                        line = readFile.nextLine();
                        readLine = new Scanner(line);
                        if(!checkLine(line, 3)){
                            throw new InvalidDieException("Error: Wrong format on dice&joker line. Three inputs should be on that line.");
                        }else{
                            int die;
                            for(int i = 0; i < 2; i++){
                                if(readLine.hasNextInt()){
                                    die = readLine.nextInt();
                                    if(die < 1 || die > 6){
                                        throw new InvalidDieException();
                                    }
                                }else{
                                    throw new InvalidDieException("Error: Incorrect type for die input.");
                                }   
                            }

                            jokerChoice = readLine.next();
                            if(jokerChoice.compareTo("c") != 0 && jokerChoice.compareTo("w") != 0){
                                throw new InvalidJokerException();
                            }
                        }
                    }
                }

                catch(FileNotFoundException f){   
                    String error = "This file does not exist. Please create a text file named 'debug_input' that respects the following criteria: "
                            + "\r\n-> For every step, please start on a new line:"
                            + "\r\n1. Number of the players"
                            + "\r\n2. Name of the player"
                            + "\r\n3. Type of the player"
                            + "\r\n4. Repeat #2 and #3 for all players"
                            + "\r\n5. dievalue1 dievalue2 jokercellchoice"
                            + "\r\nRepeat #5 as many times as you want";
                    System.out.println(error);
                    output.println(error);
                    System.exit(0);
                }

                catch(InvalidNOPException e){
                    System.out.println(e.getMessage());
                    output.println(e.getMessage());
                    System.exit(0);
                }

                catch(InvalidPlayerTypeException e){
                    System.out.println(e.getMessage());
                    output.println(e.getMessage());
                    System.exit(0);
                }

                catch(InvalidDieException e){
                    System.out.println(e.getMessage());
                    output.println(e.getMessage());
                    System.exit(0);
                }

                catch(InvalidJokerException e){
                    System.out.println(e.getMessage());
                    output.println(e.getMessage());
                    System.exit(0);
                }

                input2 = new FileReader("debug_input.txt");
                readFile = new Scanner(input2);

                for(int i = 0; i < (numberOfPlayers*2)+1; i++){
                    readFile.nextLine();
                }
            }
            
            //Rolling phase. The loop will continue until valid = true
            do{ 
                if(modeChoice == 1){
                    for(int i = 0; i < numberOfPlayers; i++){
                        if(players[i] == null){
                            continue;
                        }else{
                        dicesValue = getInput((i+1),(i+1), "\r\nPlayer " + players[i].getName() + ", enter " + (i+1) +" to roll dices: ");      
                        dicesValue = dice.throwDice();

                        players[i].setEnergy(players[i].getEnergy()-dicesValue);

                        if(isEnergyZero(i))
                            continue;

                        players[i].move(dicesValue);
                        movingPhase(i);                  
                        }                
                    }
                }else{
                    for(int i = 0; i < numberOfPlayers; i++){
                        if(players[i] == null){
                            continue;
                        }else{
                            if(readFile.hasNextLine()){
                                dice.setDie1(readFile.nextInt());
                                dice.setDie2(readFile.nextInt());
                                dicesValue = dice.getDie1() + dice.getDie2();
                                jokerChoice = readFile.next();
                                players[i].setOldPosition(players[i].getPosition());
                                players[i].setPosition(players[i].getOldPosition() + dice.getDie1() + dice.getDie2());
                                players[i].setEnergy(players[i].getEnergy() - players[i].debugDisplacement());

                                if(isEnergyZero(i))
                                    continue;

                                movingPhase(i);
                            }else{
                                valid = true;
                                noline = true;
                                break;
                            }
                        }
                    }
                }
                updateBoard();
                board.printBoard();                
                System.out.println("\n+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
                output.println("\r\n+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+\r\n");
                checkForWinners();

                if(noPlayersAlive() == true){
                    System.out.print("\nAll players are dead. Mwahahahahahahaha!!!");
                    output.print("\r\nAll players are dead. Mwahahahahahahaha!!!");
                    break;
                }
            }while(!valid);
            
            //If there are no more lines in the input txt file and the game has not yet ended
            if(noline == true){
                System.out.println("There are no more lines to read from the txt file. Therefore, the game shall end with no winner.");
            }
            
            //Ask for user if he wants to continue or end the program
            switch(getInput(1,2,"\r\nWould you like to continue? "
                            + "\r\nPress 1 to continue"
                            + "\r\nPress 2 to end"
                            + "\r\nChoice: ")){
                case 1: valid = false;
                        numberOfWinners = 0;
                        numberOfPlayers = 0;
                        modeChoice = 0;
                        noline = false;
                        DeckCell.resetDeck();
                        output.close();
                        System.out.println();
                break;
                case 2: System.out.println("\nThank you for using Spartan Race 3.0!");
                        output.close();
                        System.exit(0);
            }
        }while(!valid);
    }
    
    /**
     * 
     * @param line The line we are currently reading from the file.
     * @param numberOfString The number of values this line should have.
     * @return Returns true if the number of strings is correct. Else, returns false.
     */
    public static boolean checkLine(String line, int numberOfString){
        String [] words = line.split(" ");
        
        if(words.length == numberOfString){
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * Check if player's energy is 0
     * @param i Player number
     * @return Returns true when energy is 0
     */
    public static boolean isEnergyZero(int i){
        if(players[i].getEnergy() <= 0){
            System.out.println(players[i].getName() + " died because his energy level has reached 0 or lower than 0. RIP."); 
            players[i] = null;
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * Method is used to print out the moving phase of every player.
     * @param i Current element number, to be used in the players[] array.
     */

    public static void movingPhase(int i){
        //Prints out a message telling the user the current position of the player
        System.out.println("Player " + players[i].getName() + " rolled a " + dice.getDie1() + " and a " + dice.getDie2() + ". Total is: " + dicesValue 
            + ". New position: " + players[i].getPosition() + ".");
        output.println("Player " + players[i].getName() + " rolled a " + dice.getDie1() + " and a " + dice.getDie2() + ". Total is: " + dicesValue 
            + ". New position: " + players[i].getPosition() + ".");
        
        System.out.println("Energy remaining: " + players[i].getEnergy());
        output.println("Energy remaining: " + players[i].getEnergy());
            
        //Checks if player is on a special case. If he is, he'll perform the action required
        board.isPlayerOnObstacle(players[i].getPosition(), i);     
    }
    
    /**
     * Method is used to update the position of the players on the map. 
     */
    public static void updateBoard(){    
        board.resetBoard();
        
        //Adds the position of the players on the board
        for(int i = 0; i < (numberOfPlayers); i++){
            if(players[i] == null){
                continue;
            }else{
                switch(board.getString(players[i].getPosition())){
                    case "_ ": stringOfSaidPosition = stringOfSaidPosition.ONE;
                        board.updateBoard(players[i].getPosition(), stringOfSaidPosition.currentPlayersOnPosition);
                        break;
                    case "1 ": stringOfSaidPosition = stringOfSaidPosition.TWO;
                        board.updateBoard(players[i].getPosition(), stringOfSaidPosition.currentPlayersOnPosition);
                        break;
                    case "2 ": stringOfSaidPosition = stringOfSaidPosition.THREE;
                        board.updateBoard(players[i].getPosition(), stringOfSaidPosition.currentPlayersOnPosition);
                        break;
                    case "3 ": stringOfSaidPosition = stringOfSaidPosition.FOUR;
                        board.updateBoard(players[i].getPosition(), stringOfSaidPosition.currentPlayersOnPosition);
                        break;
                    default:
                        break;
                }
            }
        }
    }
    /**
     * Check if all players are dead
     * @return true if all players are dead
     */
    public static boolean noPlayersAlive(){
        int count = 0;
        for(int i = 0; i < numberOfPlayers; i++){
            if(players[i] == null){
                count++;
            }
        }
        
        if(count == numberOfPlayers){
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * 
     * Method is used to ask for an integer input from the user.
     * This method is used several times in the program to get an integer input from the user.
     * If the input is incorrect (input value is not within the range or input is not an integer), then it'll 
     * keep asking the user for a correct input.
     * 
     * @param minValue Integer value used to set a minimum for the input of the user.
     * @param maxValue Integer value used to set a maximum for the input of the user.
     * @param text String that contains the output text.
     * @return Returns the choice of the user.
     */
    public static int getInput(int minValue, int maxValue, String text){
        boolean valid = false;
        String input;
        int integerInput = 0;
        
        while(valid == false){
            try{
                System.out.print(text);
                output.print(text);
                input = keyboard.nextLine();
                output.println(input);
                integerInput = Integer.parseInt(input);
                if(integerInput < minValue || integerInput > maxValue)
                    throw new Exception();
                break;
            }
            catch(Exception e){
                System.err.println("Please enter a valid input.");
                output.println("Please enter a valid input.");
            }
        }
        return integerInput;       
    }
    /**
     * Method that verifies after every round if there are any winners.
     * Verifies if any players has passed or is at position 90.
     */
    public static void checkForWinners(){
        //Counts the number of players crossing the line on this turn
        for(int i = 0; i < numberOfPlayers; i++){
            if(players[i] == null){
                continue;
            }else{
                if(players[i].getPosition() >= 90){
                    winners[numberOfWinners] = players[i].getName();
                    numberOfWinners++;
                    valid = true;
                }
            }
        }
        
        //Prints out a different message, depending on if there are more than 1 winner or not
        if(numberOfWinners == 1){
            System.out.println("Player " + winners[numberOfWinners-1] + " has crossed the line and won the race! The rest of you"
                    + " shall be sold as slaves! Hahahaha!");
            output.println("Player " + winners[numberOfWinners-1] + " has crossed the line and won the race! The rest of you"
                    + " shall be sold as slaves! Hahahaha!");
        }else if(numberOfWinners > 1){
            System.out.println("We have more than one player arriving at the finish line on the same turn. Congratulations to");
            output.println("We have more than one player arriving at the finish line on the same turn. Congratulations to");
            for(int i = 0; i < numberOfWinners; i++){
                if(players[i] == null){
                    continue;
                }else{
                    System.out.print("\nPlayer " + winners[i]);
                    output.print("\r\nPlayer " + winners[i]);
                }
            }
            System.out.println("\n\nThe losers will be sold as slaves. Hahahaha!\n");
            output.println("\r\n\nThe losers will be sold as slaves. Hahahaha!\n");
            output.close();
        }
    }
}