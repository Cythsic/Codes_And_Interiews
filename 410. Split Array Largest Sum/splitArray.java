/*
Use binary search to guess the anwser.
*/

class Solution {
    public int splitArray(int[] nums, int m) {
        int sum = 0, max = nums[0];

        //find the range for binary search
        for(int num:nums) {
            sum+=num;
            max = Math.max(max,num);
        }
        return binary(max,sum,nums,m);
    }
    
    int binary(int low, int high, int[] nums, int m) {
        while(low<high) {
            int mid = (high - low)/2 + low;
            //if the guess cannot meet requirement, the make the guess larger
            if(!valid(nums,m,mid))low = mid+1;
            //if the guess can meet requirenment, the make the guess smaller
            else high = mid;
        }
        return high;
    }
    
    //if the guess anwser can meet the requirement
    boolean valid(int[] nums, int m, int target) {
        int sum = 0, count=1;
        for(int num:nums) {
            sum+=num;
            if(sum>target) {
                sum = num;
                count++;
                if(count>m) return false;
            }
        }
        return true;
    }
}