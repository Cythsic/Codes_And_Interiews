//Solution 1: Two Pointers
class Solution1 {
    public int[] twoSum(int[] numbers, int target) {
        int start = 0, end = numbers.length-1;
        int[] res = new int[2];
        while(start<end) {
            if(numbers[start] + numbers[end] >target) {
                int temp = numbers[end--];
                while(numbers[end]==temp) end--;
            }
            else if(numbers[start] + numbers[end] <target) {
                int temp = numbers[start++];
                while(numbers[start]==temp) start++;
            }
            else {
                res[0] = start+1;
                res[1] = end+1;
                break;
            }
        }
        return res;
    }
}