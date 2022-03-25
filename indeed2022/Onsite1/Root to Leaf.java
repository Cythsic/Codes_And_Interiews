/* =============================================================================
Question Description
Given a tree,(binary tree possibily) every tree edge has a cost， find the least
cost or find the leaf node that the cost of path that from root to leaf is the
least.

brute force dfs
=============================================================================*/
/*
Assume:
1. binary tree?
*/
class Edge{
    Node2 node; //表示这个edge的尾巴指向哪里。
    int cost;
    public Edge(Node2 n, int cost) {
        this.node = n;
        this.cost = cost;
    }
}
class Node2 {
    List<Edge> edges; //表示从这个头出发的所有edge
    public Node2(){
        this.edges = new ArrayList<>();
    }
}
class Pair{
  List<Edge> path;
  int cost;

  Pair() {
    path = new ArrayList<>();
    cost = 0;
  }

  Pair(List<Edge> path, int cost) {
    this.path = path;
    this.cost = cost;
  }

  List<Edge> getKey() {
    return this.path;
  }

  int getValue() {
    return this.cost;
  }
}

class Solution {
	public List<Edge> getMinPath(Node2 root) {
		List<Edge> res = new ArrayList<>();
		List<Edge> temp = new ArrayList<>();

		System.out.println(dfs(root, res, temp, 0, Integer.MAX_VALUE));
		return res;
	}

	int dfs(Node2 root, List<Edge> res, List<Edge> temp, int cost, int minCost) {
		if(root == null) {
			return cost;
		}

		if(root.edges.size() == 0) {
			if(cost < minCost) {
				res.clear();
				res.addAll(temp);
				return cost;
			}
		}

		for(Edge eg: root.edges) {
			if(cost + eg.cost > minCost) continue;
			else {
				temp.add(eg);
				minCost = Math.min(dfs(eg.node, res, temp, cost + eg.cost, minCost), minCost);
				temp.remove(temp.size()-1);
			}
		}

		return minCost;
	}

/* =============================================================================
Follow Up
改树为DAG，那leaf变成了出度为0的node，同样求实现方法。 -- same above
小问题很多，开始是简单方法来做。然后逼出dijkstra方法。
问dfs能不能用在图里面，如果能，（肯定能），改进方法就是改成dijkstra。
或者就是用map记录走过的node，这样的话减少计算。
复杂度是O(Elog(V))

感觉写的不对，应该每个点搜的时候都要带个返回值，返回从当前点到叶子的最短距离。
这样优化出来是O(E+V)

improve/optimize:
1. by dijkstra algorithm, get min cost for each node from root
2. find the leaf nodes among them
3. get the min cost of among leaf nodes
=============================================================================*/
public List<Edge> dijkstra(Node2 root) {
	List<Edge> temp = new ArrayList<>();
	Map<Node2, Pair> map = new HashMap<>();
	map.put(root, new Pair());
	Node2 cur = root;

	while(cur != null) {
		int cost = map.get(cur).getValue(), minCost = Integer.MAX_VALUE;
		Edge minNode = null;
		if(cur.edges.size() == 0) {
			cur = null;
		}
		else {
			for(Edge eg : cur.edges) {
				if(!map.containsKey(eg.node) || cost + eg.cost < map.get(eg.node).getValue()) {
					temp.add(eg);
					Pair p = new Pair(temp, cost + eg.cost); 
					map.put(eg.node, p);
					if(cost + eg.cost < minCost) {
						minCost = cost + eg.cost;
						minNode = eg;
					}
					temp.remove(temp.size()-1);
				}
				else {
					if(map.get(eg.node).getValue() < minCost) {
						minCost = map.get(eg.node).getValue();
						minNode = eg;
					}
				}
			}
			temp.add(minNode);
			cur = minNode.node;
		}
	}

	int min = Integer.MAX_VALUE;
	Node2 minN = null;
	for(Node2 n : map.keySet()) {
		if(map.get(n).getValue() < min && n.edges.size() == 0) {
			min = map.get(n).getValue();
			minN = n;
		}
	}
	System.out.println("dij:" + min);
	return map.get(minN).getKey();
}

public List<Edge> dijkstra(Node2 root) {
	Map<Node2, Pair> map = new HashMap<>();
	List<Edge> edge = new ArrayList<>();
	helper(map, root, 0, edge);

	List<Edge> res = new ArrayList<>();
	int cost = Integer.MAX_VALUE;
	for(Node2 n: map.keySet()) {
		if(map.get(n).getValue() < cost) {
			cost = map.get(n).getValue();
			res = map.get(n).getKey();
		}
	}

	return res;
}

public void helper(Map<Node2, Pair> map, Node2 root, int cost, List<Edge> path) {
	if(root == null) return;

	if(root.edges.size() == 0) {
		if(!map.containsKey(root) || map.get(root).getValue() > cost) map.put(root, new Pair(new ArrayList<>(path),cost));
		return;
	}

	PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2) -> e1.cost - e2.cost);
	for(Edge eg : root.edges) {
		pq.offer(eg);
	}

	while(!pq.isEmpty()) {
		Edge e = pq.poll();
		path.add(e);
		helper(map, e.node, cost + e.cost, path);
		path.remove(path.size()-1);
	}

	return;
}

  public static void main(String[] args) {
        Solution test = new Solution();
        /*
        *       n1
        *   e1 /  \ e3
        *     n2   n3
        * e2 /
        *   n4
        *
        * */
        Node2 n1 = new Node2();
        Node2 n2 = new Node2();
        Node2 n3 = new Node2();
        Node2 n4 = new Node2();
        // Node2 n5 = new Node2();
        // Node2 n6 = new Node2();

        Edge e1 = new Edge(n2,1);
        Edge e2 = new Edge(n4,2);
        Edge e3 = new Edge(n3,5);
        // Edge e4 = new Edge(n5,1);
        // Edge e5 = new Edge(n6,4);

        n1.edges.add(e1);
        n1.edges.add(e3);
        // n1.edges.add(e4);
        // n1.edges.add(e5);
        n2.edges.add(e2);

        List<Edge> res =  test.getMinPath(n1);
        List<Edge> res1 = test.dijkstra(n1);
        for(Edge eg : res1) {
        System.out.println("3 = "+ eg.cost);
        }

    }
}