import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private static final double CONFIDENCE_95 = 1.96;
    private double[] open_site_fractions;

    // fill up fraction array by conducting trials
    public PercolationStats (int n , int trials) {

        // if invalid parameters -> end
        if (n <= 0 || trials <= 0) throw new IllegalArgumentException("Invalid Parameters!");

        // fractions for percolated grids in T trials
        open_site_fractions = new double[trials];

        for (int t = 0; t < trials; t++) {
            // create grid for current trial
            Percolation percolation = new Percolation(n);
            // record number of open sites then it finally percolates
            int count;
            // run until it percolates
            while (true) {
                // select random row, col and set them if not already open, else skip
                int row = StdRandom.uniformInt(1, n + 1);
                int col = StdRandom.uniformInt(1, n + 1);
                if (percolation.isOpen(row, col)) {
                    continue;
                }
                // open site
                percolation.open(row, col);
                // if percolating now then break and record number of currently open sites
                if (percolation.percolates()) {
                    count = percolation.numberOfOpenSites();
                    break;
                }
            }
            // fraction of open sites at time of percolation and total sites
            open_site_fractions[t]  = (double)count / (n * n);
        }
    }

    public double mean() {
        return StdStats.mean(open_site_fractions);
    }

    public double stddev () {
        return StdStats.stddev(open_site_fractions);
    }

    public double confidenceLo() {
        return mean() - ((CONFIDENCE_95 * (stddev())) / Math.sqrt(open_site_fractions.length));
    }

    public double confidenceHi() {
        return mean() + ((CONFIDENCE_95 * (stddev())) / Math.sqrt(open_site_fractions.length));
    }

    public static void main(String[] args) {
        PercolationStats ps = new PercolationStats(30, 300);

        String confidence = ps.confidenceLo() + ", " + ps.confidenceHi();
        StdOut.println("mean                    = " + ps.mean());
        StdOut.println("stddev                  = " + ps.stddev());
        StdOut.println("95% confidence interval = " + confidence);

    }
}
