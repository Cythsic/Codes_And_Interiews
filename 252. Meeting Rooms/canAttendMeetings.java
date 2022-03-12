class Solution {
    public boolean canAttendMeetings(int[][] intervals) {
        if(intervals.length<=1) return true;
        Arrays.sort(intervals, (a,b) -> a[0]==b[0]? a[1]-b[1] : a[0]-b[0]);
        
        int[] prev = intervals[0];
        for(int i=1; i<intervals.length; i++) {
            int[] curr = intervals[i];
            if(curr[0]<prev[1]) return false;
            prev = curr;
        }
        
        return true;
    }
}