class Solution {
    public String removeKdigits(String num, int k) {
        if(num.length()==k) return "0";
        Stack<Character> st=new Stack<>();
        for(char c:num.toCharArray()) {
            while(!st.isEmpty() && st.peek()>c && k-->0)
                st.pop();
            if(st.isEmpty() && c=='0') continue;
            st.push(c);
        }
        
        while(k-->0 && !st.isEmpty()) st.pop();
        if(st.isEmpty()) return "0";
        StringBuilder sb=new StringBuilder();
        
        while(!st.isEmpty()) {
            sb.append(st.pop());
        }
        return sb.reverse().toString();
    }
}