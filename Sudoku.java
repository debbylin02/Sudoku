/**
* Name: Debby Lin
* Pennkey: debbylin
* Execution: java Sudoku
*
* Description: This class creates a Sudoku object, which goes into a Game. 
*              Each Sudoku has a board (which is a 2D array of SudokuSquares),
*              a seperate 2D array of permanent SudokuSquares (which are the 
*              starting squares from the input text file that are not white spaces),
*              and a integer that represents the total number of permanent 
*              squares that cannot be changed by the user.
*              The class creates a sudoku board by instantiating SudokuSquares,
*              and draws the squares onto the user's screen.   
*              The class updates the state of the sudoku board by allowing 
*              a user to type in a number and then click on a square to add 
*              it to the board. 
*              The class checks if a square can be changed and
*              whether or not the current state of the board is valid 
*              (meaning that there are no repeating numbers in the same 
*              2x3 box, column, or row).
*              If a player inputs a value which directly violates a rule,
*              the class marks the contradictory values in red and 
*              highlights the region of the board causing an issue.
*              If the value is changed to be a valid input, the class
*              unhighlights the region. 
*              The class checks if the user has won or not by checking whether  
*              or not the whole board is filled with valid values. 
**/

// import java linkedList library
import java.util.LinkedList;

public class Sudoku {
    
    // 2D array representing the board of sudoku squares
    private SudokuSquare[][] board;
    // array of sudoku squares that cannot be moved
    private SudokuSquare[] permanentSquares = new SudokuSquare[36];
    // counter for the number of permanentSquares
    private int numPerm = 0;
    
   /**
    * Inputs: Integer representing the value of the sudoku square from 1 - 6
    * Outputs: None 
    * Description: Constructor for a sudoku 
    */
    public Sudoku(char[][] fileTwoDArr) {
        this.board = makeSudokuBoard(fileTwoDArr);
    }
    

