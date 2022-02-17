class Solution {
    List<List<Integer>> res = new ArrayList<>();
    LinkedList<Integer> temp = new LinkedList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        backtrack(0,target,candidates,0);
        return res;
    }
    
    void backtrack(int start, int target, int[] candidates, int sum) {
        if(sum==target) {
            System.out.println(temp);
            if(!res.contains(temp));
                res.add(new LinkedList(temp));
            return;
        }
        if(sum>target) return;
        
        for(int i=start; i<candidates.length; i++) {
            sum+=candidates[i];
            temp.add(candidates[i]);
            backtrack(i, target, candidates, sum);
            sum-=candidates[i];
            temp.removeLast();
        }
        return;
    }
}