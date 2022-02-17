class Solution {
    List<String> res=new ArrayList<>();
    public List<String> generateParenthesis(int n) {
        backtrack(n,n);
        return res;
    }
    
    StringBuilder sb = new StringBuilder();
    void backtrack(int left, int right) {
        if(right<left || left<0 || right<0) return;
        if(left==0 && right==0) {
            res.add(sb.toString());
            return;
        }
        
        sb.append('(');
        backtrack(left-1,right);
        sb.deleteCharAt(sb.length()-1);
        
        sb.append(')');
        backtrack(left,right-1);
        sb.deleteCharAt(sb.length()-1);
    }
}