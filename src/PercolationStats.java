import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double [] res;  //answer mas;
    private int n;  //size
    private int t;  //repeat the experiment T times;
//    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials){
        if (n <= 0 || t <= 0)
            throw new java.lang.IllegalArgumentException();

        res = new double[t];


    }

//    // sample mean of percolation threshold
    public double mean(){
        return StdStats.mean(res);
    }
//
//    // sample standard deviation of percolation threshold
    public double stddev(){
        return StdStats.stddev(res);
    }
//
//    // low endpoint of 95% confidence interval
//    public double confidenceLo()
//
//    // high endpoint of 95% confidence interval
//    public double confidenceHi()
//
//    // test client (see below)
//    public static void main(String[] args)
}
