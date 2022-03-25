/*=========================================================================
给⼀个排序的Integer的array， [1,2,3,5,6,8,9,12]
1 --> 3
5 --> 6
8 --> 9
12

start = nums[0], end=nums[0]
loop nums -> 1 - nums.length - 1
if(end + 1 == curr) continue loop
if(start == end) output: string start
outpot: string start + "-->" +end --> put into result list
start = curr 
end = curr
=========================================================================*/
/*
Assume
1. no duplicates
*/
class Solution {
    public List<String> summaryRanges1(int[] nums) {
        if(nums.length == 0) return new ArrayList<>();
        int start = nums[0], prev = nums[0];
        List<String> res = new ArrayList<>();
        for(int i=1; i<nums.length; i++) {
            int end = nums[i];
            //for duplicates
            while(end == prev) continue;

            if(prev + 1 < end) {
                if(prev - start == 0) res.add(Integer.toString(start));
                else {
                    StringBuilder sb = new StringBuilder();
                    sb.append(start + "->" + prev);
                    res.add(sb.toString());
                }
                start = end;
            }
            prev = end;
        }
        if(start == nums[nums.length-1]) res.add(Integer.toString(start));
        else {
            StringBuilder sb = new StringBuilder();
            sb.append(start + "->" + prev);
            res.add(sb.toString());
        }
        return res;
    }
/*=========================================================================
Follow Up:
未排序 --> 用set

1. set store array
    min, max
2. while loop(min -> max) --> start = min
start+1 --> in set continue loop
        --> not in set output string--> min = start
=========================================================================*/
    public List<String> summaryRanges2(int[] nums) {
        if(nums.length == 0) return new ArrayList<>();
        List<String> res = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        int min = nums[0], max = nums[0];
        for(int num : nums) {
          set.add(num);
          min = min>num ? num : min;
          max = max<num ? num : max;
        }

        
        while(min <= max) {
          int start = min;
          while(set.contains(min) && min<=max) min++;
          String s = start == min-1? Integer.toString(start) : start + "->" + (min-1);
          res.add(s);
          while(!set.contains(min) && min<=max) min++;
        }
        return res;
    }
}