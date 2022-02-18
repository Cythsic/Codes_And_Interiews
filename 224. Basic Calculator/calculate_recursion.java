class Solution {
    int index=0;
    public int calculate(String s) {
        return helper(s);
    }
    
    int helper(String s) {
        int curr=0, res=0, sign=1;
        Stack<Integer> st=new Stack<>();
        
        for(;index<s.length();index++) {
            char c=s.charAt(index);
            if(Character.isDigit(c)) {
                curr=curr*10+(c-'0');
            }
            else if(c==' ') continue;
            else if(c=='(') {
                index++;
                curr=helper(s);
            }
            else if(c==')') break;
            else {
                st.push(curr*sign);
                curr=0;
                sign=c=='+'?1:-1;
            }
        }
        st.push(curr*sign);
        while(!st.isEmpty()) {
            res+=st.pop();
        }
        return res;
    }
}