class Solution {
    public int findMaximumXOR(int[] nums) {
        int res=0, mask=0;
        for(int i=31; i>=0; i--) { //greedy从最高位开始试探是否能拿下
            mask = mask | (1<<i);
            Set<Integer> set = new HashSet<>(); //类似two sum，找到是否有两个数异或后使当前试探位为1
            for(int num:nums) {
                set.add(num&mask);
            }
            int tmp = res|(1<<i);
            for(int trie:set) {
                if(set.contains(trie^tmp)) { //异或操作特性：若a^b=c 则 a^c=b
                    res = tmp;
                    break;
                }
            }
        }
        return res;
    }
}