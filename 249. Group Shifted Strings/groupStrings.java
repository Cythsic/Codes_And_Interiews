/*
Examples:
["aa","ccc","b"] --> if use int type to count all gaps between characters, such test cases will be one list rather than 3;

["abc","xyz","al"] --> if use int type append to string, such test cases will be one list rather than 2, because:
"abc" and "xyz": "1" + "1"
"al": "11"

So it shoule convert int to character to append to stringbuilder.
*/

class Solution {
    public List<List<String>> groupStrings(String[] strings) {
        Map<String, List<String>> map = new HashMap<>();
        List<List<String>> res = new ArrayList<>();
        
        for(String s:strings) {
            StringBuilder sb = new StringBuilder();
            for(int i=1; i<s.length(); i++) {
                char c = s.charAt(i);
                int inc = c-s.charAt(i-1);
                if(inc<0) {
                    inc += 26;
                }
                sb.append((char) inc+'a');
            }
            if(!map.containsKey(sb.toString())) map.put(sb.toString(), new ArrayList<String>());
            map.get(sb.toString()).add(s);
        }
        
        for(String gap:map.keySet()) {
            res.add(map.get(gap));
        }
        
        return res;
    }
}