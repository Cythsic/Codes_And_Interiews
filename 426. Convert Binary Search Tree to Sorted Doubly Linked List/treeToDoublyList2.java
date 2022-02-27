/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};
*/

class Solution {
    Node head = new Node(), target = head;
    public Node treeToDoublyList(Node root) {
        if(root==null) return null;
        dfs(root);
        target.right = head.right;
        Node prev = head.right, curr = head.right.right;
        while(curr!=head.right) {
            curr.left = prev;
            prev = curr;
            curr = curr.right;
        }
        curr.left = prev;
        return head.right;
    }
    
    void dfs(Node root) {
        if(root==null) return;
        dfs(root.left);
        target.right = new Node(root.val);
        target = target.right;
        dfs(root.right);
    }
}