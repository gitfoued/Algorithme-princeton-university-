/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private static final int top = 0;
    private int size;
    private boolean[][] opened;
    private int openedsite;
    private int bottom;
    private final WeightedQuickUnionUF uf;
    private final WeightedQuickUnionUF ufFull;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException("n must be greater than 0");
        size = n;
        opened = new boolean[n][n];
        openedsite = 0;
        bottom = n * n + 1;
        uf = new WeightedQuickUnionUF(n * n + 2);
        ufFull = new WeightedQuickUnionUF(n * n + 1);
    }

    private int xyTo1D(int row, int col) {
        if (row < 1 || row > size || col < 1 || col > size)
            throw new IllegalArgumentException("row and col must be within valid range");
        return (row - 1) * size + (col - 1) + 1;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (!isOpen(row, col)) {
            opened[row - 1][col - 1] = true;
            openedsite++;
            int index = xyTo1D(row, col);

            if (row == 1) {
                uf.union(top, index);
                ufFull.union(top, index);
            }
            if (row == size) {
                uf.union(bottom, index);
            }

            connectToNeighbors(row, col, index);
        }
    }

    private void connectToNeighbors(int row, int col, int index) {
        if (row > 1 && isOpen(row - 1, col)) { // connect to top neighbor
            uf.union(index, xyTo1D(row - 1, col));
            ufFull.union(index, xyTo1D(row - 1, col));
        }
        if (row < size && isOpen(row + 1, col)) { // connect to bottom neighbor
            uf.union(index, xyTo1D(row + 1, col));
            ufFull.union(index, xyTo1D(row + 1, col));
        }
        if (col > 1 && isOpen(row, col - 1)) { // connect to left neighbor
            uf.union(index, xyTo1D(row, col - 1));
            ufFull.union(index, xyTo1D(row, col - 1));
        }
        if (col < size && isOpen(row, col + 1)) { // connect to right neighbor
            uf.union(index, xyTo1D(row, col + 1));
            ufFull.union(index, xyTo1D(row, col + 1));
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 1 || row > size || col < 1 || col > size)
            throw new IllegalArgumentException("row and col must be within valid range");
        return opened[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row < 1 || row > size || col < 1 || col > size)
            throw new IllegalArgumentException("row and col must be within valid range");
        return ufFull.connected(top, xyTo1D(row, col));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openedsite;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.connected(top, bottom);
    }
}
