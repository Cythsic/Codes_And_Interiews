class Solution {
    public void rotate(int[][] matrix) {
        int n=matrix.length;
        for(int i=0; i<n; i++) {
            for(int j=i; j<n; j++) {
                swap(i,j,matrix);
            }
        }
        for(int[] row:matrix) {
            reverse(row);
        }
        return;
    }
    
    void swap(int i, int j, int[][] matrix) {
        int temp=matrix[i][j];
        matrix[i][j]=matrix[j][i];
        matrix[j][i]=temp;
        return;
    }
    
    void reverse(int[] row) {
        int n=row.length;
        for(int i=0; i<n/2; i++) {
            int temp=row[i];
            row[i]=row[n-1-i];
            row[n-1-i]=temp;
        }
    }
}