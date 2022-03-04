//Solution with SC:O(1) and without division operation

class Solution {
    public int[] productExceptSelf(int[] nums) {
       int[] res = new int[nums.length];
        res[0] = 1;
        for(int i=1; i<nums.length; i++) {
            res[i] = nums[i-1]*res[i-1];
        }
        int i=1;
        for(int j=nums.length-1; j>=0; j--) {
            res[j] *=i;
            i *= nums[j];
        }
        
        return res;
    }
}