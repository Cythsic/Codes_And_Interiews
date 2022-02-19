class Solution {
    public int jump(int[] nums) {
        if(nums.length<=1) return 0;
        int step=0,i=0;
        
        while(i<nums.length) {
            if(i+nums[i]>=nums.length-1) return ++step;
            else {
                int maxLen=Integer.MIN_VALUE,maxP=0;
                for(int p=1; p<=nums[i]; p++) {
                    maxP=(i+p+nums[p+i])>maxLen?i+p:maxP;
                    maxLen=(i+p+nums[p+i])>maxLen?(i+p+nums[p+i]):maxLen;
                }
                step++;
                i=maxP;
            }
        }
        return step;
    }
}