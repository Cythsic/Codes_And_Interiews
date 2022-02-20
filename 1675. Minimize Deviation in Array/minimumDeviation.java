class Solution {
    public int minimumDeviation(int[] nums) {
        PriorityQueue<Integer> pq=new PriorityQueue<>((o1,o2)->o2.compareTo(o1));
        int min=Integer.MAX_VALUE, dev=Integer.MAX_VALUE;
        
        for(int i=0; i<nums.length; i++) {
            if(nums[i]%2!=0) {
                nums[i]*=2;
            }
            pq.offer(nums[i]);
            min=Math.min(nums[i],min);
        }
        
        while(!pq.isEmpty()) {
            int top=pq.poll();
            dev=Math.min(top-min,dev);
            if(top%2!=0) break;
            min=Math.min(min,top/2);
            pq.offer(top/2);
        }
        
        return dev;
    }
}


/* Deviation Defination: 当前数组中最大和最小值的差

1. Pre-doubling for all odd numbers --为了后续不做乘法，只做除法
2. Priority Queue随时更新最大值和最小值
3. 顶端值计算完deviation后，除以2重新放入pq中
4. Stop sign：直到再次遇到odd number时，停止循环，返回deviation

*/