    /**
     * Inputs: 2D char array representing the input sudoku grid text file 
     * Outputs: None 
     * Description: This method makes a 2D sudoku square board using the 
     *              values from the 2D char array. It constructs sudoku squares 
     *              if the value of the char is either a white space or 1-6. 
    */
    public SudokuSquare[][] makeSudokuBoard(char[][] fileTwoDArr) {
        SudokuSquare[][] newBoard = new SudokuSquare[6][6];
        for (int i = 0; i < fileTwoDArr.length; i++) {
            for (int j = 0; j < fileTwoDArr[i].length; j++) {
                char c = fileTwoDArr[i][j];
                
                // check digit value
                if (c == ' ') {
                    // create square with no value
                    newBoard[i][j] = new SudokuSquare();
                    // draw
                    newBoard[i][j].draw(.08 + (0.166 * j), 0.6875 - (.125 * i));
                }
                else if (c == '1') {
                    newBoard[i][j] = new SudokuSquare(1);
                    // draw
                    newBoard[i][j].draw(.08 + (0.166 * j), 0.6875 - (.125 * i));
                    // add to array of permanent squares
                    this.permanentSquares[this.numPerm] = newBoard[i][j];
                    // increase number of permanent squares
                    this.numPerm++;
                }
                else if (c == '2') {
                    newBoard[i][j] = new SudokuSquare(2);
                    newBoard[i][j].draw(.08 + (0.166 * j), 0.6875 - (.125 * i));
                    // add to array of permanent squares
                    this.permanentSquares[this.numPerm] = newBoard[i][j];
                    // increase number of permanent squares
                    this.numPerm++;
                }
                else if (c == '3') {
                    newBoard[i][j] = new SudokuSquare(3);
                    newBoard[i][j].draw(.08 + (0.166666 * j), 0.6875 - (.125 * i));
                    // add to array of permanent squares
                    this.permanentSquares[this.numPerm] = newBoard[i][j];
                    // increase number of permanent squares
                    this.numPerm++;
                }
                else if (c == '4') {
                    newBoard[i][j] = new SudokuSquare(4);
                    newBoard[i][j].draw(.08 + (0.166 * j), 0.6875 - (.125 * i));
                    // add to array of permanent squares
                    this.permanentSquares[this.numPerm] = newBoard[i][j];
                    // increase number of permanent squares
                    this.numPerm++;
                }
                else if (c == '5') {
                    newBoard[i][j] = new SudokuSquare(5);
                    newBoard[i][j].draw(.08 + (0.166 * j), 0.6875 - (.125 * i));
                    // add to array of permanent squares
                    this.permanentSquares[this.numPerm] = newBoard[i][j];
                    // increase number of permanent squares
                    this.numPerm++;
                }
                else if (c == '6') {
                    newBoard[i][j] = new SudokuSquare(6);
                    newBoard[i][j].draw(.08 + (0.166 * j), 0.6875 - (.125 * i));
                    // add to array of permanent squares
                    this.permanentSquares[this.numPerm] = newBoard[i][j];
                    // increase number of permanent squares
                    this.numPerm++;
                }
                // error message if the file had chars that were invalid inputs
                else {
                    throw new IllegalArgumentException("Text file contains " +
                                                       "characters other than " +
                                                       "white spaces or " +
                                                       "integers between 1 and 6");
                }
            }
        }
        return newBoard; 
    }
    

    
    /**
     * Inputs: None 
     * Outputs: None 
     * Description: This method updates the sudoku board based on the user's 
     *              interactions. The user can fill one of the empty 
     *              sudoku squares by typing in a number and then clicking 
     *              on an empty box to insert it. If it is an invalid move 
     *              the method alerts the user and provides a
     *              meaningful error message. 
    */
    public void updateBoard() {
        //  check if mouse has been clicked
        if (PennDraw.mousePressed()) {
            //get and store the coordinates of the mouse cursor
            double x = PennDraw.mouseX();
            double y = PennDraw.mouseY();
            
            // write instructional message to the user
            PennDraw.setPenColor(PennDraw.WHITE);
            PennDraw.filledRectangle(0.5, 0.95, 0.5, 0.05);
            PennDraw.setPenColor();
            PennDraw.text(0.50, 0.95, "Type a number between 1-6 " +
                          "and then click on a box to insert it");
            
            //row and column value for new sudoku square
            int row = 0;
            int column = 0;
            
            // y coordinate
            if ((0 <= y) && (y < 0.125)) {
                row = 5;
            }
            else if ((0.125 <= y) && (y < 0.25)) {
                row = 4;
            }
            else if ((0.25 <= y) && (y < 0.375)) {
                row = 3;
            }
            else if ((0.375 <= y) && (y < 0.5)) {
                row = 2;
            }
            else if ((0.5 <= y) && (y < 0.625)) {
                row = 1;
            }
            else if ((0.625 <= y) && (y < 0.75)) {
                row = 0;
            }
            
            // x coordinate
            if ((0 <= x) && (x < 0.166)) {
                column = 0;
            }
            else if ((0.166 <= x) && (x < 0.332)) {
                column = 1;
            }
            else if ((0.332 <= x) && (x < 0.498)) {
                column = 2;
            }
            else if ((0.498 <= x) && (x < 0.664)) {
                column = 3;
            }
            else if ((0.664 <= x) && (x < 0.830)) {
                column = 4;
            }
            else if ((0.830 <= x) && (x < 1)) {
                column = 5;
            }
            
            // check if key has been typed
            if (PennDraw.hasNextKeyTyped()) {
                // clear the previous message
                PennDraw.setPenColor(PennDraw.WHITE);
                PennDraw.filledRectangle(0.5, 0.88, 0.5, 0.05);
                
                //retrieve key
                char c = PennDraw.nextKeyTyped();
                
                // cast input to an int
                int input = (int) (c - 48);
                
                // check if the square can be changed
                if (canChangeSquare(this.board[row][column])) {
                    // set value in board
                    this.board[row][column].setValue(input);
                    // display number typed message to the user
                    PennDraw.setPenColor();
                    PennDraw.text(0.50, 0.88, "The number typed is " + input);
                    
                    // check if the rows, columns, and boxes are valid
                    if (checkBoard()) {
                        // clear previous messages and sudoku square
                        PennDraw.setPenColor(PennDraw.WHITE);
                        PennDraw.filledRectangle(.08 + (0.166 * column), 
                                                 0.6875 - (.125 * row), 0.03, 0.03);
                        PennDraw.filledRectangle(0.5, 0.80, 0.5, 0.05);
                        // unhighlight the grid and boxes
                        unhighlightError(this.board[row][column]);
                        highlightBoxes();
                        // add the number to the grid
                        PennDraw.setFontBold();
                        PennDraw.setPenColor(PennDraw.GREEN);
                        // draw the new square
                        this.board[row][column].draw(.08 + (0.166 * column),
                                                     0.6875 - (.125 * row));
                        // reset pen and font
                        PennDraw.setPenColor();
                        PennDraw.setFontPlain();
                    }
                    
                    // if the board is not valid
                    else if (!checkBoard()) {
                        // clear the sudoku square and messages
                        PennDraw.setPenColor(PennDraw.WHITE);
                        PennDraw.filledRectangle(0.5, 0.80, 0.5, 0.05);
                        PennDraw.filledRectangle(.08 + (0.166 * column), 
                                                 0.6875 - (.125 * row), 0.03, 0.03);
                        // unhighlight the grid
                        unhighlightError(this.board[row][column]);
                        highlightBoxes();
                        // add the number to the grid
                        PennDraw.setFontBold();
                        PennDraw.setPenColor(PennDraw.RED);
                        this.board[row][column].draw(.08 + (0.166 * column), 
                                                     0.6875 - (.125 * row));
                        // reset font and write invalid message to the user
                        PennDraw.setFontPlain();
                        PennDraw.text(0.50, 0.80, "Invalid number: please " +
                                      "choose a new number before proceeding");
                        // highlight the grid
                        highlightError(this.board[row][column]);
                        highlightBoxes();
                        // reset pen color
                        PennDraw.setPenColor();
                        
                    }
                    
                }
                // if user tries to change a permanent square
                else if (!canChangeSquare(this.board[row][column])) {
                    // clear message
                    PennDraw.setPenColor(PennDraw.WHITE);
                    PennDraw.filledRectangle(0.5, 0.80, 0.5, 0.05);
                    // write unchangeable message to user
                    PennDraw.setPenColor(PennDraw.RED);
                    PennDraw.text(0.50, 0.80, "Cannot change this square");
                }
                
            }
        }
    }
    
