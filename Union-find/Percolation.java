
public class Percolation {

    // creates n-by-n grid, with all sites initially blocked
    private int [][] matrice;
    private int n;
    public Percolation(int n) {
        if (n > 0) {
            this.n = n;
            matrice = new int[n+1][n+1];

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    matrice[i][j] = 0;
                }
            }
        } else {
            throw new IllegalArgumentException("La taille de la matrice doit être supérieure à zéro.");
        }
    }


    // opens the site (row, col) if it is not open already
    public void open(int row, int col){
      validateargument(row,col);
      matrice[row][col] = 1;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
        validateargument(row,col);
        return matrice[row][col] == 1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col){
        validateargument(row,col);
        return matrice[row][col] == 2;
    }

    // returns the number of open sites
    public int numberOfOpenSites(){
        int count = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (matrice[i][j]==1) {
                    count++;
                }
            }
        }
        return count;
    }

    // does the system percolate?
    public boolean percolates(){

    }
    private void validateargument(int row, int col){
        if (row < 1 || row > n || col < 1 || col > n) {
            throw new IllegalArgumentException("arguments invalides");
        }
    }

    // test client (optional)
    public static void main(String[] args){

    }
}