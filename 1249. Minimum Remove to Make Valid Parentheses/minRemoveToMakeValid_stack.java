/*
Stack: restore all the positions of invalid parenthes
using function: deleteCharAt() of StringBuilder type to delete invalid chars
*/

class Solution {
    public String minRemoveToMakeValid(String s) {
        StringBuilder sb=new StringBuilder(s);
        Stack<Integer> st=new Stack<>();
        for(int i=0; i<s.length();i++){
            if(s.charAt(i)=='('){
                st.push(i);
            }
            if(s.charAt(i)==')'){
                if(st.size()>0 && s.charAt(st.peek())=='('){
                    st.pop();
                }
                else
                    st.push(i);
            }
        }
        while(st.size()>0){
            sb.deleteCharAt(st.pop());
        }
        return sb.toString();
    }
}