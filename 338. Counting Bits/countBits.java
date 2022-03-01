/*
0 --> 0
1 --> 1
2 --> 10
3 --> 11
4 --> 100
5 --> 101

As we can see:
for even numbers: 
6/2 = 3,
6 has two 1's, then 3 also has two 1's

for odd numbers:
5/2 = 2,
5 has two 1's, but 2 has one 1's

So conclusion:
If we use bit manupilation: if x is even --> res[x] = res[x>>1]
                            if x is odd  --> res[x] = res[x>>1] + 1

Example: 
5 = 101
5>>1 = 10 = 2

6 = 110
6>>1 = 11 = 3
*/

//TC: O(n)
//SC: O(1)

class Solution {
    public int[] countBits(int n) {
        int[] res = new int[n+1];
        for(int i=0; i<=n; i++) {
            if(i%2==0) res[i] = res[i>>1];
            else res[i] = res[i>>1]+1;
        }
        
        return res;
    }
}