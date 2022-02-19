class Solution {
    public int[][] generateMatrix(int n) {
        int[][] res=new int[n][n];
        int upper_bd=0, lower_bd=n-1, left_bd=0, right_bd=n-1;
        int k=1;
        while(k<=n*n) {
            if(upper_bd<=lower_bd) {
                for(int i=left_bd; i<=right_bd; i++) {
                    res[upper_bd][i]=k++;
                }
                upper_bd++;
            }
            
            if(left_bd<=right_bd) {
                for(int i=upper_bd; i<=lower_bd; i++) {
                    res[i][right_bd]=k++;
                }
                right_bd--;
            }
            
            if(upper_bd<=lower_bd) {
                for(int i=right_bd; i>=left_bd; i--) {
                    res[lower_bd][i]=k++;
                }
                lower_bd--;
            }
            
            if(left_bd<=right_bd) {
                for(int i=lower_bd; i>=upper_bd; i--) {
                    res[i][left_bd]=k++;
                }
                left_bd++;
            }
        }
        return res;
    }
}