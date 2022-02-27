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
    Node dummy = new Node(-1), p = dummy;
    public Node treeToDoublyList(Node root) {
        if(root==null) return null;
        Node prev = null;
        inorder(root, prev);
        dummy.right.left = p;
        p.right = dummy.right;
        return dummy.right;
    }
    
    void inorder(Node node, Node prev) {
        if(node==null) return;
        inorder(node.left, prev);
        p.right = new Node(node.val);
        prev = p;
        p = p.right;
        p.left = prev;
        inorder(node.right, prev);
    }
}