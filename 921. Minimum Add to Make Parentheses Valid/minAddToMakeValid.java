class Solution {
    public int minAddToMakeValid(String s) {
        if(s.length()==0) return 0;
        int balance=0, count=0;
        for(char c:s.toCharArray()) {
            if(c=='(') balance++;
            else if(balance>0) balance--;
            else count++;
        }
        return count+balance;
    }
}