/**
* Name: Debby Lin
* Pennkey: debbylin
* Execution: java Game
*
* Description: This class plays a Sudoku game. 
*              It draws the 6x6 grid seen by the user and checks if the 
*              input text file is valid (i.e. it is a 6x6 grid, it has 
*              characters outside of white spaces, 1, 2, 3, 4, 5, or 6, and  
*              it does not have repeating values in the same row, column, or 2 x 3
*              box). 
*              It reads the input text file and converts it into a 2D array of 
*              chars, which it uses to instantiate a Sudoku object.
*              It then calls on the Sudoku object to run the game. 
*
**/

// import java linkedList library
import java.util.LinkedList;

public class Game {
   
    /**
     * Inputs: An array of strings representing the command line arguments
     * Outputs: None
     * Description: The main method calls on other functions to play the game 
    */
    public static void main(String [] args) {
        // the first command line arguments is taken as the file name
        String fileName = args[0];
        // row variable
        int rowCount = 0;
        
        // create new 1D and 2D char arrays 
        char[] fileCharArray = new char[6];       
        char[][] fileTwoDArray = new char[6][6]; 
            
        // check if the textfile is a 6x6 grid with 36 valid chars
        if (is6x6(fileName)) {
            // read the text file and convert to a char array
            fileCharArray = fileToCharArray(fileName);
            // convert the 1D char array representation of the file into a 2D array
            fileTwoDArray = convertToTwoDArr(fileCharArray);
            // if there are repeating numbers in the file's rows and columns
            if (!checkFileBoxes(fileTwoDArray)) {
                throw new IllegalArgumentException("Text file contains repeating " +
                                                   "numbers in the same " +
                                                   "2 x 3 box");
            }
            if ((!checkFileColumns(fileTwoDArray)) &&
            (!checkFileRows(fileTwoDArray))) {
                throw new IllegalArgumentException("Text file contains repeating " +
                                                   "numbers in the same row " +
                                                   "and in the same column");
            }
            // if there are repeating numbers only in the file's columns
            else if (!checkFileColumns(fileTwoDArray)) {
                throw new IllegalArgumentException("Text file contains repeating " +
                                                   "numbers in the same column");
            }
            // if there are repeating numbers only in the file's rows
            else if (!checkFileRows(fileTwoDArray)) {
                throw new IllegalArgumentException("Text file contains repeating " +
                                                   "numbers in the same row ");
            }
            
        }
        
        // draw grid
        drawGrid();
        
        // construct the sudoku 
        Sudoku newSudoku = new Sudoku(fileTwoDArray); 
        
        // while loop
        while (true) {
            // check if the game has been won to exit the while loop
            if (newSudoku.hasWon()) {
                break;
            }
            // if user hasn't won, updateBoard based on user interactions
            newSudoku.updateBoard();
        }
        
    }
    
    
    
   /**
    * Inputs: None
    * Outputs: None
    * Description: This method clears the screen and draws the
    *              6 x 6 grid of the sudoku board and starting message
    *              using PennDraw functions
    */
    public static void drawGrid() {
        // adjust canvas size to 512 x 600 pixels
        PennDraw.setCanvasSize(512, 600);
        // Clear the screen
        PennDraw.clear();
        // set the pen color to black
        PennDraw.setPenColor();
        // set the font back to plain
        PennDraw.setFontPlain();
        // write the opening instructions to begin the game
        PennDraw.text(0.50, 0.95, "Click anywhere to begin");
        // draw the main box
        PennDraw.square(0.50, 0.25, 0.50);
        
        for (int i = 1; i < 6; i++) {
            // draw the vertical lines
            PennDraw.line(0.166 * i, 0, 0.166 * i, 0.75);
            // draw the horizontal lines
            PennDraw.line(0, 0.125 * i, 1, 0.125 * i);
            
        }
        
    }
    
