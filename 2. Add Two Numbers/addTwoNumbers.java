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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1), n1 = l1, n2 = l2, p = dummy;
        int carry = 0;
        while(n1!=null && n2!=null) {
            int sum = n1.val + n2.val+carry;
            carry = sum/10;
            sum%=10;
            p.next = new ListNode(sum);
            p = p.next;
            n1 = n1.next;
            n2 = n2.next;
        }
        while(n1!=null) {
            int sum = n1.val+carry;
            carry = sum/10;
            sum%=10;
            p.next = new ListNode(sum);
            p = p.next;
            n1 = n1.next;
        }
        while(n2!=null) {
            int sum = n2.val+carry;
            carry = sum/10;
            sum%=10;
            p.next = new ListNode(sum);
            p = p.next;
            n2 = n2.next;
        }
        if(carry!=0) p.next = new ListNode(carry);
        return dummy.next;
    }
}