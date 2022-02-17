class Solution {
    List<String> res=new ArrayList<>();
    HashMap<Character, String> map = new HashMap<>();
    StringBuilder sb=new StringBuilder();
    public List<String> letterCombinations(String digits) {
        if(digits.length()==0) return res;
        map.put('2',"abc");
        map.put('3',"def");
        map.put('4',"ghi");
        map.put('5',"jkl");
        map.put('6',"mno");
        map.put('7',"pqrs");
        map.put('8',"tuv");
        map.put('9',"wxyz");
        
        backtrack(digits, 0);
        return res;
    }
    
    void backtrack(String digits, int start) {
        if(digits.length()==1) {
            String choices=map.get(digits.charAt(start));
            for(char ch:choices.toCharArray()) {
                sb.append(ch);
                res.add(sb.toString());
                int len=sb.length();
                sb.deleteCharAt(len-1);
            }
            return;
        }
        String p=map.get(digits.charAt(start));
        for(char ch:p.toCharArray()) {
            sb.append(ch);
            backtrack(digits.substring(start+1),start);
            int len=sb.length();
            sb.deleteCharAt(len-1);
        }
    }
}