class Solution {
    public String multiply(String num1, String num2) {
        int[] res=new int[num1.length()+num2.length()];
        for(int i=num1.length()-1; i>=0; i--) {
            int j=num2.length()-1,a=num1.charAt(i)-'0';;
            while(j>=0){
                int b=num2.charAt(j)-'0';
                int muti=a*b,carry1=0,carry2=0;
                carry1=(res[i+j+1]+muti%10)/10;
                res[i+j+1]=(res[i+j+1]+muti%10)%10;
                muti/=10;
                carry2=(res[i+j]+muti+carry1)/10;
                res[i+j]=(res[i+j]+muti+carry1)%10;
                
                if((i+j-1)>=0 && carry2>0) res[i+j-1]+=carry2;
                j--;
            }
        }
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<res.length; i++) {
            if(sb.isEmpty() && res[i]==0) continue;
            sb.append(res[i]);
        }
        if(sb.isEmpty()) return "0";
        return sb.toString();
    }
}