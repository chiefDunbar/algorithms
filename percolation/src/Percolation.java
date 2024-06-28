import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean[][] grid;
    private int n;
    private WeightedQuickUnionUF uf;
    private int openSitesNumber;
    private int topVirtual = 0;
    private int bottomVirtual;

    // row, col [0, n-1]
    // 0 means blocked, range [1, n]
    // 0th uf array element vtopsite n**2+1 element of uf vbottomsite
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        grid = new boolean[n][n];
        this.n = n;
        uf = new WeightedQuickUnionUF(n * n + 2);
        bottomVirtual = n * n + 1;
        openSitesNumber = 0;

    }

    // 0 * 5 + 4; 4 * 5 + 4 == 24
    private int getIndex(int row, int col) {
        return (row - 1) * n + col;
    }

    private void validateLocation(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n) {
            throw new IllegalArgumentException();
        }
    }

    // connect site to all it's adjacent open sites
    public void open (int row, int col) {
        validateLocation(row, col);
        if (!isOpen(row, col)){

            grid[row - 1][col - 1] = true;
            openSitesNumber++;
            int index = getIndex(row, col);

            if (row == 1) {
                uf.union(index, topVirtual);
            }
            if (row == n) {
                uf.union(index, bottomVirtual);
            }
            if (row > 1 && isOpen(row - 1, col)) {
                uf.union(index, getIndex(row - 1, col));
            }
            if (row < n && isOpen(row + 1, col)) {
                uf.union(index, getIndex(row + 1, col));
            }
            if (col > 1 && isOpen(row, col - 1)) {
                uf.union(index, getIndex(row, col - 1));
            }
            if (col < n && isOpen(row, col + 1)) {
                uf.union(index, getIndex(row, col + 1));
            }
        }


    }

    public boolean isOpen (int row, int col) {
        validateLocation(row, col);
        return grid[row - 1][col - 1];
    }

    // connected to virtual top site
    public boolean isFull (int row, int col) {
        validateLocation(row, col);
        if (uf.find(topVirtual) == uf.find(getIndex(row, col))) return true;
        return  false;
    }

    public int numberOfOpenSites (){
        return openSitesNumber;
    }

    // virtual top site connected to virtual bottom site
    public boolean percolates() {
        return uf.find(bottomVirtual) == uf.find(topVirtual);
    }

    public static void main(String[] args) {
    }
}
