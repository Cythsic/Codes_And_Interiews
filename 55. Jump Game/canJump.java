class Solution {
    public boolean canJump(int[] nums) {
        int i=0;
        while(i<nums.length) {
            if(i+nums[i]>=nums.length-1) return true;
            int max=Integer.MIN_VALUE, point=0;
            for(int j=1; j<=nums[i]; j++) {
                point=(i+j+nums[i+j])>max?i+j:point;
                max=Math.max(i+j+nums[i+j],max);
            }
            if(max==Integer.MIN_VALUE) return false;
            i=point;
        }
        return false;
    }
}