/** Solution:
 * Fast pointer moves 2 nodes every step
 * Slow pointer moves 1 node every step
 * 
 * If fast and slow can meet, there must be a cycle, else no cycle -- 判断有环
 * If there is a cycle, make slow point to head, and move slow and fast togeter until the meet each other again.
 * The node they meet is where the cycle begins.
 * 
 * Example: 
 * a -- distance between head to the beginning of cycle
 * b -- distance between begging of cycle to the first meet node of fast and slow
 * c -- distance between meet node to beginning of cycle
 * 
 * When fast and slow first meet each other:
 * slow moves: a+b
 * fast moves: a+b+2(b+c)
 * 
 * Since fast moves as 2 times faster as slow, so the distance fast moves is 2 times further than slow's.
 * So we can get: 2(a+b) = a+b+2(b+c) --> a=c
 * 
*/
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head, slow = head;
        while(fast!=null && fast.next!=null) {
            fast = fast.next.next;
            slow = slow.next;
            if(slow == fast) break;
        }
        if(fast==null || fast.next==null) return null;
        slow = head;
        while(slow!=fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}