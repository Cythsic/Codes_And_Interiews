class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length, col = matrix[0].length;
        // if(row==1 && col==1) return matrix[0][0]==target?true : false;
        int start = 0, end = row-1;
        while(start<=end) {
            int mid = (end - start)/2 + start;
            if(matrix[mid][0]>target) end = mid-1;
            else if(matrix[mid][col-1]<target) start = mid+1;
            else if(matrix[mid][0]<=target && matrix[mid][col-1]>=target) {
                start = mid;
                break;
            }
            else return false;
        }
        if(start==row) return false;
        int r=start;
        start = 0;
        end = col-1;
        while(start<=end) {
            int mid = (end-start)/2 + start;
            if(matrix[r][mid] > target) end = mid-1;
            else if(matrix[r][mid] < target) start = mid+1;
            else return true;
        }
        
        return false;
    }
}