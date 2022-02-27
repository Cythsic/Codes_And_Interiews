/*
为完全二叉树宽度编码：
若父节点编码x， 则左子树节点编码为x*2，右子树节点编码为x*2+1
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
    public int widthOfBinaryTree(TreeNode root) {
        if(root==null) return 0;
        int res = 1;
        Queue<Pair<TreeNode, Integer>> q = new LinkedList<>();
        q.offer(new Pair(root, 1));
        while(!q.isEmpty()) {
            int size = q.size(), start = 1, end = 1;
            for(int i=size; i>0; i--) {
                Pair<TreeNode, Integer> pr = q.poll();
                TreeNode curr = pr.getKey();
                int val = pr.getValue();
                if(curr.left != null) q.offer(new Pair(curr.left, val*2));
                if(curr.right != null) q.offer(new Pair(curr.right, val*2+1));
                
                if(i==size) start = val;
                if(i==1) end = val;
            }
            res = Math.max(res, end-start+1);
        }
        return res;
    }
}