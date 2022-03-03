//Solution 2: Binary Search + HashMap
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int start = 0, end = numbers.length-1;
        int[] res = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        while(start<=end) {
            int left = target - numbers[start];
            int right = target - numbers[end];
            
            if(map.containsKey(left)) {
                res[0] = Math.min(map.get(left), start)+1;
                res[1] = Math.max(map.get(left), start)+1;
                break;
            }
            map.put(numbers[start],start);
            
            if(map.containsKey(right)) {
                res[0] = Math.min(map.get(right), end)+1;
                res[1] = Math.max(map.get(right), end)+1;
                break;
            }
            map.put(numbers[end], end);
            
            start++;
            end--;
        }
        return res;
    }
}