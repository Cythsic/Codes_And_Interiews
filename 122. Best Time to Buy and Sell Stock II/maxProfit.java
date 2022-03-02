class Solution {
    //Solution 1
    public int maxProfit(int[] prices) {
        int prof = 0;
        int i = 0;
        while(i<prices.length-1) {
            if(prices[i]>=prices[i+1]) {
                i++;
                continue;
            }
            int buy = prices[i];
            while(i<prices.length-1 && prices[i]<prices[i+1]) i++;
            prof += prices[i] - buy;
            i++;
        }
        
        return prof;
    }

    // Solution 2

    // public int maxProfit(int[] prices) {
    //     int profit=0;
    //     for(int i=0;i<prices.length-1;i++){
    //         if(prices[i+1]>prices[i])
    //             profit+=prices[i+1]-prices[i];
    //     }
    //     return profit;
    // }
}