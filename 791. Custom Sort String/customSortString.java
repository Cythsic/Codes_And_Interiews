class Solution {
    public String customSortString(String order, String s) {
        int[] map = new int[26];
        for(char c:s.toCharArray()) {
            map[c-'a']++;
        }
        StringBuilder sb = new StringBuilder();
        
        for(char c:order.toCharArray()) {
            while(map[c-'a']>0) {
                sb.append(c);
                map[c-'a']--;
            }
        }
        for(char ch='a'; ch<='z'; ch++) {
            while(map[ch-'a']>0) {
                sb.append(ch);
                map[ch-'a']--;
            }
        }
        
        return sb.toString();
    }
}