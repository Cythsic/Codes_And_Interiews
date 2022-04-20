class Solution {
    public int titleToNumber(String columnTitle) {
        int len = columnTitle.length()-1;
        int res = 0;
        for(int i=0; i<=len; i++) {
            int curr = (int) Math.pow(26,len-i);
            int c = columnTitle.charAt(i) - 'A' + 1;
            curr *= c;
            res += curr;
        }
        
        return res;
    }
}