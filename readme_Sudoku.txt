/**********************************************************************
 * Project: Sudoku Solver 
 **********************************************************************/

Name: Debby Lin 
PennKey: debbylin 

/**********************************************************************
 *  Have you enter all help, collaboration, and outside resources
 *  in your help log?  If not, do so now.  (And in future, make sure
 *  you make your help log entries as you go, not at the end!)
 *
 *  If you did not get any help in outside of TA office hours,
 *  and did not use any materials outside of the standard
 *  course materials and piazza, write the following statement below:
 *  "I did not receive any help outside of TA office hours.  I
 *  did not collaborate with anyone, and I did not use any
 *  resources beyond the standard course materials."
 **********************************************************************/
 * 
 * "I did not receive any help outside of TA office hours.  I
 * did not collaborate with anyone, and I did not use any
 * resources beyond the standard course materials."
 *
 *
/**********************************************************************
 *   Instructions on how to run the program               
 **********************************************************************/
 * Run the Game class with java Game. 
 * The Game class will call on Sudoku.java, 
 * which will call on SudokuSquare.java)
 * The only command line argument needed is the name of a text file,
 * which will be used for the input sudoku grid. 
 *
 *
 *
 * 
/**********************************************************************
 *  A brief description of each file and its purpose                   
 **********************************************************************/
 * 
 * SudokuSquare.java: 
 * The file creates the SudokuSquare object. 
 * A SudokuSquare has a value between 1-6, a boolean that checks if it has a value
 * (meaning if the square is a white space or not), 
 * and an x and y position for where the square is drawn on the grid. 
 * The file's draw function draws the square onto the grid seen by the player 
 * during a game. 
 *
 * Sudoku.java: 
 * The file creates a Sudoku object. 
 * Sudoku has a board (which is a 2D array of SudokuSquares), 
 * a seperate 2D array of permanent SudokuSquares that are the starting 
 * non white space squares constructed using the input text file, 
 * and a integer that represents the total number of permanent squares. 
 * The file creates a sudoku board by instantiating SudokuSquares,
 * draws the squares onto the player's screen, and can update the sudoku board              
 * by allowing players to type in a number and then click on a square  
 * to add the number to the sudoku board. 
 * The file also checks if a square can be changed and 
 * whether or not the current state of the board is valid. 
 * If a player inputs a value which directly violates a rule,
 * the file marks the contradictory values in red and              
 * highlights the region of the board causing an issue.             
 * If the value is changed to be a valid input, the class unhighlights the region.              
 * The file also checks if the user has won or not by checking whether               
 * or not the whole board is filled with valid values.              
 *              
 * Game.java:              
 * The file plays a Sudoku game. 
 * It draws the starting 6x6 grid seen by the user and checks if the 
 * input text file is valid. 
 * It reads the input text file and converts it into a 2D array of 
 * chars, which it uses to instantiate a Sudoku object.
 * It then calls on the Sudoku object to run the game until the player has won. 


