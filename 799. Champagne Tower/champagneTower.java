/*Solution 1: Simulation
The exceed champagne can be calculated by poured into this glass minus 1
And the left glass and right glass in the next row can receive equally, so just divide the exceed by 2 and accumulate it
*/

//TC: O(R^2) -- R is the number of rows
//SC: O(R^2)

class Solution {
    public double champagneTower(int poured, int query_row, int query_glass) {
        double[][] map = new double[query_row+2][query_row+2];
        map[0][0] = (double) poured;
        
        for(int i=0; i<=query_row; i++) {
            for(int j=0; j<=query_row; j++) {
                double curr = map[i][j];
                if(curr-1>0) {
                    map[i+1][j] += (curr-1)/2;
                    map[i+1][j+1] += (curr-1)/2;
                }
            }
        }
        return Math.min(1,map[query_row][query_glass]);
    }
}