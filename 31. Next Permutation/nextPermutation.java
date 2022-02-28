/*
Step 1: Search the first element in ascending order from right to left
Step 2: Find the first element that is larger than the number finded in step1
Step 3: Swap numbers in positions finded in two steps above
Step 4: Reverse all numbers after the fisrt ascending position

Example: nums = [1,5,8,4,7,6,5,3,1]
Step 1: ascPosition  = 3 --> value is 4
Step 2: largerPosition = 6 --> value is 5
Step 3: Swap nums[3] and nums[6] and result is [1,5,8,5,7,6,4,3,1]
Step 4: The numbers after position 3 are following descending order and should reverse them to ascending order to keep the minimum larger result
*/

class Solution {
    public void nextPermutation(int[] nums) {
        if(nums.length==1) return;
        int descP = -1 ;
        
        //Step 1
        for(int i=nums.length-2; i>=0; i--) {
            if(nums[i]<nums[i+1]) {
                descP = i;
                break;
            }
        }
        

        if(descP>-1) {
            int i = nums.length-1;
            // Step 2
            while(nums[i]<=nums[descP]) i--;
            //Step 3
            swap(nums,i,descP);
        }
        
        //Step 4
        reverse(nums,descP+1);
    }
    
    void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    void reverse(int[] nums, int start) {
        int i =start, j = nums.length-1;
        while(i<j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }
}