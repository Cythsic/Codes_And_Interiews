/*
Only get the right length rather the right content.
Example: [1,3,10,2]
-- 1
-- 1,3
-- 1,3,10
-- 1,2,10
*/

class Solution {
    public int lengthOfLIS(int[] nums) {
        List<Integer> list = new ArrayList<>();
        list.add(nums[0]);
        for(int i=1; i<nums.length; i++) {
            int size = list.size();
            if(list.get(size-1)<nums[i]) list.add(nums[i]);
            int p = binary(list, nums[i]);
            list.set(p, nums[i]);
        }
        
        return list.size();
    }
    
    int binary(List<Integer> list, int target) {
        int len = list.size();
        int start = 0, end = len-1;
        while(start < end) {
            int mid = (end-start)/2 + start;
            if(list.get(mid)>target) end = mid;
            else if(list.get(mid) < target) start = mid+1;
            else return  mid;
        }
        return start;
    }
}