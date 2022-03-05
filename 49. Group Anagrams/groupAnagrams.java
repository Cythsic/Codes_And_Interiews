class Solution {
    public List<List<String>> (String[] strs) {
        List<List<String>> res = new ArrayList<>();
        if(strs.length==0) return res;
        HashMap<String, List<String>> map = new HashMap<>();
        for(String s:strs) {
            String key = getKeys(s);
            map.put(key, map.getOrDefault(key,new ArrayList<String>()));
            map.get(key).add(s);
        }
        
        for(String key:map.keySet()) {
            res.add(map.get(key));
        }
        
        return res;
    }
    
    String getKeys(String str) {
        int[] ch = new int[26];
        for(char c:str.toCharArray()) {
            ch[c-'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<26; i++) {
            sb.append(ch[i]+",");
        }
        
        return sb.toString();
    }
}