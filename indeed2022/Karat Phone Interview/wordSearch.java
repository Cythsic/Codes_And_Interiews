class Solution {
	//Problem1
	public static List<String> wordSearch1 (List<String> words, String letters) {
		List<String> res = new ArrayList<>();
		if(letters.length()==0) return res;
		int[] map = new int[26];
		for(char l:letters.toCharArray()) {
			map[l-'a']++;
		}

		for(String word:words) {
			int[] temp = new int[26];
      int isValid = 1;
			for(char w:word.toCharArray()) {
				temp[w-'a']++;
			}
			for(char w:word.toCharArray()) {
				if(map[w-'a']<temp[w-'a']) {
          isValid = 0;
          continue;
        }
			}
      if(isValid == 1) res.add(word);
		}

    for(String s:res) {
      System.out.println(s);
    }
		return res;
	}

	//Problem2 lc79
	public boolean wordSearch2(char[][] board, String word) {
    Map<Character, Integer> map = new HashMap<>();
		for(char w:word.toCharArray()) {
			map.put(w, map.getOrDefault(w,0)+1);
		}
		return backtrack(board,map,map.size(), 0, 0, 0);
  }
    
  public boolean backtrack(char[][] dic, Map<Character, Integer> map, int len, int count, int i, int j) {
		if(i>=dic.length || j>=dic[0].length) return false;
		char curr = dic[i][j];
		if(map.containsKey(curr)) {
			map.put(curr, map.get(curr)-1);
            if(map.get(curr) == 0) {
			    count++;
			    if(count==len) return true;
		    }
            else if(map.get(curr) < 0) return false;
		}
		else return false;
        boolean right = false, down = false;
        if(j+1<dic[0].length) {
            right = backtrack(dic, map, len, count, i, j+1);
		    if(j+1<dic[0].length && map.containsKey(dic[i][j+1])) map.put(dic[i][j+1], map.get(dic[i][j+1])+1);
        }
        
        if(i+1<dic.length) {
            down = backtrack(dic, map, len, count, i+1, j);
		    if(i+1<dic.length && map.containsKey(dic[i+1][j])) map.put(dic[i+1][j], map.get(dic[i+1][j])+1);
        }

		return right==true || down == true? true : false;
	}
}

//     List<String> words1 = new ArrayList<>(Arrays.asList("ads","add","best"));
//     wordSearch(words1, "abcdest");