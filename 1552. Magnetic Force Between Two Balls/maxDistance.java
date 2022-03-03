class Solution {
    public int maxDistance(int[] position, int m) {
        Arrays.sort(position);
        int min = position[0], max = position[position.length-1];
        
        return binary(max, min, m, position);
    }
    
    int binary(int max, int min, int m, int nums[]) {
        int high = (max-min)/(m-1), low = 1;
        while(low < high) {
            int mid = (high - low + 1)/2 + low;
            if(valid(mid,nums,m)) low = mid;
            else high = mid-1;
        }
        return low;
    }
    
    boolean valid(int target, int[] nums, int m) {
        int prev = 0, count = 1;
        for(int i=1; i<nums.length; i++) {
            if(nums[i] - nums[prev]>=target) {
                count++;
                prev = i;
                if(count>=m) return true;
            }
        }
        return false;
    }
}