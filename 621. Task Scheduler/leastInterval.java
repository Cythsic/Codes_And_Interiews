class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] map=new int[26];
        for(char c:tasks) {
            map[c-'A']++; //count numbers of each task
        }
        
        Arrays.sort(map);
        int idles=(map[25]-1)*n; //count total numbers of idles, the last line doesn't need idle, so it shoule be map[25]-1
        
        for(int i=24; i>=0; i--) {
            idles-=Math.min(map[i],map[25]-1); //fill the idles, if map[i] is larger than map[25]-1, it should be map[25]-1
        }
        
        return idles>0?idles+tasks.length : tasks.length; 
    }
}