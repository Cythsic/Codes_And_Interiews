/* =============================================================================
Question：
Now you have a dice, and throw it multiple times.
Find the possibility the sum of points is a target number

Brute force dfs
TC:O(6^dice) SC:O(1)

step 1: for loop traverse every possible case for each time we throw the dice(1 -> 6)

step 2: add the num of dice to sum

step 3: check if sum == target --> count +1
 				sum > target --> end loop
 				sum < target --> do recursion and throw dice again
step 4: get result by do count / total --> total = 6^dice 
=============================================================================*/
/*
1. how many sides of dice
*/

class Solution{
	public static double getPossibility(int dice, int target) {
		if(dice<=0 || target<dice || target > dice * 6) return 0.0;
		int count = helper(0, target, dice, 0);
		int total = (int) Math.pow(6, dice);

		return (double) count/total;
	}

	public static int helper(int sum, int target, int dice, int count) { 
		if(dice == 0) return count;
		for(int point = 1; point<=6; point++) { //TC:(6)
			sum += point;
			if(sum == target) count++;
			else if(sum>target) break;
			else {
				count += helper(sum, target, dice-1, count); //TC:(6^dice-1)
			}
		}

		return count;
	}

	public static void main(String[] args) {
        Dice_Sum test = new Dice_Sum();
        int dice = 2;
        int target = 2;
        double res1 = test.getPossibility(dice, target);
        System.out.print(res1);
    }
}

/* =============================================================================
Follow Up
优化复杂度 Memorized search --> TC: O(6*dice) SC:O(dice+1 * target+1)
use Memorized recursion to avoid duplicates calculation
we use a 2D array -->in this array we know when we dice x times and get a sum of y, 
					 we can get target m times --> m = arr[dice-x][target-y]
=============================================================================*/
class Solution{
	public static double getPossibility(int dice, int target) {
		if(dice<=0 || target<dice || target > dice * 6) return 0.0;
		int total = (int) Math.pow(6, dice);
		int[][] memo = new int[dice+1][target+1];
		int count = dfs(memo, dice, target);

		return (double) count/total;
	}

	public static int dfs(int[][] memo, int dice, int target) {
		if(dice ==0 && target == 0) return 1;
		else if(memo[dice][target] != 0) return memo[dice][target];

		int count = 0;
		for(int i=1; i<=6; i++) { //TC:O(6)
			count += dfs(memo, dice-1, target-i); //TC:O(dice)
		}

		memo[dice][target] = count; //不要忘记更新memo数组！！！！！
		return count;
	}

	public static void main(String[] args) {
        int dice = 2;
        int target = 2;
        double res1 = getPossibility(dice, target);
        System.out.print(res1);
    }
}