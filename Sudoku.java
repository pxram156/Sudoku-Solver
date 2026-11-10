


/**
 * Paramjot Sawhney
 * Validating Sudoku puzzles
 * This program validates Sudoku puzzles by checking rows, columns, and boxes.
 * fixes invalid sudoku puzzles into one that can be solved
 * from valid rows, columns, or boxes.
 */
public class Sudoku {

    public Sudoku() {
        // hard coded both invalid and valid Sudoku
        int [][] sudoku1 = {
                {1,2,3,4,5,6,7,8,9},
                {4,5,6,2,6,7,5,9,1},
                {7,8,9,1,8,1,5,3,7},
                {2,7,5,2,8,3,5,1,9},
                {3,3,6,3,1,7,6,6,3},
                {5,9,7,1,9,2,9,3,5},
                {6,9,7,5,1,9,9,2,7},
                {8,2,5,4,7,8,8,5,1},
                {9,5,4,1,9,3,8,7,8}
        };
        int[][] sudoku2 = {
                {1,9,8,7,6,5,4,3,2},
                {7,6,5,4,3,2,1,8,9},
                {4,3,2,1,9,8,6,5,7},
                {9,8,1,5,4,6,2,7,3},
                {2,7,6,9,1,3,8,4,5},
                {5,4,3,8,2,7,9,1,6},
                {6,1,7,3,8,9,5,2,4},
                {3,2,4,6,5,1,7,9,8},
                {8,5,9,2,7,4,3,6,1}
        };

        // all print statements
        System.out.println();
        System.out.println("Making an Invalid Puzzle:");
        PrintPuzzle(sudoku1);
        System.out.println("are the rows ok? "+ CheckRows(sudoku1));
        System.out.println("are the cols ok? " + CheckCols(sudoku1));
        System.out.println("are the boxs ok? "+ CheckBoxs(sudoku1));
        System.out.println();
        System.out.println("Making a Valid Puzzle:");
        PrintPuzzle(sudoku2);
        System.out.println("are the rows ok? "+ CheckRows(sudoku2));
        System.out.println("are the cols ok? " + CheckCols(sudoku2));
        System.out.println("are the boxs ok? "+ CheckBoxs(sudoku2));
        System.out.println();
        System.out.println("Here’s the Invalid Sudoku: ");
        PrintPuzzle(sudoku1);
        System.out.println("And here it’s fixed! ");
        PrintPuzzle(FixedPuzzle(sudoku1));
    }

