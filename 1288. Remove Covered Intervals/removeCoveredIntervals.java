class Solution {
    public int removeCoveredIntervals(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0]==o2[0]) return o2[1]-o1[1]; //倒叙排行
                else return o1[0]-o2[0]; //升序排列
            }
        });
        int count=1, max=intervals[0][1];
        for(int i=1; i<intervals.length; i++) {
            if(intervals[i][0]==intervals[i-1][0]) continue;
            if(intervals[i][1]<=max) continue;
            max=intervals[i][1];
            count++;
        }
        return count;
    }
}