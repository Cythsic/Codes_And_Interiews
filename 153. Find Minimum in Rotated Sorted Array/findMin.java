/*
All elements in the left of rotation position are larger than the first element and following ascending order from left to right
All elements in the right of rotation position are smaller than the first element and following descending order from left to right
*/
class Solution {
    public int findMin(int[] nums) {
        int len = nums.length, start = 0, end = len-1;
        if(nums[0]<=nums[len-1]) return nums[0]; //ascending order case
        while(start<end) {
            int mid = (end - start)/2 + start;
            if(nums[mid]>=nums[0]) start = mid+1; //if mid=0, move to right
            else end = mid;
        }
        return nums[start];
    }
}