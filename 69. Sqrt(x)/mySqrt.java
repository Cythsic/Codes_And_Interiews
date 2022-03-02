class Solution {
    public int mySqrt(int x) {
        int start = 1, end = x;
        while(start <= end) {
            int mid = (end-start)/2 + start;
            if(x/mid < mid) end = mid-1; //use division in case of overflow
            else if(x/mid > mid) start = mid+1;
            else return mid;
        }
        return end;
    }
}