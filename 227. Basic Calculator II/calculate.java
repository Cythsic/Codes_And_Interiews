class Solution {
    public int calculate(String s) {
        int res=0,flag=1,curr=0;
        char opr='+';
        Stack<Integer> st = new Stack<>();
        for(char ch:s.toCharArray()) {
            if(ch-'0'>=0 && ch-'0'<=9) {
                curr=curr*10+(ch-'0');
                
            }
            else if(ch==' ') continue;
            else{
                switch(opr) {
                    case '+': break;
                    case '-': curr*=-1; break;
                    case '*': curr*=st.pop(); break;
                    case '/': curr=st.pop()/curr; break;
                }
                st.push(curr);
                opr=ch;
                curr=0;
            }
        }
        switch(opr) {
                    case '+': break;
                    case '-': curr*=-1; break;
                    case '*': curr*=st.pop(); break;
                    case '/': curr=st.pop()/curr; break;
                }
                st.push(curr);
        while(!st.isEmpty()) {
            res+=st.pop();
        }
        return res;
    }
}