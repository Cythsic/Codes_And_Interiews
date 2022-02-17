class Solution {
    public int reverse(int x) {
        int flag=1;
        if(x<0) {
            flag=-1;
            x*=-1;
        }
        
        int res=0;
        while(x>0) {
            if(flag*res >Integer.MAX_VALUE/10 || flag*res < Integer.MIN_VALUE/10)
                return 0;
            res=res*10+x%10;
            x/=10;
        }
        return res*flag;
    }
}