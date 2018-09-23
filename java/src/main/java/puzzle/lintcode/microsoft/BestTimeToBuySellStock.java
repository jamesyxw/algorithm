package puzzle.lintcode.microsoft;

import puzzle.lintcode.common.ArrayUtils;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.

    If you were only permitted to complete at most one transaction 
    (ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.
    
    Example
    Given array [3,2,3,1,2], return 1.

 * @author jamesyxw
 *
 */
public class BestTimeToBuySellStock {
    public static void main(String[] args) {
        int [][] prices = {
                {3, 2, 3, 1, 2}
        };
        
        for (int i = 0; i < prices.length; i++) {
            ArrayUtils.display(prices[i]);
            System.out.println(maxProfit(prices[i]));
        }
    }
    
    /**
     * @param prices: Given an integer array
     * @return: Maximum profit
     */
    public static int maxProfit(int[] prices) {
        if (prices.length <= 1) {
            return 0;
        }
        
        int maxProfit = Integer.MIN_VALUE;
        
        //f[i] = the lowest price of the stock up until ith day
        int [] f = new int[prices.length];
        f[0] = prices[0];
        
        for (int i = 1; i < prices.length; i++) {
            f[i] = prices[i] < f[i - 1] ? prices[i] : f[i - 1];
            if (prices[i] - f[i - 1] > maxProfit) {
                maxProfit = prices[i] - f[i - 1];
            }
        }
        
        return maxProfit;
    }
}
