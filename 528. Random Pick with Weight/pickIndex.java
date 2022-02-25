/*
1. calculate prefix sum and store in an array
2. randomly select a target number in range of [0,sum]
3. using binary search to find the index where cover target value
Example: [1,3,2,1]
  prefix:[1,4,6,7]
  target:5
  index :2
*/
class Solution {
    private int[] prefix;
    private Random r;
    public Solution(int[] w) {
        prefix = new int[w.length];
        r = new Random();
        prefix[0] = w[0];
        for(int i=1; i<w.length; i++) {
            prefix[i] = prefix[i-1] + w[i];
        }
    }
    
    public int pickIndex() {
        int len = prefix.length, start = 0, end = len-1;
        int target = r.nextInt(prefix[len-1])+1;
        while(start < end) {
            int mid = (end-start)/2 + start;
            if(prefix[mid]==target) return mid;
            else if(prefix[mid]<target) start = mid+1;
            else end = mid-1;
        }
        return start;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickIndex();
 */