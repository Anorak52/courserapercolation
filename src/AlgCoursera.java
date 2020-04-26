import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class AlgCoursera {
    private boolean[][] grid; //0 - blocked 1 - open
    private WeightedQuickUnionUF unionfind;
    private WeightedQuickUnionUF unionfindBACK;
    private int size;
    private int n;
    private int numofopensites;
    //create grid
    public AlgCoursera(int n){
        if (n <= 0)
            throw new java.lang.IllegalArgumentException("Nope, only > 0");

        grid = new boolean [n][n]; //initialize by the size

        for (int i =0; i < n; ++i){  //set close state
            for (int j = 0; j < n; ++j){
                grid[i][j] = false;
            }
        }
        this.n = n;
        size = (n*n+2);
        unionfind = new WeightedQuickUnionUF(size); //create two points top and bottom
        unionfindBACK = new WeightedQuickUnionUF(n*n + 1); // one virtual node because of problem back wash

        for (int i = 1; i <= n; ++i) { //binding node with the points above and below
            unionfind.union(0, i); //union the pTop
            unionfind.union(size-1, n*n+1-i); //union the pBottom
            unionfindBACK.union(0, i);  //union the virtual node
        }
    }


    public void open(int row, int col){
        if(row>n || row<1 || col>n || col<1)
            throw new IndexOutOfBoundsException();
        // Indexes are from 1 to N while array indexes from 0 to N - 1
        grid[row-1][col-1] = true;
        numofopensites++;
        int idx = posToIndex(row, col);


        //test, if the points around is open then union them
        if (row > 1 && isOpen(row-1, col)) {
            unionfind.union(posToIndex(row-1, col), idx);
            unionfindBACK.union(posToIndex(row-1, col), idx);
        }
        if (row < n && isOpen(row+1, col)) {
            unionfind.union(posToIndex(row+1, col), idx);
            unionfindBACK.union(posToIndex(row+1, col), idx);
        }
        if (col > 1 && isOpen(row, col-1)) {
            unionfind.union(posToIndex(row, col-1), idx);
            unionfindBACK.union(posToIndex(row, col-1), idx);
        }
        if (col < n && isOpen(row, col+1)) {
            unionfind.union(posToIndex(row, col+1), idx);
            unionfindBACK.union(posToIndex(row, col+1), idx);
        }
    }

    public boolean isOpen(int row, int col){
        return grid[row-1][col-1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col){
        //return isOpen(i,j) && ufBack.connected(posToIndex(i,j), 0);
        if (isOpen(row, col)){
            int indx = posToIndex(row, col);
            int a = unionfindBACK.find(indx);
            int b = unionfindBACK.find(0);
            return a == b;
        }
        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites(){
        return numofopensites;
    }

    // does the system percolate?
    public boolean percolates(){
        boolean ret = false;
        if (n == 1){
            ret = isOpen(1, 1);
        }
        else{
            int set1 = unionfind.find(0);
            int set2 = unionfind.find(size-1);
            if (set1 == set2){
                ret = true;
            }
        }
        return ret;
    }

//    // test client (optional)
//    public static void main(String[] args){
//        AlgCoursera percolation = new AlgCoursera(4);
//        System.out.println(percolation.percolates());
//        percolation.open(2, 2);
//        percolation.open(3, 2);
//        System.out.println(percolation.percolates());
//        percolation.open(4, 2);
//        percolation.open(4, 3);
//        percolation.open(1, 2);
//        System.out.println(percolation.percolates());
//        System.out.println(percolation.isFull(4, 3));
//        System.out.println(percolation.numberOfOpenSites());
//        percolation.open(5,3);
//        System.out.println(percolation.percolates());
//    }

    private int posToIndex(int i, int j) {  // convert2dTo1dCoord
        return (i - 1) * n + j;
    }
}