class Solution {
    public int minMeetingRooms(int[][] intervals) {
        if(intervals.length<=1) return 1;
        Arrays.sort(intervals, (a,b) -> a[0]==b[0]? a[1]-b[1] : a[0]-b[0]);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[1]-b[1]);
        for(int[] in : intervals) {
            if(pq.isEmpty()) pq.offer(in);
            else {
                // System.out.println(pq.peek()[1]);
                if(pq.peek()[1]<= in[0]) {
                    pq.poll();
                }
                pq.offer(in);
            }
        }
        
        return pq.size();
    }
}