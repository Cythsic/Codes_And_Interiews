class Solution {
    int index=0;
    public int calculate(String s) {
        return helper(s);
    }
    
    int helper(String s) {
        Stack<Integer> dq=new Stack<>();
        int res=0, curr=0;
        char opr='+';
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
            else if(c==')') {
                break;
            }
            else if(index==s.length()-1 || !Character.isDigit(c)){
                switch(opr){
                    case '+': {dq.push(curr);break;}
                    case '-': {dq.push(curr*-1); break;}
                    case '*': {int temp=dq.pop();
                        dq.push(curr*temp);
                        break;}
                    case '/': {int temp=dq.pop();
                        dq.push(temp/curr);
                        break;}
                }
                curr=0;
                opr=c;
            }
        }
         switch(opr){
                    case '+': {dq.push(curr);break;}
                    case '-': {dq.push(curr*-1); break;}
                    case '*': {int temp=dq.pop();
                        dq.push(curr*temp);
                        break;}
                    case '/': {int temp=dq.pop();
                        dq.push(temp/curr);
                        break;}
                }
        while(dq.size()>0) {
            res+=dq.pop();
        }
        return res;
    }
}