class Solution {
    public void moveZeroes(int[] nums) {
        if(nums.length==1) return;
        int slow = 0, fast = 1;
        for(;fast<nums.length; fast++) {
            if(nums[fast]!=0 && nums[slow]==0) {
                nums[slow] = nums[fast];
                nums[fast] = 0;
                slow++;
            }
            else if(nums[slow]!=0) slow++;
        }
        return;
    }
}