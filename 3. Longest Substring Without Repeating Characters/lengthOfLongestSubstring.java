class Solution {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int left = 0, right = 0, res = Integer.MIN_VALUE, space = 0;
        for(;right<s.length(); right++) {
            char c = s.charAt(right);
            if(map.containsKey(c)) {
                res = Math.max(res, map.size());
                int pos = map.get(c);
                while(left<=pos) {
                    map.remove(s.charAt(left++));
                }
            }
            map.put(c,right);
        }
        res = Math.max(res, map.size());
        
        return res;
    }
}