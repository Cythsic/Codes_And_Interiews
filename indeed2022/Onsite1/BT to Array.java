/* =============================================================================
Question Description
Given a tree, try to implemnt it using other data structures to reduce the overhead.
Memory consuming

step 1: get size of arry: 2^height --> get height of tree
step 2: arr[0] = root.val
bfs traverse the tree:
we start from the level 0, increse the it when we finish traversal of one layer
for the node we currently traverse: we store its children into the array with the index of: 2*level+1 and 2*level + 2

memory consuming: header 4B + reference 4B + 4B*2^height + int 4B
=============================================================================*/
/*
1. is it a binary tree?
2. dense/sparse tree
*/

class TreeNode{
    int val;
    TreeNode left, right;
    public TreeNode(int val){
        this.val = val;
        this.left = null;
        this.right = null;
    }
}
class Solution {
	//dense
	public int[] btToArray(TreeNode root) {
		int height = getHeight(root);
		if(height == 0) return null;
	 	int[] res = new int[(int) Math.pow(2, height)];
	 	Queue<TreeNode> q = new LinkedList<>();
	 	q.offer(root);
	 	res[0] = root.val;

	 	int i=0;
	 	while(!q.isEmpty()) {
	 		int size = q.size(), index = 0;
	 		while(index++ < size) {
	 			TreeNode cur = q.poll();
	 			if(cur.left != null) {
	 				res[2*i+1] = cur.left.val;
	 				q.offer(cur.left);
	 			}
	 			if(cur.right != null) {
	 				res[2*i+2] = cur.right.val;
	 				q.offer(cur.right);
	 			}
	 		}
	 		i++;
	 	}

	 	return res;
	}

	public int getHeight(TreeNode root) {
		if(root == null) return 0;
		int left = getHeight(root.left);
		int right = getHeight(root.right);

		return Math.max(left, right)+1;
	}

	/* map key: val of node value: index of the node 
	memory consuming: header 4B + refence 4B + 8B * n 
	assuming no duplicates && sparse tree
	*/
	public Map<Integer,Integer> btToMap(TreeNode root) {
		if(root == null) return null;
		Map<Integer, Integer> res = new HashMap<>();
		Queue<TreeNode> q= new LinkedList<>();
		q.offer(root);
		res.put(root.val, 0);

		int i=0;
	 	while(!q.isEmpty()) {
	 		int size = q.size(), index = 0;
	 		while(index++ < size) {
	 			TreeNode cur = q.poll();
	 			if(cur.left != null) {
	 				res.put(cur.left.val, 2*i+1);
	 				q.offer(cur.left);
	 			}
	 			if(cur.right != null) {
	 				res.put(cur.right.val, 2*i+2);
	 				q.offer(cur.right);
	 			}
	 		}
	 		i++;
	 	}

	 	return res;
	}

	// String : header + reference + (header + reference + in 4B + 2B * n)
	public String btToString(TreeNode root) {
		if(root == null) return "";
		StringBuilder sb = new StringBuilder();
		Queue<TreeNode> q= new LinkedList<>();
		q.offer(root);
		while(!q.isEmpty()) {
	 		int size = q.size(), index = 0;
	 		while(index++ < size) {
	 			TreeNode cur = q.poll();
	 			if(cur == null) sb.append("null");
	 			else {
	 				sb.append(cur.val);
		 			q.offer(cur.left);
		 			q.offer(cur.right);
		 		}
	 		}
	 	}
	 	return sb.toString();
	}

	public static void main(String[] args) {
        Solution test = new Solution();
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        int[] res = test.btToArray(t1);
        System.out.println(Arrays.toString(res));
        Map<Integer, Integer> resMap = test.btToMap(t1);
        Iterator<Integer> ite = resMap.keySet().iterator();
        while (ite.hasNext()){
            int num = ite.next();
            System.out.println("root index is "+num + " root value is "+resMap.get(num));
        }
        System.out.println(test.btToString(t1));
    }
}
