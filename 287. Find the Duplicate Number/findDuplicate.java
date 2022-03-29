/**
Fast Slow Pointers
First set the slow pointer slow and the fast pointer fast, the slow pointer moves one step at a time, and the fast pointer moves two steps at a time.

In this case, we will meet each other. At this time, we put slow at the starting point 0, and both the fast and the slow pointers move one step at the same time.

The meeting point is the answer.
*/

class Solution {
    public int findDuplicate(int[] nums) {
       int slow = nums[0], fast = nums[nums[0]];
        while(slow!=fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        
        slow = 0;
        while(slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        
        return slow;
    }
}