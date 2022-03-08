class Solution {
    public int longestSubstring(String s, int k) {
        int res = 0;
        for(int unique=1; unique<=26; unique++) {
            int left = 0, count = 0;
            Map<Character, Integer> map = new HashMap<>();
            for(int i=0; i<s.length(); i++) {
                char c = s.charAt(i);
                map.put(c, map.getOrDefault(c,0)+1);
                if(map.get(c)==k) count++;
                while(map.keySet().size()>unique) {
                    char del = s.charAt(left++);
                    if(map.get(del)==k) count--;
                    map.put(del, map.get(del)-1);
                    if(map.get(del)==0) map.remove(del);
                }
                if(count == map.keySet().size()) res = Math.max(res, i-left+1);
            }
        }
        
        return res;
    }
}