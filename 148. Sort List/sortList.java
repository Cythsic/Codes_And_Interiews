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
    public ListNode sortList(ListNode head) {
        PriorityQueue<Integer> pq=new PriorityQueue<>();
        ListNode node=head;
        while(node!=null) {
            pq.offer(node.val);
            node=node.next;
        }
        ListNode dummy=new ListNode(-1);
        ListNode temp=dummy;
        while(!pq.isEmpty()) {
            temp.next=new ListNode(pq.poll());
            temp=temp.next;
        }
        return dummy.next;
    }
}