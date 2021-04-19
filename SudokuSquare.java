/**
* Name: Debby Lin
* Pennkey: debbylin
* Execution: java SudokuSquare
*
* Description: This class creates a SudokuSquare object, which goes into a 
*              Sudoku. Each SudokuSquare has a value which is an integer between 
*              1 to 6, an x and y position on the drawn board, and a boolean
*              that checks if the square has a value or not.
*              White space squares are treated as having no value. 
*              The class can also draw the SudokuSquare onto the board that is 
*              seen by the user. 
*
**/
public class SudokuSquare {
    
    // the value of the SudokuSquare
    private int value;
    // if the SudokuSquare has a value 
    private boolean hasValue;
    // the x position of the SudokuSquare
    private double x;
    // the y position of the SudokuSquare
    private double y;
    
    /**
     * Inputs: Integer representing the value of the sudoku square from 1 - 6
     * Outputs: None 
     * Description: Constructor for a SudokuSquare with a value. 
    */
    public SudokuSquare(int value) {
        this.hasValue = true;
        this.value = value;
    }
    
    /**
     * Inputs: None
     * Outputs: None 
     * Description: Constructor for a SudokuSquare with an empty value. 
    */
    public SudokuSquare() {
        this.hasValue = false;
    }
    
    /**
     * Inputs: None
     * Outputs: Boolean true or false for whether or not the square has a value
     *          that is an integer value between 1-6 
     * Description: Getter function for the hasValue field of the SudokuSquare
    */
    public boolean getHasValue() {
        return this.hasValue;
    }
    
    /**
     * Inputs: Integer representing the value of the sudoku square from 1 - 6
     * Outputs: None 
     * Description: Setter function that sets the value field of the SudokuSquare
     *              equal to the input value. It also changes the hasValue 
     *              field to be true. 
    */
    public void setValue(int value) {
        this.value = value;
        if ((value == 1) || (value == 2) || (value == 3) || (value == 4) ||
        (value == 5) || (value == 6)) {
            this.hasValue = true;
        }
    }
    
    /**
     * Inputs: None
     * Outputs: Integer representing the value of the sudoku square from 1 - 6
     * Description: Getter function for the value field of the SudokuSquare
    */
    public int getValue() {
        return this.value;
    }
    
    /**
     * Inputs: None
     * Outputs: Double representing the x position of the drawn sudoku square  
     * Description: Getter function for the x field of the SudokuSquare
    */
    public double getXPos() {
        return this.x;
    }
    
    /**
     * Inputs: None
     * Outputs: Double representing the y position of the drawn sudoku square  
     * Description: Getter function for the y field of the SudokuSquare
    */
    public double getYPos() {
        return this.y;
    }
    
    /**
     * Inputs: Doubles x and y representing the coordinates for the drawing
     * Outputs: None
     * Description: This method takes in coordinate double values to use for
     *              the PennDraw method that draws the sudoku square on the board.
    */
    public void draw(double x, double y) {
        this.x = x;
        this.y = y;
        // Drawing a blank square
        if (!this.hasValue) {
            PennDraw.text(x, y, " ");
        }
        else {
            // drawing a square containing a 1-6 integer value
            String strVal = Integer.toString(this.value);
            PennDraw.text(x, y, strVal);
        }
        
    }
    
}
