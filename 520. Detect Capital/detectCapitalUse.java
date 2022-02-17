class Solution {
    public boolean detectCapitalUse(String word) {
        int isLow = 0, isCap = 0;
        for(int i=0; i<word.length(); i++) {
            char ch = word.charAt(i);
            if(ch>='A' && ch<='Z' && i!=0) isCap=1;
            if(ch>='A' && ch<='Z' && isLow==1) return false;
            if(ch>='a' && ch<='z' && isCap==1) return false;
            if(ch>='a' && ch<='z') isLow=1;
        }
        return true;
    }
}