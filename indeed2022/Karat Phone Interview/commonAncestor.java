class Solution {
	public static List<List<Integer>> commonAncestor1(int[][] pairs) {
		List<List<Integer>> res = new ArrayList<>();
		Map<Integer, Integer> map = new HashMap<>();
		for(int[] pair : pairs) {
			map.put(pair[0], map.getOrDefault(pair[0],0));
			map.put(pair[1], map.getOrDefault(pair[1],0)+1);
		}

		List<Integer> zero = new ArrayList<>();
		List<Integer> one = new ArrayList<>();

		for(int key:map.keySet()) {
			if(map.get(key) == 0) {
				zero.add(key);
				System.out.println("zero:" + key);
			}
			else if(map.get(key) == 1) {
				one.add(key);
				System.out.println("one:" + key);
			}
		}

		res.add(zero);
		res.add(one);

		return res;
	}
  
  public static boolean commonAncestor2(int[][] pairs, int node1, int node2) {
		Set<Integer> set1 = new HashSet<>();
		Set<Integer> set2 = new HashSet<>();
		help_commonAncestor2(pairs, node1, set1);
		help_commonAncestor2(pairs, node2, set2);

		for(int node : set1) {
			if(node == node2 || set2.contains(node)) return true;
		}

		return false;
	}

	public static void help_commonAncestor2(int[][] pairs, int node, Set<Integer> set) {
		for(int[] pair : pairs) {
			if(pair[1] == node) {
				set.add(pair[0]);
				help_commonAncestor2(pairs, pair[0], set);
			}
		}
	}

	public static int commonAncestor3(int[][] pairs, int node) {
		int[] res= help_commonAncestor3(pairs, node, 0);
		return res[0]==node?-1 : res[0];
	}

	public static int[] help_commonAncestor3(int[][] pairs, int node, int layer) {
		int[] res = new int[2];
		res[0] = -1;
		res[1] = Integer.MIN_VALUE;
		for(int[] pair : pairs) {
			if(pair[1] == node) {
				int[] temp = help_commonAncestor3(pairs, pair[0], layer+1);
				res = temp[1]>res[1]? temp : res;
			}
		}
		if(res[0] == -1) {
			res[0] = node;
			res[1] = layer;
		}
		return res;
	}
}

/*
int[][] pairs = {{1,3}, {2,3}, {3,6}, {5,6}, {5,7}, {4,5}, {4,8}, {8,10}};
    commonAncestor1(pairs);
    System.out.println(commonAncestor2(pairs, 3, 8));
    System.out.println(commonAncestor2(pairs, 5, 4));
    System.out.println(commonAncestor2(pairs, 6, 8));
    System.out.println(commonAncestor2(pairs, 1, 3));
   */