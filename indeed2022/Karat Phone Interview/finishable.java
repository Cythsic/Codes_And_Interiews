class Solution {
	public static boolean finishable(String[] teleporters, int die, int start, int end) {
		Map<Integer, Integer> map = new HashMap<>();
		for(String t:teleporters) {
			String[] temp = t.split(",");
			int val1 = 0, val2 = 0;
			int i=0;
			while(i<temp[0].length()) {
				val1 = val1*10 + temp[0].charAt(i++)-'0';
			}
			i = 0;
			while(i<temp[1].length()) {
				val2 = val2*10 + temp[1].charAt(i++)-'0';
			}
			map.put(val1,val2);
//   for(int[] t:teleporters) {
// 			map.put(t[0],t[1]);
		}

		int[] visited = new int[end+1];
		
		return help(die, start, end, map, visited);
	}

	static boolean help(int die, int start, int end, Map<Integer, Integer> map, int[] visited) {
		for(int i = start; i<=end; i++) {
			if(visited[i] == 1) return false;
			visited[i] = 1;
			if(i==end) return true;
			if(map.containsKey(i)) {
				i = map.get(i);
			}
			else {
				for(int k=1; k<=die; k++) {
					 if(help(die, i+k, end, map, visited) == true) return true;
				}
			}
		}
		return true;
	}
}

/**
String[] teleporters1 = {"10,8", "11,5", "12,7", "13,9"};
    System.out.println(finishable(teleporters1, 4, 0, 20));
    
    String[] teleporters2 = {"10,8", "11,5", "12,1", "13,9", "2,15"};
    System.out.println(finishable(teleporters2, 4, 0, 20));
    
    String[] teleporters3 = {"2,4", "9,8", "11,7", "12,6", "18,14",
                "19,16", "20,9", "21,14", "22,6", "23,26",
                "25,10", "28,19", "29,27", "31,29", "38,33",
                "39,17", "41,30", "42,28", "45,44", "46,36"]
};
    System.out.println(finishable(teleporters3, 4, 0, 20));
    
    String[] teleporters1 = {"4,21", "11,18", "13,17", "16,17", "18,21",
                "22,11", "26,25", "27,9", "31,38", "32,43",
                "34,19", "35,19", "36,39", "38,25", "41,31
"};
    System.out.println(finishable(teleporters1, 4, 0, 20));

    */