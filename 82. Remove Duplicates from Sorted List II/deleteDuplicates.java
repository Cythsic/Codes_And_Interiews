/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(-1), node = head, p = dummy;
        int prev = 101;
        while(node!=null) {
            if(prev==101 || node.val != prev) {
                if(node.next==null || node.val != node.next.val) {
                    p.next = new ListNode(node.val);
                    p = p.next;
                }
            }
            prev = node.val;
            node = node.next;
        }
        
        return dummy.next;
    }
}