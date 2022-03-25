/* =============================================================================
Question Description
Given a git commit (wiki:...), each commit has an id. Find all the commits
that we have.
讨论：
1. 一个node可以有几个commit
2. 如果有多个，需要去重吗

hashet --> all the unique node
queue --> get all nodes we are going to check/traverse
list --> result

step1: put root queue and set
step2: while loop the queue
each node: check all nodes in its parents list, if the parent node is in the set --> have traversed
												if not in set: add node to queue and set
step3: get the result list from set
=============================================================================*/

class GitNode{
	int id;
	List<GitNode> parents;

	GitNode(int val) {
		this.id = val;
		parents = new ArrayList<>();
	}

	List<List<Integer>> getAllCommits(GitNode root) {
		List<List<Integer>> res = new ArrayList<>();
		Queue<GitNode> q = new LinkedList<>();
		Set<GitNode> set = new HashSet<>(); //这步去重
		q.offer(root);
		set.add(root);

		while(!q.isEmpty()) {
			List<Integer> temp = new ArrayList<>(); //get result in each layer
			int size = q.size();

			for(int i=0; i<size; i++) { //分层 
				GitNode node = q.poll();
				temp.add(node.id); //如果不分层， 在这里加入res
				for(GitNode p : node.parents) { //向queue添加新节点
					if(!set.contains(p)) {
						set.add(p);
						q.offer(p);
					}
				}
			}
			res.add(temp);
			// res.add(new ArrayList<>(temp)); 上下两种都可以
		}

		return res;
	}

/* =============================================================================
Follow Up
找到两个commit的最近公共parent commit。

2 queues to track all nodes we need to traverse
2 hasets to store all unique parent node for the two given node

step1: get all parent node of node1 and put into set1
	  for each parent node: check if it is in set2 --> true get lowest common ansector
												   --> false, put it into queue
step2: similar to stpe2 --> check node2

step3: return null
=============================================================================*/
	GitNode findLCA(GitNode node1, GitNode node2) {
		if(node1 == null || node2 == null) return null;

		Queue<GitNode> q1 = new LinkedList<>();
		Queue<GitNode> q2 = new LinkedList<>();
		q1.offer(node1);
		q2.offer(node2);

		Set<GitNode> set1 = new HashSet<>();
		Set<GitNode> set2 = new HashSet<>();
		set1.add(node1);
		set2.add(node2);
		int len1 = 1, len2 = 1; //万一是要求最短路径长度呢。

		while(!q1.isEmpty() && !q2.isEmpty()) {
			int size1 = q1.size();
			for(int i=0; i<size1; i++) {
				GitNode curr1 = q1.poll();
				if(set2.contains(curr1)) return curr1;
				for(GitNode par1 : curr1.parents) {
					if(set2.contains(par1)) return par1;
					if(!set1.contains(par)) {
						set1.add(par1);
						q1.offer(par1);
					}
				}
			}
			len1++;

			int size2 = q2.size();
			for(int j=0; j<size2; j++) {
				GitNode curr2 = q2.poll();
				if(set1.contains(curr2)) return curr2;
				for(GitNode par2 : curr2.parents) {
					if(set1.contains(par2)) return par2;
					if(!set2.contains(par2)) {
						set2.add(par2);
						q2.offer(par2);
					}
				}
			}
			len2++;
		}

		return null;
	}

/* =============================================================================
Follow Up
找到两个commit的最近公共parent commit, 要求是距离两个node距离和最短的点

2 queues to track all nodes we need to traverse
2 hashmap, key: node value: dis from node to the node1/node2

step1: traverse all parent nodes for node1, and put the dis into map1 by plus 1 to dis of the current node
	check each parent node, if parent == node2, return node2

step2: traverse all parent nodes for node2, put dis into map2 by plus 1 to dis of the current node
	check each parent node, if in the map1: return parent node

step3: return null
=============================================================================*/
public static GitNode findClosestPath(GitNode node1, GitNode node2) {
	if(node1 == null || node2 == null) return null;
	if(node1 == node2) return node1;

	// int len1 = 0, len2 = 0;
	Queue<GitNode> q1 = new LinkedList<>();
	Map<GitNode, Integer> map1 = new HashMap<>();
	Queue<GitNode> q2 = new LinkedList<>();
	Map<GitNode, Integer> map2 = new HashMap<>();

	q1.offer(node1);
	q2.offer(node2);

	map1.put(node1, 0);
	map2.put(node2, 0);

	while(!q1.isEmpty()) {
		int size = q1.size();
		for(int i=0; i<size; i++) {
			GitNode cur = q1.poll();
			for(GitNode par:cur.parents) {
				if(!map1.containsKey(par)) {
					map1.put(par, map1.get(cur)+1);
					q1.offer(par);
				}
				if(par == node2) {
					// System.out.println("len1:" + map.get(par));
					return node2;
				}
			}
		}
	}

	while(!q2.isEmpty()) {
		int size = q2.size();
		for(int i=0; i<size; i++) {
			GitNode cur = q2.poll();
			for(GitNode par : cur.parents) {
				if(!map2.containsKey(par)) {
					map2.put(par, map2.get(cur)+1);
					q2.offer(par);
				}
				if(map1.containsKey(par)) {
					// System.out.println("len1:" + map.get(par) + " - len2:" + map2.get(par));
					return par;
				}
			}
		}
	}
	return null;
}

}

