class Solution {
    public boolean validPalindrome(String s) {
        int left=0, right=s.length()-1;
        while(left<right) {
            char l=s.charAt(left), r=s.charAt(right);
            if(l!=r) {
                return valid(s, left+1, right) || valid(s, left, right-1);
            }
            left++;
            right--;
        }
        return true;
    }
    
    boolean valid(String s, int left, int right) {
        while(left<right) {
            char l=s.charAt(left), r=s.charAt(right);
            if(l!=r) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}