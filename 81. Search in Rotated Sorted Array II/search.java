class Solution {
    public boolean search(int[] nums, int target) {
        int pivot = 0;
        if(nums.length == 1) return nums[0]==target? true : false;
        while(pivot<nums.length-1 && nums[pivot+1]>=nums[pivot]) pivot++;
        int start = 0, end = nums.length-1;
        if(target<nums[0]) {
            start = pivot +1;
        }
        else if(target >= nums[0]) {
            end = pivot;
        }
        
        while(start<=end) {
            int mid = (end-start)/2 + start;
            if(target>nums[mid]) start = mid+1;
            else if(target<nums[mid]) end = mid-1;
            else return true;
        }
        
        return false;
    }
}