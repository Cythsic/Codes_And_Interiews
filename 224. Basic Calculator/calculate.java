class Solution {
    public int calculate(String s) {
        int curr=0, res=0, opr=1;
        Stack<Integer> st=new Stack<>();
        
        for(int i=0;i<s.length(); i++) {
            char c=s.charAt(i);
            if(c-'0'>=0 && c-'0'<=9) {
                curr=c-'0';
                while(i+1<s.length() && Character.isDigit(s.charAt(i+1))) {
                    curr=curr*10+(s.charAt(++i)-'0');
                }
                res+=curr*opr;
            }
            else if(c=='+') opr=1;
            else if(c=='-') opr=-1;
            else if(c==' ') continue;
            else if(c=='(') {
                st.push(res);
                st.push(opr);
                res=0;
                opr=1;
            }
            else if(c==')') {
                res=res*st.pop()+st.pop();
            }
        }
        
        return res;
    }
}