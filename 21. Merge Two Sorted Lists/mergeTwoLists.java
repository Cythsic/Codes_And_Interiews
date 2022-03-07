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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy, one = list1, two = list2;
        while(one!=null && two!=null) {
            if(one.val < two.val) {
                cur.next = new ListNode(one.val);
                cur = cur.next;
                one = one.next;
            }
            else {
                cur.next = new ListNode(two.val);
                cur = cur.next;
                two = two.next;
            }
        }
        if(one!=null) {
            cur.next = one;
        }
        if(two!=null) {
            cur.next = two;
        }
        
        return dummy.next;
    } 
}