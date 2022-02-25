/*
loop the heights array from right to left
better than
loop from left to right
*/

class Solution {
    public int[] findBuildings(int[] heights) {
        Stack<Integer> st=new Stack<>();
        int len=heights.length;
        st.push(len-1);
        for(int i=len-2; i>=0; i--) {
            if(heights[i]>heights[st.peek()]) {
                st.push(i);
            }
        }
        int[] res=new int[st.size()];
        for(int i=0; i<res.length; i++) {
            res[i]=st.pop();
        }
        return res;
    }
}