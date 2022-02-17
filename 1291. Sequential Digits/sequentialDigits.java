class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        int carry = 11, curr = 1, start = 1, loop = 8; //loop表示每层的sequential数的个数，
        List<Integer> res = new ArrayList<>();
        while(curr<=high) {
            curr = start;
            for(int i = 0; i<loop; i++) {
                curr += carry;
                if(curr>=low && curr<=high) res.add(curr);
            }
            start = start*10 + 10-loop;
            carry = carry*10 +1;
            loop--;
        }
        return res;
    }
}