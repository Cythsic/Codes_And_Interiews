//Sliding Window
//TC: O(N)
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0, right = 0, res = Integer.MAX_VALUE, sum=0;
        for(; right<nums.length; right++) {
            sum+=nums[right];
            while(sum>=target) {
                res = Math.min(res,right-left+1);
                sum-=nums[left++];
            }
        }
        return res==Integer.MAX_VALUE?0 : res;
    }
}