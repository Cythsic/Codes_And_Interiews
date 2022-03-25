/* =============================================================================
Question Description
Given an array of strings wordsDict and two different strings that already exist in the array word1 and word2, 
return the shortest distance between these two words in the list.
(LC243)

step1: for loop traverse String[]
step2: for each word in array: ==word1/word2
        == word1 --> p1 = i
        == word2 -->p2 = i
step3: if p1 and p2 != -1
res = Math.min(res, Math.abs(p1 - p2));

=============================================================================*/
class Solution {
    public int shortestDistance(String[] wordsDict, String word1, String word2) {
        int p1 = -1, p2 = -1, dis = wordsDict.length;
        for(int i=0; i<wordsDict.length; i++) {
            String cur = wordsDict[i];
            if(cur.equals(word1)) p1 =  i;
            if(cur.equals(word2)) p2 = i;
            if(p1!=-1 && p2!=-1) {
                dis = Math.min(dis, Math.abs(p1-p2));
            }
        }
        
        return dis;
    }
}

/* =============================================================================
Follow Up
a.optimize it if the function will be called multiple times (most likely LC 244)
时间多的话先演一下（用第一题的算法直接写）
用第一题的算法会超时， 所以初始化的时候变成map，避免equals()方法，使用containsKey

map key: unique word value: list of index
map.get()

word不同:
i = 0, j = 0;
res = Math.min(res, Math.abs(l1.get(i) - l2.get(j)))
p1 < p2 --> i++
p2 < p1 --> j++

word相同：
i = 0, j = 1
res = Math.min(res, Math.abs(l1.get(i) - l2.get(j)))
i++, j++
=============================================================================*/
class WordDistance {
    Map<String, List<Integer>> map;
    int len;
    public WordDistance(String[] wordsDict) {
        this.len = wordsDict.length;
        map = new HashMap<>();
        int index = 0;
        for(String s:wordsDict) {
            map.put(s, map.getOrDefault(s, new ArrayList<Integer>()));
            map.get(s).add(index);
            index++;
        }
    }
    
    public int shortest(String word1, String word2) {
        List<Integer> l1 = map.get(word1);
        List<Integer> l2 = map.get(word2);
        int dis = len, i=0, j=0, pos1, pos2;

        //follow up2: 如果两个word相同
        if(word1.equals(word2)) {
            i = 1;
            int prev = l1.get(0);
            while(i<l1.size()) {
                dis = Math.min(dis, Math.abs(l1.get(i)-prev));
                prev = l1.get(i++);
            }
            return dis;
        }
        //follow up2结束

        while(i<l1.size() && j<l2.size()) {
            pos1 = l1.get(i);
            pos2 = l2.get(j);
            dis = Math.min(dis, Math.abs(pos1 - pos2));
            if(pos1<pos2) i++;
            else j++;
        }
        
        return dis;
    }
}