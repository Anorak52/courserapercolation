import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats {
    private double [] res;  //answer mas;
    private int n;  //size
    private int t;  //repeat the experiment T times;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials){
        if (n <= 0 || trials <= 0)
            throw new java.lang.IllegalArgumentException();

        res = new double[trials];
        Percolation per;
        for (int step = 0; step < trials; ++step) { //repeat the exp T times
            per = new Percolation(n);
            int i, j, openNum = 0;
            while (!per.percolates()) {
                i = StdRandom.uniform(1, n+1);
                j = StdRandom.uniform(1, n+1);
                if (per.isOpen(i,j))
                    continue;
                per.open(i,j);
                openNum++;
            }

            res[step] = openNum / (n*n);
        }


    }

    // sample mean of percolation threshold
    public double mean(){
        return StdStats.mean(res);
    }

    // sample standard deviation of percolation threshold
    public double stddev(){
        return StdStats.stddev(res);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo(){
        return mean() - 1.96 * stddev() / Math.sqrt(t);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi(){
        return mean() + 1.96 * stddev() / Math.sqrt(t);
    }

    // test client (see below)
    public static void main(String[] args){
//        int n = Integer.parseInt(args[0]);
//        int t = Integer.parseInt(args[1]);
//        PercolationStats ps = new PercolationStats(n, t);
//        StdOut.printf("mean                    = %f%n", ps.mean());
//        StdOut.printf("stddev                  = %f%n", ps.stddev());
//        StdOut.printf("95%% confidence interval = %f, %f%n", ps.confidenceLo(), ps.confidenceHi());
        PercolationStats percolationStats = new PercolationStats(20,100);

        System.out.println("mean="+ percolationStats.mean());
        System.out.println("stddev="+ percolationStats.stddev());
        System.out.println("95%% confidence Interval="+percolationStats.confidenceLo()+"  "+ percolationStats.confidenceHi());
    }
}
