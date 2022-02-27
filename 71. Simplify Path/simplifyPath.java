class Solution {
    public String simplifyPath(String path) {
        Stack<String> st=new Stack<>();
        StringBuilder sb = new StringBuilder();
        String[] p = path.split("/");
        
        for(String s:p) {
            if(s.length()==0 || s.equals(".")) continue;
            if(!st.isEmpty() && s.equals("..")) {
                st.pop();
                continue;
            }
            else if(s.equals("..")) continue;
            st.push(s);
        }
        
        while(!st.isEmpty()) {
            sb.insert(0,st.pop());
            sb.insert(0,"/");
        }
        if(sb.length()==0) sb.append("/");
        return sb.toString();
    }
}