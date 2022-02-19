class Solution {
    public String countAndSay(int n) {
        if(n==1) return "1";
        StringBuilder sb=new StringBuilder();
        char[] says=countAndSay(n-1).toCharArray();
        int i=0;
        while(i<says.length) {
            char curr=says[i++];
            int count=1;
            while(i<says.length && says[i]==curr) {
                i++;
                count++;
            }
            sb.append(count);
            sb.append(curr);
        }
        return sb.toString();
    }
}