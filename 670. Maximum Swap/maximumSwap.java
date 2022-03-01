/*
Tip: Convert int number to array
Step 1: Find first element "firstAsc" in ascending order
Step 2: Find the largest element from firstAsc to the end of array and store the value "max" and position "maxp"
Step 3: Traverse the array from left to right and find the first element which is smaller than max, then swap them
Step 4: Get the result
*/

class Solution {
    public int maximumSwap(int num) {
        char[] arr = Integer.toString(num).toCharArray();
        int prev = arr[0]-'0';
        int index=1;
        //Step 1
        while(index<arr.length && arr[index]-'0'<=prev) {
            prev = arr[index] - '0';
            index++;
        }
        if(index==arr.length) return num; //Test case: 9973

        //Step 2
        int max = arr[index] -'0', maxp=index++;
        for(;index<arr.length; index++) {
            if(max<=arr[index]-'0') { //Test case: 1993
                maxp = index;
                max = arr[index] - '0';
            }
        }
        int res = 0;
        for(int i=0; i<arr.length; i++) {
            //Step 3
            if(max>arr[i]-'0') {
                char temp = arr[i];
                arr[i] = arr[maxp];
                arr[maxp] = temp;
                max = Integer.MIN_VALUE;
            }

            //Step 4
            res = res*10 + (arr[i]-'0');
        }
        return res;
    }
}