    /**
     * Inputs: String representing the filename
     * Outputs: Boolean that returns true if the input is correctly formatted
     *          as a 6x6 grid with 36 chars
     * Description: This method reads the input sudoku grid from the text file
     *              line by line and then stores each row in an array of strings.
     *              It checks to see if each of the expected 6 rows have
     *              6 columns (meaning that the file is a 6x6 grid)
     *              and also checks if there are exactly 36 chars
     *              (not including new line chars)
    */
    public static boolean is6x6(String fileName) {
        // instantiate inStream to use the In object
        In inStream = new In(fileName);
        // array of strings representing the expected 6 rows of the file grid
        String[] fileRowArray = new String[6];
        // counter for the number of chars in the file that are not new lines
        int count = 0;
        
        // populate the array
        for (int i = 0; i < 6; i++) {
            // read the file line by line and store the rows into the array
            fileRowArray[i] = inStream.readLine();
            if (fileRowArray[i].length() != 6) {
                // error message if the rows are not all 6 columns long
                throw new IllegalArgumentException("invalid input file: is not " +
                                                   "a 6x6 grid");
            }
            // iterate through each of the rows
            for (int j = 0; j < 6; j++) {
                char c = fileRowArray[i].charAt(j);
                // check if a char is a new line char
                if (c != '\n') {
                    // increase the counter
                    count++;
                }
            }
        }
        //error message if the file does not contain exactly 36 valid chars
        if (count != 36) {
            throw new IllegalArgumentException("invalid input file: does not " +
                                               "contain exactly 36 valid " +
                                               "characters");
        }
        
        return true;
    }
    
    
    /**
     * Inputs: String representing the filename
     * Outputs: Array of chars representing the contents of the file
     * Description: This method reads the input sudoku grid from the text file by
     *              storing it as a string and then converting it into a 1D array
     *              of chars.
    */
    public static char[] fileToCharArray(String filename) {
        // instantiate inStream to use the In object
        In inStream = new In(filename);
        // 1D array of chars to store the 36 chars in the sudoku file
        char[] stringToCharArray = new char[36];
        // read the whole file and store its contents as a string
        String  s = inStream.readAll();
        // counter for the number of chars in the file that are not new lines
        int count = 0;
        // convert string to char array by iterating through the string
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // check if the char is a next line characters
            if (c != '\n') {
                // add char to the array
                stringToCharArray[count] = c;
                // increase the counter
                count++;
            }
        }
        
