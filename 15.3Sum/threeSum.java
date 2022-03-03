class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        if(len<3) return res;
        for(int i=0; i<len; i++) {
            if(i>0 && nums[i]==nums[i-1]) continue;
            int start = i+1, end = len-1;
            while(start<end) {
                int sum = nums[i] + nums[start] + nums[end];
                if(sum==0) {
                    List<Integer> temp = new ArrayList<>();
                    // temp.add(nums[i]);
                    // temp.add(nums[start]);
                    // temp.add(nums[end]);
                    // res.add(temp);
                    res.add(Arrays.asList(nums[i],nums[start],nums[end]));
                    while(start<end && nums[start]==nums[start+1]) start++;
                    while(end>start && nums[end]==nums[end-1]) end--;
                    start++;
                    end--;
                }
                else{
                    if(sum>0) end--;
                    else start++;
                }
            }
        }
        
        return res;
    }
    
}