/*
1. TC(NlogN): BFS+sorting
2. TC(N): BFS without sorting -- use "min" and "max" store range of column and no need to sort the keySet of map
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
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res=new ArrayList<>();
        if(root==null) return res;
        Queue<Pair<TreeNode,Integer>> q=new LinkedList<>();
        Map<Integer, List<Integer>> map=new HashMap<>();
        int min=0, max=0; //solution2
        q.offer(new Pair<>(root,0));
        while(!q.isEmpty()) {
            int size=q.size();
            while(size>0){
                Pair<TreeNode, Integer> temp=q.poll();
                TreeNode n=temp.getKey();
                int col=temp.getValue();
                min=Math.min(col,min);
                max=Math.max(col,max);
                if(!map.containsKey(col)) map.put(col, new ArrayList<Integer>());
                map.get(col).add(n.val);
                if(n.left!=null) {
                   q.offer(new Pair<TreeNode, Integer>(n.left,col-1));
                }
                if(n.right!=null) {
                    q.offer(new Pair<TreeNode, Integer>(n.right, col+1));
                }
                size--;
            }
        }
        
        // List<Integer> list=new ArrayList<>(map.keySet()); 
        // Collections.sort(list); //solution1

        for(int i=min; i<=max; i++) {
            res.add(new ArrayList<>(map.get(i)));
        }
        return res;
    }
    
}