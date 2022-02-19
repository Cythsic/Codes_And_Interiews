class Solution {
    List<List<Integer>> res=new ArrayList<>();
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        int[] use = new int[nums.length];
        backtrack(nums,use);
        return res;
    }
    
    LinkedList<Integer> temp=new LinkedList<>();
    void backtrack(int[] nums, int[] use) {
        int i=0;
        while(i<nums.length) {
            if(temp.size()==nums.length) {
                res.add(new LinkedList(temp));
                break;
            }
            if(use[i]==1) {
                i++;
                continue;
            }
            use[i]=1;
            temp.add(nums[i]);
            backtrack(nums,use);
            use[i]=0;
            temp.removeLast();
            int curr=nums[i++];
            while(i<nums.length && nums[i]==curr) i++;
        }
        return;
    }
}