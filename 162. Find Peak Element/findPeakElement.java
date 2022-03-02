//Solution 1 -- simplified
class Solution1 {
    public int findPeakElement(int[] nums) {
        int left=0,right=nums.length-1;
        while(left<right){
            int mid = left+(right-left)/2;
            if(nums[mid]>nums[mid+1])
                right = mid;
            else left = mid+1;
        }
        return left;
    }
}

//Solution 2
class Solution2 {
    public int findPeakElement(int[] nums) {
        int start = 0, end = nums.length-1;
        while(start<=end) {
            int mid = (end - start)/2 + start;
            if(mid>0 && mid<nums.length-1) {
                if(nums[mid-1]<nums[mid] && nums[mid+1]<nums[mid]) return mid;
                else if(nums[mid-1]>nums[mid+1]) end = mid-1;
                else start = mid+1;
            }
            else if(mid<=0 && mid<nums.length-1) {
                if(nums[mid+1]<nums[mid]) return mid;
                else start = mid+1;
            }
            else if(mid>0 && mid>=nums.length-1) {
                if(nums[mid-1]<nums[mid]) return mid;
                else end = mid-1;
            }
            else return mid;
        }
        return start;
    }
}