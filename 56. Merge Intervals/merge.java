class Solution {
    public int[][] merge(int[][] intervals) {
        List<int[]> res = new ArrayList<>();
        Arrays.sort(intervals, (a,b) -> a[0]==b[0]?a[1]-b[1] : a[0]-b[0]);
        int start = intervals[0][0], end = intervals[0][1];
        for(int[] arr:intervals) {
            if(arr[0]<=end) {
                end = Math.max(end, arr[1]);
            }
            else {
                int[] temp = new int[2];
                temp[0] = start;
                temp[1] = end;
                res.add(temp);
                start = arr[0];
                end = arr[1];
            }
        }
        int[] temp = new int[2];
        temp[0] = start;
        temp[1] = end;
        res.add(temp);
        return res.toArray(new int[res.size()][2]);
    }
}