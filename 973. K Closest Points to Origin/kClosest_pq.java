class Solution {
    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> pq=new PriorityQueue<>((o1,o2) -> (o1[0]*o1[0]+o1[1]*o1[1]) - (o2[0]*o2[0]+o2[1]*o2[1]));
        
        for(int[] p:points) {
            pq.offer(p);
        }
        
        int[][] res = new int[k][2];
        for(int i=0; i<k; i++) {
            res[i] = pq.poll();
        }
        return res;
    }
}