    /**
     * Prints a Sudoku puzzle in a formatted grid with dividers.
     * @param arr the 2D int array representing the Sudoku grid
     */
    public void PrintPuzzle(int [][] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (i % 3 == 0) {   // if i modulo 3 equals to 0, print the divider
                System.out.println("-------------------------");
            }
            for (int j = 0; j < arr[i].length; j++) {
                if (j % 3 == 0) System.out.print("| "); // if j mod3 equals 0, print "|"
                System.out.print(arr[i][j]+ " "); // or else, print the value with a space next to it
            }
            System.out.println("|");
        }
        System.out.println("-------------------------");
    }

    /**
     * Checks if a single row is valid.
     * @param arr the Sudoku grid
     * @param row the row index to check
     * @return true if the row contains no duplicates and values are 1–9, false otherwise
     */
    private boolean CheckRow(int [][] arr, int row) {
        boolean[] Copy = new boolean[10]; // boolean to store boolean value of each index
        for (int i = 0; i < arr[row].length; i++) {
            int value = arr[row][i];
            if (value < 1 || value > 9) return false; // if out of index, invalid sudoku
            if (Copy[value]) return false;
            Copy[value] = true; // else, all values in row are valid, return true
        }
        return true;
    }

    /**
     * Checks if all rows in the Sudoku are valid.
     * @param arr the Sudoku grid
     * @return true if all rows are valid, false otherwise
     */
    private boolean CheckRows(int[][] arr) {
        for (int i=0; i<arr.length; i++) {
            if (!CheckRow(arr,i)) {
                return false; // if row is invalid, sudoku is invalid
            }
        }
        return true; // if all rows are valid, return true
    }

    /**
     * Checks if a single column is valid.
     * @param arr the Sudoku grid
     * @param cols the column index to check
     * @return true if the column contains no duplicates and values are 1–9, false otherwise
     */
    private boolean CheckCol(int[][] arr, int cols) {
        boolean[] Copy = new boolean[10];
        for (int i = 0; i < arr.length; i++) { // same thing as Check row just flipped
            int value = arr[i][cols];
            if (value < 1 || value > 9) return false;
            if (Copy[value]) return false;
            Copy[value] = true;
        }
        return true;
    }

    /**
     * Checks if all columns in the Sudoku are valid.
     * @param arr the Sudoku grid
     * @return true if all columns are valid, false otherwise
     */
    private boolean CheckCols(int [][] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (!CheckCol(arr, i)) {
                return false; // if column is invalid, sudoku is invalid
            }
        }
        return true; // if all columns are valid, return true
    }

    /**
     * Checks if a single 3x3 box is valid.
     * @param arr the Sudoku grid
     * @param box the box index (0–8)
     * @return true if the 3x3 box contains no duplicates and values are 1–9, false otherwise
     */
    private boolean CheckBox(int[][] arr, int box) {
        int startrow = (box / 3) * 3;
        int startcol = (box % 3) * 3; // formulas used to only focus on one box at a time
        boolean[] meow = new boolean[10]; // boolean array to store box values
        for (int r = startrow; r < startrow + 3; r++) {
            for (int c = startcol; c < startcol + 3; c++) {
                int value = arr[r][c];
                if (value < 1 || value > 9) return false;
                if (meow[value]) return false;
                meow[value] = true;
            }
        }
        return true;
    }

    /**
     * Checks if all 3x3 boxes in the Sudoku are valid.
     * @param arr the Sudoku grid
     * @return true if all boxes are valid, false otherwise
     */
    private boolean CheckBoxs(int[][] arr) {
        for (int box = 0; box < 9; box++) {
            if (!CheckBox(arr, box)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Fixes the Sudoku by only keeping valid rows.
     * @param arr the Sudoku grid
     * @return a new grid with valid rows copied, invalid rows replaced with 0s
     */
    public int[][] FixRows(int[][] arr) {
        int[][] fixed = new int[9][9]; // automatically filled with 0s
        for (int r = 0; r < arr.length; r++) {
            if (CheckRow(arr, r)) {
                for (int c = 0; c < arr[r].length; c++) {
                    fixed[r][c] = arr[r][c];  // copy each element in the row
                }
            }
            // if row is invalid, it stays all zeros
        }
        return fixed;
    }

    /**
     * Fixes the Sudoku by only keeping valid columns.
     * @param arr the Sudoku grid
     * @return a new grid with valid columns copied, invalid columns replaced with 0s
     */
    private int[][] fixCols(int[][] arr) {
        int[][] fixed = new int[9][9];
        for (int c = 0; c < arr.length; c++) {
            if (CheckCol(arr, c)) {
                for (int r = 0; r < arr.length; r++) {
                    fixed[r][c] = arr[r][c];  // copy each element in the column
                }
            }
        }
        return fixed;
    }

    /**
     * Fixes the Sudoku by only keeping valid 3x3 boxes.
     * @param arr the Sudoku grid
     * @return a new grid with valid boxes copied, invalid boxes replaced with 0s
     */
    private int[][] fixBoxs(int[][] arr) {
        int[][] fixed = new int[9][9];
        for (int box = 0; box < 9; box++) {
            if (CheckBox(arr, box)) { //if valid, use check method to copy data
                int startrow = (box / 3) * 3;
                int startcol = (box % 3) * 3;
                for (int r = startrow; r < startrow + 3; r++) {
                    for (int c = startcol; c < startcol + 3; c++) {
                        fixed[r][c] = arr[r][c];  // copy each element in the box
                    }
                }
            }
        }
        return fixed;
    }

    /**
     * Combines the results of FixRows, fixCols, and fixBoxs into one final puzzle.
     * A value is kept if it passes at least one of the three checks.
     * @param arr the Sudoku grid
     * @return a merged fixed Sudoku grid
     */
    private int[][] FixedPuzzle(int[][] arr) { //merge all fixed methods into one print method
        int[][] fixed = new int[9][9];
        int[][] rowsFixed = FixRows(arr);
        int[][] colsFixed = fixCols(arr);
        int[][] boxsFixed = fixBoxs(arr);

        //if any fixer kept a value, keep it in the final grid
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (rowsFixed[r][c] != 0) fixed[r][c] = rowsFixed[r][c];
                else if (colsFixed[r][c] != 0) fixed[r][c] = colsFixed[r][c];
                else if (boxsFixed[r][c] != 0) fixed[r][c] = boxsFixed[r][c];
                // else stays 0
            }
        }
        return fixed;
    }

    public static void main(String[] args) {
        new Sudoku();
    }
}