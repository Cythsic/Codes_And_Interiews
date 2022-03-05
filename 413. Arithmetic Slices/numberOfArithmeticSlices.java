/*
Idea:

An Arithmetic Slice (AS) is at least 3 ints long, s.t. for a1, a2, a3, they're in arithmetic progression (AP), i.e. a3 - a2 = a2 - a1
If there's an AS forming at any index, then it'll be 1 longer than the AS forming at the previous index. Why?
Let a[i], ..., a[j] form an AS of size k, and
a[j + 1] - a[j] = a[j] - a[j - 1], then a[j + 1] becomes a part of the previous AS, i.e. it extends the AS by 1
Total AS = sum of all count of AS ending at each index

Example: 
nums: [1, 2, 3, 4, 5, 6]
Total:[0, 0, 1, 3, 6, 10]
prev: [0, 0, 1, 2, 3, 4]
*/

class Solution {
    public int numberOfArithmeticSlices(int[] nums) {
        // if(nums.length<3) return 0;
        int res = 0, prev = 0;
        for(int i=2; i<nums.length; i++) {
            if(nums[i]-nums[i-1] == nums[i-1]-nums[i-2]) {
                res+=++prev;
            }
            else prev = 0;
        }
        return res;
    }
}