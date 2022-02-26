class Solution {
    public int[][] kClosest(int[][] points, int k) {
        Arrays.sort(points, (o1,o2) -> ((o1[0]*o1[0]+o1[1]*o1[1]) - (o2[0]*o2[0]+o2[1]*o2[1])));
        int[][] res = new int[k][2];
        for(int i=0; i<k; i++) {
            res[i] = points[i];
        }
        
        return res; 
    }
}