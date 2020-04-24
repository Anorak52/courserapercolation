import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class AlgCoursera {
    private boolean[][] grid; //0 - blocked 1 - open
    //create grid
    public AlgCoursera(int n){
        if (n <= 0)
            throw new java.lang.IllegalArgumentException("Nope, only > 0");

        grid = new boolean [n][n]; //initialize by the size

        for (int i =0; i < n; i++){  //set close state
            for (int j = 0; j < n; j++){
                grid[i][j] = false;
            }
        }


    }

    /*// opens the site (row, col) if it is not open already
    public void open(int row, int col)

    // is the site (row, col) open?
    public boolean isOpen(int row, int col)

    // is the site (row, col) full?
    public boolean isFull(int row, int col)

    // returns the number of open sites
    public int numberOfOpenSites()

    // does the system percolate?
    public boolean percolates()

    // test client (optional)
    public static void main(String[] args)*/
}