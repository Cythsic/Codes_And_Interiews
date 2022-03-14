class Solution {
	public static int findEnding(List<Integer> endings, List<List<Integer>> choices, int choice) {
		if(choices.size() == 0) return endings.get(0);
		Map<Integer, Integer> map = new HashMap<>();
		int max = Math.max(choices.get(choices.size()-1).get(0), choices.get(choices.size()-1).get(1));
		max = Math.max(choices.get(choices.size()-1).get(2), max);
		max = Math.max(endings.get(endings.size()-1), max);
		int[] visit = new int[max+1];

		for(List<Integer> l:choices) {
			map.put(l.get(0), l.get(choice));
		}
		for(int end: endings) {
			visit[end] = 2;
		}

		int page = 1;
		while(page<=max) {
			if(visit[page]==1) return -1;
			if(visit[page]==2) return page;
			visit[page] = 1;

			if(map.containsKey(page)) {
				page = map.get(page);
			}
			else page++;
		}
		return -1;
	}

	static Set<Integer> res = new HashSet<>();
	public static List<Integer> happyEnding(List<Integer> endings, List<List<Integer>> choices) {
		Map<Integer, List<Integer>> map = new HashMap<>();
		int max = Math.max(choices.get(choices.size()-1).get(0), choices.get(choices.size()-1).get(1));
		max = Math.max(choices.get(choices.size()-1).get(2), max);
		max = Math.max(endings.get(endings.size()-1), max);
		int[] visit = new int[max+1];

		for(List<Integer> l:choices) {
			map.put(l.get(0), new ArrayList<Integer>(Arrays.asList(l.get(1), l.get(2))));
		}
		for(int end: endings) {
			visit[end] = 2;
		}

		backtrack(visit, map, 1, max);

		List<Integer> ans = new ArrayList<>();
		for(int r:res) {
			ans.add(r);
			System.out.println(r);
		}

		return ans;
	}


	static void backtrack(int[] visit, Map<Integer, List<Integer>> map, int page, int end) {
		if(visit[page] == 1 || page>end) return;
		if(visit[page] == 2) {
			res.add(page);
			return;
		}
		visit[page] = 1;
		if(map.containsKey(page)) {
			backtrack(visit, map, map.get(page).get(0), end);
			backtrack(visit, map, map.get(page).get(1), end);
		}
		else {
			backtrack(visit, map, page+1, end);
		}
	}
}


}

/**
List<Integer> endings = new ArrayList<>(Arrays.asList(6,15,21,30));
    
    List<List<Integer>> choices1 = new ArrayList<>();
    choices1.add(new ArrayList<Integer>(Arrays.asList(3, 14, 2)));
    
    List<List<Integer>> choices2 = new ArrayList<>();
    choices2.add(new ArrayList<Integer>(Arrays.asList(5, 11, 28)));
    choices2.add(new ArrayList<Integer>(Arrays.asList(9, 19, 29)));
    choices2.add(new ArrayList<Integer>(Arrays.asList(14, 16, 20)));
    choices2.add(new ArrayList<Integer>(Arrays.asList(18, 7, 22)));
    choices2.add(new ArrayList<Integer>(Arrays.asList(25, 6, 30)));
    
    List<List<Integer>> choices3 = new ArrayList<>();
    
    List<List<Integer>> choices4 = new ArrayList<>();
    choices4.add(new ArrayList<Integer>(Arrays.asList(2, 10, 15)));
    choices4.add(new ArrayList<Integer>(Arrays.asList(3, 4, 10)));
    choices4.add(new ArrayList<Integer>(Arrays.asList(4, 3, 15)));
    choices4.add(new ArrayList<Integer>(Arrays.asList(10, 3, 15)));
    
    System.out.println(findEnding(endings, choices1, 1));
    System.out.println(findEnding(endings, choices1, 2));
    System.out.println(findEnding(endings, choices2, 1));
    System.out.println(findEnding(endings, choices2, 2));
    System.out.println(findEnding(endings, choices3, 1));
    System.out.println(findEnding(endings, choices3, 2));
    System.out.println(findEnding(endings, choices4, 1));
    System.out.println(findEnding(endings, choices4, 2));
*/