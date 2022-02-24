/*
HashSet: restore all invalid positions of parenthes
*/
class Solution {
    public String minRemoveToMakeValid(String s) {
        int balance=0;
        StringBuilder sb = new StringBuilder();
        char[] ch = s.toCharArray();
        Set<Integer> set = new HashSet<>();
        
        for(int i=0; i<ch.length; i++) {
            if(ch[i]=='(') balance++;
            else if(ch[i]==')') {
                if(balance>0) balance--;
                else set.add(i);
            }
        }
        if(balance>0) {
            for(int i=ch.length-1; i>=0; i--) {
            if(ch[i]=='(') {
                balance--;
                set.add(i);
            }
            if(balance==0) break;
            }
        }
        
        for(int c=0; c<ch.length; c++) {
            if(set.contains(c)) continue;
            sb.append(ch[c]);
        }
        
        return sb.toString();
    }
}