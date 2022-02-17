import java.math.*;
class Solution {
    public int myAtoi(String s) {
        if(s.length()==0) return 0;
        int res=0, i=0, sign=1, len=s.length();
        while(i<len && s.charAt(i)==' ') { i++; }
        if(i<len && (s.charAt(i)=='-' || s.charAt(i)=='+')) {
            if(s.charAt(i++)=='-') sign=-1;
        }
        while(i<len && s.charAt(i)<='9' && s.charAt(i)>='0'){
            if(res>Integer.MAX_VALUE/10 || (res==Integer.MAX_VALUE/10) && s.charAt(i)-'0'>7) {
                if(sign==-1) return Integer.MIN_VALUE;
                return Integer.MAX_VALUE;
            }
            res=(s.charAt(i++)-'0')+res*10;
        }
        return sign*res; 
    }
}