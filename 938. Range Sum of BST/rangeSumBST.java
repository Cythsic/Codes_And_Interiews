/* DFS */

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
    public int rangeSumBST(TreeNode root, int low, int high) {
        if(root==null) return 0;
        int count=0;
        if(root.val<low) {
            count += rangeSumBST(root.right, low, high);
        }
        else if(root.val>high) {
            count +=  rangeSumBST(root.left, low, high);
        }
        else{
            count += root.val;
            count += rangeSumBST(root.left, low, high);
            count += rangeSumBST(root.right, low, high);
        }
        return count;
    }
}