    /**
     * Inputs: None 
     * Outputs: Boolean that returns true if the user has won and false otherwise
     * Description: The method checks to see if the user has won based on
     *              whether or not the board is valid and all 36 squares
     *              are filled. If the user has won, the method draws the
     *              victory message
    */
    public boolean hasWon() {
        // number of full sudoku squares in the board
        int numFull = 0;
        
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                if (this.board[i][j].getHasValue()) {
                    numFull++;
                }
            }
        }
        // if board is valid and there are 36 full squares, return true
        if (checkBoard() && (numFull == 36)) {
            // draw the victory message
            PennDraw.setPenColor(PennDraw.WHITE);
            PennDraw.filledRectangle(0.5, 0.85, 0.5, 0.05);
            PennDraw.setPenColor(PennDraw.GREEN);
            PennDraw.text(0.50, 0.85, "You win!");
            // return hasWon as true
            return true;
        }
        
        return false;
    }
    
    /**
     * Inputs: A 2D array of sudoku squares representing the 2 x 3 boxes within the
     *         6 x 6 sudoku
     * Outputs: A boolean that checks if the box is valid by returning 
     *          true if there are no repeating numbers within it. o 
     * Description: This method checks if there are any repeating digits 
     *              in a 2 x 3 box of sudoku squares. If there are no repeats,
     *              the box is valid and the method returns true. 
    */
    public boolean isValidBox(SudokuSquare[][] box) {
        LinkedList<Integer> list = new LinkedList<Integer>();
        for (int i = 0; i < box.length; i++) {
            for (int j = 0; j < box[i].length; j++) {
                if (box[i][j].getHasValue()) {
                    // check for repeating values already in the list
                    if (list.contains(box[i][j].getValue())) {
                        return false;
                    }
                    else {
                        // add to list 
                        list.add(box[i][j].getValue());
                    }
                }
            }
        }
        return true;
    }
    

    /**
     * Inputs: An array of sudoku squares 
     * Outputs: Boolean that returns true if the sudoku square array 
     *          is valid (meaning that there are no repeating numbers)
     * Description: This method checks if the array of sudoku squares taken
     *              from the board contains any repeating
     *              non white space values.
    */
    public boolean isArrayValid(SudokuSquare[] arr) {
        // create new linkedList of sudoku squares
        LinkedList<Integer> list = new LinkedList<Integer>();
        // iterate through the array
        for (int i = 0; i < arr.length; i++) {
            // check if a value is already in the list
            if (list.contains(arr[i].getValue()) && (arr[i].getValue() != 0)) {
                return false;
            }
            else {
                // add to list 
                list.add(arr[i].getValue());
            }
        }
        return true;
    }
    
    /**
     * Inputs: The 6x6 sudoku square 2D array board and the target square s
     * Outputs: None
     * Description: This method highlights the row and column that
     *              the target square is a part of with yellow squares.
     *              If a square has the same value as s and is in the same
     *              row or column, it gets written in red.
    */
    public void highlightError(SudokuSquare s) {
        // get the column index of the board that contains the error
        int errorColumn = findErrorColumn();
        // get the row index of the board that contains the error
        int errorRow = findErrorRow();
        /*
        * check if the errorColumn is the default value -1
        * if it is not, then there is a column with an error
        */
        if (errorColumn != -1) {
            // find the the squares in the errorColumn
            for (int i = 0; i < this.board.length; i++) {
                // get the x and y positions of the squares in the column
                double x = this.board[i][errorColumn].getXPos();
                double y = this.board[i][errorColumn].getYPos();
                // highlight the squares with yellow rectangles
                PennDraw.setPenColor(PennDraw.YELLOW);
                PennDraw.filledRectangle(x, y, 0.03, 0.03);
                // rewrite the square values
                PennDraw.setPenColor();
                this.board[i][errorColumn].draw(x, y);
                // check if s and the square in the column have the same value
                if (this.board[i][errorColumn].getValue() == s.getValue()) {
                    PennDraw.setPenColor(PennDraw.RED);
                    this.board[i][errorColumn].draw(x, y);
                }
            }
        }
        /*
        * check if the errorRow is the default value -1
        * if it is not, then there is a row with an error
        */
        if (errorRow != -1) {
            // find the the squares in the errorRow
            for (int j = 0; j < this.board[0].length; j++) {
                // get the x and y positions of the squares in the row
                double x = this.board[errorRow][j].getXPos();
                double y = this.board[errorRow][j].getYPos();
                // highlight the squares with yellow rectangles
                PennDraw.setPenColor(PennDraw.YELLOW);
                PennDraw.filledRectangle(x, y, 0.03, 0.03);
                // rewrite the square values
                PennDraw.setPenColor();
                this.board[errorRow][j].draw(x, y);
                // check if s and the square in the row have the same value
                if (board[errorRow][j].getValue() == s.getValue()) {
                    PennDraw.setPenColor(PennDraw.RED);
                    this.board[errorRow][j].draw(x, y);
                }
            }
        }
        
    }
    
    /**
     * Inputs: The 6x6 sudoku square 2D array board and the target square s
     * Outputs: None
     * Description: This method unhighlights the squares in the same 
     *              row and column that the target square is a part of.             
    */
    public void unhighlightError(SudokuSquare s) {
        // row and column indices of square s
        int row = 0;
        int column = 0;
        //find the indices of SudokuSquare s in the board array
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[0].length; j++) {
                // if found s in the board
                if (this.board[i][j] == s) {
                    // set row and column values
                    row = i;
                    column = j;
                }
            }
        }
        
        // iterate through the rows of the target column
        for (int i = 0; i < this.board.length; i++) {
            // get the x and y position of the sudoku squares in the column
            double x = this.board[i][column].getXPos();
            double y = this.board[i][column].getYPos();
            
            // clear the square
            PennDraw.setPenColor(PennDraw.WHITE);
            PennDraw.filledRectangle(x, y, 0.035, 0.035);
            
            // redraw the sudoku square in black
            PennDraw.setPenColor();
            board[i][column].draw(x, y);
        }
        
        // iterate through the columns of the target row
        for (int j = 0; j < this.board[0].length; j++) {
            // get the x and y position of the sudoku squares in the row
            double x = this.board[row][j].getXPos();
            double y = this.board[row][j].getYPos();
            
            // clear the square
            PennDraw.setPenColor(PennDraw.WHITE);
            PennDraw.filledRectangle(x, y, 0.035, 0.035);
            
            // redraw the sudoku square in black
            PennDraw.setPenColor();
            board[row][j].draw(x, y);
        }
        
    }
    
    /**
     * Inputs: None 
     * Outputs: Boolean that returns true if the rows of the board are valid
     *          and false if they are not.
     * Description: The method checks each of the rows of the board by calling
     *              the isArrayValid method on them to see if there are any
     *              repeating numbers.
    */
    public boolean checkRows() {
        // get the rows of the board
        for (int i = 0; i < this.board.length; i++) {
            // call the isArrayValid function on the row
            if (!isArrayValid(this.board[i])) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Inputs: None 
     * Outputs: Integer value representing the index of the row in the board
     *          that has a repeating value
     * Description: The method checks each of the rows of the board by calling
     *              the isArrayValid method on them to see if there are any
     *              repeating numbers. If a row is not valid, the method
     *              returns the row index.
    */
    public int findErrorRow() {
        // get the rows of the board
        for (int i = 0; i < this.board.length; i++) {
            // call the isArrayValid function
            if (!isArrayValid(this.board[i])) {
                // return the row index of the errorRow
                return i;
            }
        }
        // no error, return default
        return -1;
    }
    
    /**
     * Inputs: None 
     * Outputs: Integer value representing the index of the column in the board
     *          that has a repeating value
     * Description: The method checks each of the columns of the board by calling
     *              the isArrayValid method on them to see if there are any
     *              repeating numbers. If a column is not valid, the method
     *              returns the column index.
    */
    public int findErrorColumn() {
        // create temporary columns representing the columns of the board
        SudokuSquare[] col1 = new SudokuSquare[6];
        SudokuSquare[] col2 = new SudokuSquare[6];
        SudokuSquare[] col3 = new SudokuSquare[6];
        SudokuSquare[] col4 = new SudokuSquare[6];
        SudokuSquare[] col5 = new SudokuSquare[6];
        SudokuSquare[] col6 = new SudokuSquare[6];
        
        // iterate through the rows of the column to fill the temp arrays
        for (int j = 0; j < this.board.length; j++) {
            col1[j] = this.board[j][0];
            col2[j] = this.board[j][1];
            col3[j] = this.board[j][2];
            col4[j] = this.board[j][3];
            col5[j] = this.board[j][4];
            col6[j] = this.board[j][5];
        }
        
        // if the column is not valid, return the column index in the board
        if (!isArrayValid(col1)) {
            return 0;
        }
        
        else if (!isArrayValid(col2)) {
            return 1;
        }
        
        else if (!isArrayValid(col3)) {
            return 2;
        }
        
        else if (!isArrayValid(col4)) {
            return 3;
        }
        
        else if (!isArrayValid(col5)) {
            return 4;
        }
        else if (!isArrayValid(col6)) {
            return 5;
        }
        
        // no error column, return default
        return -1;
    }
    
    /**
    * Inputs: None 
    * Outputs: Boolean that returns true if the columns of the board are valid
    *          and false if they are not.
    * Description: The method checks each of the columns of the board by calling
    *              the isArrayValid method on them to see if there are any
    *              repeating numbers.
    */
    public boolean checkColumns() {
        // create temporary columns representing the columns of the board
        SudokuSquare[] col1 = new SudokuSquare[6];
        SudokuSquare[] col2 = new SudokuSquare[6];
        SudokuSquare[] col3 = new SudokuSquare[6];
        SudokuSquare[] col4 = new SudokuSquare[6];
        SudokuSquare[] col5 = new SudokuSquare[6];
        SudokuSquare[] col6 = new SudokuSquare[6];
        // iterate through the rows of each column
        for (int j = 0; j < board.length; j++) {
            col1[j] = this.board[j][0];
            col2[j] = this.board[j][1];
            col3[j] = this.board[j][2];
            col4[j] = this.board[j][3];
            col5[j] = this.board[j][4];
            col6[j] = this.board[j][5];
        }
        
        // check if all columns are valid
        if (isArrayValid(col1) && isArrayValid(col2) && isArrayValid(col3) &&
            isArrayValid(col4) && isArrayValid(col5) && isArrayValid(col6)) {
            return true;
        }
        
        return false;
        
    }
    
    /**
     * Inputs: None 
     * Outputs: None
     * Description: The method breaks up the 6 x 6 board into 2 x 3 boxes and then
     *              calls the isValidBox method on each of them to check if it they
     *              are valid (meaning that they do not have repeating values.
     *              If a box is not valid, the method outlines it in red.
     *              If a box is valid, the method "unhighlights" it by
     *              redrawing it in black.
    */
    public void highlightBoxes() {
        // create temporary 2 x 3 box
        SudokuSquare[][] temp = new SudokuSquare[2][3];
        
        //top left
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                temp[i][j] = this.board[i][j];
            }
        }
        boolean isTopLeftValid = isValidBox(temp);
        // highlight the top left box if the box is not valid
        if (!isTopLeftValid) {
            PennDraw.setPenColor(PennDraw.RED);
            PennDraw.rectangle(0.249, .625, 0.25, 0.125);
        }
        // unhighlight the top left box if the box is valid
        if (isTopLeftValid) {
            PennDraw.setPenColor();
            PennDraw.rectangle(0.249, .625, 0.25, 0.125);
        }
        
        //top right
        for (int i = 0; i < 2; i++) {
            for (int j = 3; j < 6; j++) {
                temp[i][j - 3] = this.board[i][j];
            }
        }
        boolean isTopRightValid = isValidBox(temp);
        // highlight the top right box if the box is not valid
        if (!isTopRightValid) {
            PennDraw.setPenColor(PennDraw.RED);
            PennDraw.rectangle(0.751, .625, 0.25, 0.125);
        }
        // unhighlight the top right box if the box is valid
        if (isTopRightValid) {
            PennDraw.setPenColor();
            PennDraw.rectangle(0.751, .625, 0.25, 0.125);
        }
        
        //middle left
        for (int i = 2; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                temp[i - 2][j] = this.board[i][j];
            }
        }
        boolean isMiddleLeftValid = isValidBox(temp);
        // highlight the middle left box if the box is not valid
        if (!isMiddleLeftValid) {
            PennDraw.setPenColor(PennDraw.RED);
            PennDraw.rectangle(0.249, .375, 0.25, 0.125);
        }
        // unhighlight the middle left box if the box is valid
        if (isMiddleLeftValid) {
            PennDraw.setPenColor();
            PennDraw.rectangle(0.249, .375, 0.25, 0.125);
        }
        
        //middle right
        for (int i = 2; i < 4; i++) {
            for (int j = 3; j < 6; j++) {
                temp[i - 2][j - 3] = this.board[i][j];
            }
        }
        boolean isMiddleRightValid = isValidBox(temp);
        // highlight the middle right box if the box is not valid
        if (!isMiddleRightValid) {
            PennDraw.setPenColor(PennDraw.RED);
            PennDraw.rectangle(0.751, .375, 0.25, 0.125);
        }
        // unhighlight the middle right box if the box is valid
        if (isMiddleRightValid) {
            PennDraw.setPenColor();
            PennDraw.rectangle(0.751, .375, 0.25, 0.125);
        }
        
        //bottom left
        for (int i = 4; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                temp[i - 4][j] = this.board[i][j];
            }
        }
        boolean isBottomLeftValid = isValidBox(temp);
        // highlight the bottom left box if the box is not valid
        if (!isBottomLeftValid) {
            PennDraw.setPenColor(PennDraw.RED);
            PennDraw.rectangle(0.249, .125, 0.25, 0.125);
        }
        // unhighlight the bottom left box if the box is valid
        if (isBottomLeftValid) {
            PennDraw.setPenColor();
            PennDraw.rectangle(0.249, .125, 0.25, 0.125);
        }
        
        //bottom right
        for (int i = 4; i < 6; i++) {
            for (int j = 3; j < 6; j++) {
                temp[i - 4][j - 3] = this.board[i][j];
            }
        }
        boolean isBottomRightValid = isValidBox(temp);
        // highlight the bottom right box if the box is not valid
        if (!isBottomRightValid) {
            PennDraw.setPenColor(PennDraw.RED);
            PennDraw.rectangle(0.751, .125, 0.25, 0.125);
        }
        // unhighlight the bottom right box if the box is valid
        if (isBottomRightValid) {
            PennDraw.setPenColor();
            PennDraw.rectangle(0.751, .125, 0.25, 0.125);
        }
        
    }
    
    /**
     * Inputs: None 
     * Outputs: Boolean that returns true if the board is valid
     *          and false if it is not.
     * Description: The method checks if the rows, columns, and 2 x 3 boxes of the
     *              board are valid. It calls on checkColumns and checkRows
     *              to check if the columns and rows are valid.
     *              The method also breaks up the 6x6 board into 2 x 3 boxes and 
     *              calls the isValidBox method on each of them to check if it they
     *              are valid (meaning that they do not have repeating values).
    */
    public boolean checkBoard() {
        // create temporary 2 x 3 box
        SudokuSquare[][] temp = new SudokuSquare[2][3];
        // check columns
        boolean areColumnsValid = checkColumns();
        // check rows
        boolean areRowsValid = checkRows();
        //top left
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                temp[i][j] = this.board[i][j];
            }
        }
        boolean isTopLeftValid = isValidBox(temp);
        
        //top right
        for (int i = 0; i < 2; i++) {
            for (int j = 3; j < 6; j++) {
                temp[i][j - 3] = this.board[i][j];
            }
        }
        boolean isTopRightValid = isValidBox(temp);
        
        //middle left
        for (int i = 2; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                temp[i - 2][j] = this.board[i][j];
            }
        }
        boolean isMiddleLeftValid = isValidBox(temp);
        
        //middle right
        for (int i = 2; i < 4; i++) {
            for (int j = 3; j < 6; j++) {
                temp[i - 2][j - 3] = this.board[i][j];
            }
        }
        boolean isMiddleRightValid = isValidBox(temp);
        
        //bottom left
        for (int i = 4; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                temp[i - 4][j] = this.board[i][j];
            }
        }
        boolean isBottomLeftValid = isValidBox(temp);
        
        //bottom right
        for (int i = 4; i < 6; i++) {
            for (int j = 3; j < 6; j++) {
                temp[i - 4][j - 3] = this.board[i][j];
            }
        }
        boolean isBottomRightValid = isValidBox(temp);
        
        // if the rows, columns, and boxes are all valid, return true
        if (isTopLeftValid && isTopRightValid && isMiddleLeftValid && 
            isMiddleRightValid && isBottomLeftValid && isBottomRightValid &&
            areColumnsValid && areRowsValid) {
            return true;
        }
        
        return false;
    }
    
    /**
     * Inputs: The sudoku square that the user wants to change
     * Outputs: Boolean that returns true if the target square
     *          can be changed and false if it cannot.
     * Description: This method checks if the target sudoku square
     *              is one of the starting permanent squares that is
     *              filled with a value between 1-6.
     *              If it is, then the method returns false, meaning
     *              that the target square cannot be changed.
    */
    public boolean canChangeSquare(SudokuSquare targetSquare) {
        // cannot change permanent squares from the starting file
        for (int i = 0; i < numPerm; i++) {
            if (this.permanentSquares[i] == targetSquare) {
                return false;
            }
        }
        return true;
    }
    

    
}

