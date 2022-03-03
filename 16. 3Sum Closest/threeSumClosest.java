class Solution {
    public int threeSumClosest(int[] nums, int target) {
        int diff = Integer.MAX_VALUE, res = 0;
        int len = nums.length;
        Arrays.sort(nums);
        for(int i=0; i<len; i++) {
            if(i>0 && nums[i-1]==nums[i]) continue;
            int start = i+1, end = len-1;
            while(start<end) {
                int sum = nums[i] + nums[start] + nums[end];
                if(diff>Math.abs(sum-target)) {
                    diff = Math.abs(sum-target);
                    res = sum;
                }
                if(sum>target) end--;
                else if(sum<target) start++;
                else return sum;
            }
        }
        return res;
    }
}