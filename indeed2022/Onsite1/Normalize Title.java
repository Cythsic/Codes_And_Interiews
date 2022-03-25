/* =============================================================================
Question Description
Given a rawTitle, and a list(or array) of clean titles. For each clean title,
the raw title can get a "match point". For example, if raw title is "senior software
engineer" and clean titles are "software engineer" and "mechanical engineer", the
"match point" will be 2 and 1. In this case we return "software engineer" because
it has higher "match point".

step1: map key: unique word value: frequency
step2: trvaerse the String[]
step3: each string: get a score by comparing each word in string with map
step4: get the maxinmun score's string
=============================================================================*/
//Assume:
//1.单词重复吗 
//2.单词顺序重要吗？还是只考虑一致

class Solution{
	public String getHighestTitle(String rawTitle, String[] cleanTitles){
		Map<String, Integer> map = new HashMap<>();
		String[] raw = rawTitle.split("\\s+");
		for(String r : raw) {
			map.put(r, map.getOrDefault(r, 0)+1);
		}

		int max = 0;
		String res = "";
		for(String c : cleanTitles) {
			int score = getScore(map, c);
			res = score > max? c : res;
			max = score > max? score : max;
		}
		return res;
	}

	public int getScore(Map<String, Integer> map, String clean) {
		String[] cn = class.split("\\s+");
		int score = 0;
		for(String c : cn) {
			if(map.containsKey(c) && map.get(c)>0) {
				score++;
				map.put(c, map.get(c)-1);
			}
		}

		for(String c : cn) {
			if(map.containsKey(c)) {
				map.put(c, map.get(c)+1);
			}
		}

		return score;
	}

	public static void main(String[] args) {
        Solution test = new Solution();
        String rawTitle = "senior software engineer";
        String[] cleanTitles = {
            "software engineer",
                "mechanical engineer",
                "senior software engineer"};

        String result = test.getHighestTitle(rawTitle, cleanTitles);
        System.out.println(result);
    }
}

/* =============================================================================
Follow Up
raw title和clean title中有duplicate word怎么办
比如raw = "a a a b", clean = "a a b"

同上
=============================================================================*/

// 如果单词顺序要一致
class Solution{
	public String getHighestTitle(String rawTitle, String[] cleanTitles){
		int max = 0;
		String res = "";
		for(String c : cleanTitles) {
			int score = getScore(rawTitle, c);
			res = score > max? c : res;
			max = score > max? score : max;
		}
		return res;
	}

/*
prev = -1 --> index of last word both appears in raw and clean
raw title: software engineer intern
 								r
 								prev = 2 --> r = prev + 1 == 3
clean ttle: math intern engineer
			            c 					
score = 1;
*/
	public int getScore(String raw, String ct){
        int count = 0;
        int rIdx = 0, cIdx = 0， prev = -1;
        String[] rA = raw.split("\\s+");
        String[] cA = ct.split("\\s+");

        while (cIdx < cA.length){
            String cCur = cA[cIdx];
            while(rIdx < rA.length) {
                String rCur = rA[rIdx];
                if(rCur.equals(cCur)) {
                    count++;
                    prev = rIdx;
                    break;
                }
                rIdx++;
            }
            cIdx++;
            rIdx = prev + 1;
        }

        return count;
    }

	public static void main(String[] args) {
        Solution test = new Solution();
        String rawTitle = "senior software engineer";
        String[] cleanTitles = {
            "software engineer",
                "mechanical engineer",
                "senior software engineer"};

        String result = test.getHighestTitle(rawTitle, cleanTitles);
        System.out.println(result);
    }
}

