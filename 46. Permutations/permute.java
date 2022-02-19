class Solution {
    List<List<Integer>> res=new ArrayList<>();
    public List<List<Integer>> permute(int[] nums) {
        int[] use = new int[nums.length];
        backtrack(nums,use);
        return res;
    }
    
    LinkedList<Integer> temp=new LinkedList<>();
    void backtrack(int[] nums, int[] use) {
        for(int i=0; i<nums.length; i++) {
            if(temp.size()==nums.length) {
                res.add(new LinkedList(temp));
                break;
            }
            if(use[i]==1) continue;
            use[i]=1;
            temp.add(nums[i]);
            backtrack(nums,use);
            temp.removeLast();
            use[i]=0;
        }
        return;
    }
}