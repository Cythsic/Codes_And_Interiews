/*
Dismater of currrent node "curr" is sum of the diameters of its left child and right child.
If the current node is null, return 0; else return maximun value between left and right then plus 1 instead.
*/ 

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return path;
    }
    int path = 0;
    int dfs(TreeNode node) {
        if(node==null) return 0;
        int l = dfs(node.left);
        int r = dfs(node.right);
        
        path = Math.max(path, l+r);
        return Math.max(l,r)+1;
    }
}