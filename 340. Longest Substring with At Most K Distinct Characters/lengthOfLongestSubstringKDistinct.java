class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int res = 0, left = 0;
        Map<Character, Integer> map = new HashMap<>();
        for(int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c,0)+1);
            while(map.size()>k) {
                char del = s.charAt(left++);
                map.put(del, map.get(del)-1);
                if(map.get(del)==0) map.remove(del);
            }
            res = Math.max(res, i-left+1);
        }
        return res;
    }
}