        // return the char array
        return stringToCharArray;
    }
    
    
    /**
     * Inputs: Char array representation of a text file's sudoku grid
     * Outputs: The input char array converted into a 2D char array
     * Description: This method converts the char array produced when reading the
     *               sudoku text file into a 2D char array.
    */
    public static char[][] convertToTwoDArr(char[] fileCharArray) {
        char[][] sudoku2DCharArray = new char[6][6];
        int count = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                char c = fileCharArray[count];
                sudoku2DCharArray[i][j] = c;
                count++;
            }
        }
        return sudoku2DCharArray;
        
    }
    
        /**
     * Inputs: An array of chars
     * Outputs: Boolean that returns true if the char array from the file
     *          is valid (meaning that there are no repeating numbers)
     * Description: This method checks if the array of chars taken
     *              from the input file contains any repeating
     *              non white space values.
    */
    public static boolean isFileArrayValid(char[] arr) {
        // create new linkedList of chars
        LinkedList<Character> list = new LinkedList<Character>();
        // iterate through the array
        for (int i = 0; i < arr.length; i++) {
            // check if a value is already in the list and is not a white space
            if (list.contains(arr[i]) && (arr[i] != ' ')) {
                return false;
            }
            else {
                // add the char to the list
                list.add(arr[i]);
            }
        }
        return true;
    }
    
    /**
     * Inputs: 2D array of sudoku squares representing the board
     * Outputs: Boolean that returns true if the all the rows from the input file
     *          are valid.
     * Description: The method checks to see if the rows of the input
     *              are valid or not by calling the isArrayValid method
     *              on each of them to check for repeating values.
    */
    public static boolean checkFileRows(char[][] fileTwoDArr) {
        // iterate through the 2D array to get the rows
        for (int i = 0; i < fileTwoDArr.length; i++) {
            // call the isArrayValid function to check if the row is valid
            if (!isFileArrayValid(fileTwoDArr[i])) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Inputs: 2D array of sudoku squares representing the board
     * Outputs: Boolean that returns true if the all the columns
     *          from the input file are valid.
     * Description: The method checks to see if the columns of the input
     *              are valid or not by calling the isArrayValid method
     *              on each of them to check for repeating values.
    */
    public static boolean checkFileColumns(char[][] fileTwoDArr) {
        // instantiate temporary char arrays representing the columns
        char[] col1 = new char[6];
        char[] col2 = new char[6];
        char[] col3 = new char[6];
        char[] col4 = new char[6];
        char[] col5 = new char[6];
        char[] col6 = new char[6];
        // iterate through the rows of each column
        for (int j = 0; j < fileTwoDArr.length; j++) {
            col1[j] = fileTwoDArr[j][0];
            col2[j] = fileTwoDArr[j][1];
            col3[j] = fileTwoDArr[j][2];
            col4[j] = fileTwoDArr[j][3];
            col5[j] = fileTwoDArr[j][4];
            col6[j] = fileTwoDArr[j][5];
        }
        // check if all the columns are valid
        if ((isFileArrayValid(col1)) && (isFileArrayValid(col2)) &&
            (isFileArrayValid(col3)) && (isFileArrayValid(col4)) &&
            (isFileArrayValid(col5)) && (isFileArrayValid(col6))) {
            return true;
        }
        
        return false;
    }
    
    
    /**
     * Inputs: A 2D array of chars representing the 2 x 3 boxes within the
     *         6 x 6 char array taken from the file
     * Outputs: A boolean that returns true if a 2 x 3 box has no repeating numbers
     * Description: This method checks if there are any repeating numbers in 
     *              a 2 x 3 box. If there are no repeats, then the box is valid. 
    */
    public static boolean isValidFileBox(char[][] box) {
        LinkedList<Character> list = new LinkedList<Character>();
        for (int i = 0; i < box.length; i++) {
            for (int j = 0; j < box[i].length; j++) {
                char c = box[i][j];
                // do not include white spaces or next line breaks 
                if ((c != ' ') && (c != '\n')) {
                    // check if repeated char 
                    if (list.contains(box[i][j])) {
                        return false;
                    }
                    else {
                        // add to list 
                        list.add(box[i][j]);
                    }
                }
            }
        }
        return true;
    }
    
    /**
     * Inputs: 2D array of chars representing the input text file
     * Outputs: Boolean that returns true if the file is valid
     *          and false if it is not.
     * Description: The method checks if the 2 x 3 boxes of the file are valid.
     *              The method also breaks up the 6x6 file into 2x3 boxes and then
     *              calls the isValidFileBox method on each of them 
     *              to check if it they are valid 
     *              (meaning that they do not have repeating values).
    */
    public static boolean checkFileBoxes(char[][] fileTwoDArr) {
        // create temporary 2 x 3 box
        char[][] temp = new char[2][3];
        //top left
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                temp[i][j] = fileTwoDArr[i][j];
            }
        }
        boolean isTopLeftValid = isValidFileBox(temp);
        
        //top right
        for (int i = 0; i < 2; i++) {
            for (int j = 3; j < 6; j++) {
                temp[i][j - 3] = fileTwoDArr[i][j];
            }
        }
        boolean isTopRightValid = isValidFileBox(temp);
        
        //middle left
        for (int i = 2; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                temp[i - 2][j] = fileTwoDArr[i][j];
            }
        }
        boolean isMiddleLeftValid = isValidFileBox(temp);
        
        //middle right
        for (int i = 2; i < 4; i++) {
            for (int j = 3; j < 6; j++) {
                temp[i - 2][j - 3] = fileTwoDArr[i][j];
            }
        }
        boolean isMiddleRightValid = isValidFileBox(temp);
        
        //bottom left
        for (int i = 4; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                temp[i - 4][j] = fileTwoDArr[i][j];
            }
        }
        boolean isBottomLeftValid = isValidFileBox(temp);
        
        //bottom right
        for (int i = 4; i < 6; i++) {
            for (int j = 3; j < 6; j++) {
                temp[i - 4][j - 3] = fileTwoDArr[i][j];
            }
        }
        boolean isBottomRightValid = isValidFileBox(temp);
        
        // if the boxes are all valid, return true
        if (isTopLeftValid && isTopRightValid && isMiddleLeftValid &&
            isMiddleRightValid && isBottomLeftValid && isBottomRightValid) {
                return true;
        }
        
        return false;
    }

    
}