/**
 * Example:
 * [[10,20], [30,200], [40,500], [30, 20]]
 * 
 * Just for sake in the starting assume that we are sending first half to cityA & Second half to cityB
 * [[10,20], [30,200], [40,500], [30,20]]
 *   A        A            B         B
 * Total cost is: 10 + 30 + 500 + 20 = 560
 * 
 * Then, we calculate what if we send an interviewer to the two city, what the difference will be?
 * A:    10  30   40   30
 * B:    20  200  500  20
 * Diff: -10 -170 -460 10
 * 
 *  We sort the diff by ascending order, the first n interviewer are the best n to be sent to city A.
 */

class Solution {
    public int twoCitySchedCost(int[][] costs) {
        Arrays.sort(costs, (a,b) -> a[0]-a[1]-(b[0]-b[1]));
        int cost = 0;
        for(int i=0; i<costs.length/2; i++) {
            cost += costs[i][0] + costs[i+costs.length/2][1];
        }
        return cost;
    }
}