class Solution {
    public boolean validWordAbbreviation(String word, String abbr) {
        int len=abbr.length(), maxLen = word.length(), i=0, j=0;
        if(len>maxLen) return false;
        for(; i<len && j<maxLen; i++, j++) {
            char c = abbr.charAt(i);
            if(Character.isDigit(c)) {
                if(c=='0') return false;
                int end = i, num=0;
                while(end<len && Character.isDigit(abbr.charAt(end))) {
                    end++;
                    num=Integer.parseInt(abbr.substring(i,end));
                }
                j=j+num;
                if(j==maxLen && end>=len) return true;
                else if(j>=maxLen || end>=len) return false;
                if(word.charAt(j)!=abbr.charAt(end)) return false;
                i=end;
            }
            else {
                if(abbr.charAt(i)!=word.charAt(j)) return false;
            }
        }
        if(i<len || j<maxLen) return false;
        return true;
    }
}