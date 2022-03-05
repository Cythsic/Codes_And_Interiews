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
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(-1), p = dummy, n = head;
        int count=1;
        while(n!=null) {
            if(count<left || count>right) {
                ListNode curr = new ListNode(n.val);
                p.next = curr;
                p = p.next;
                n = n.next;
                count++;
            }
            else {
                while(count>=left && count<=right) {
                    ListNode curr = new ListNode(n.val);
                    curr.next = p.next;
                    p.next = curr;
                    n = n.next;
                    count++;
                }
                while(p.next!=null) {
                    p = p.next;
                }
                }
            }
        return dummy.next;
    }
}