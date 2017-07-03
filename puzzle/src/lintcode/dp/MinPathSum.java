package lintcode.dp;

/**
 * Given a m x n grid filled with non-negative numbers, 
 * find a path from top left to bottom right which minimizes the sum of all numbers 
 * along its path.

     Notice

    You can only move either down or right at any point in time.
 * @author wangy
 *
 */
public class MinPathSum {
    /**
     * @param grid: a list of lists of integers.
     * @return: An integer, minimizes the sum of all numbers along its path
     */
    public int minPathSum(int[][] grid) {
        
        //let grid by n x m, where there are n rows by m columns
        //let ms[m] be an array where ms[j] at ith row is the min sum to get to to grid[i][j]
        // 0 <= i < n & 0 <= j < m
        //initialy, ms[0] = grid[0][0], ms[1] = ms[0] + grid[0][1] and so on
        int n = grid.length;
        if (n == 0) {
            return 0;
        }
        
        int m = grid[0].length;
        
        int[] minSum = new int[m];
        minSum[0] = grid[0][0];
        for (int j = 1; j < m; j++) {
            minSum[j] = minSum[j-1] + grid[0][j];
        }
        
        //so for j > 0 & i > 0, minSum[j] = min(minSum[j-1], minSum[j]) + grid[i][j]
        //for j = 0 & i > 0, minSum[j] = minSum[j] + grid[i][j]
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (j == 0) {
                    minSum[j] = minSum[j] + grid[i][j];
                } else {
                    minSum[j] = Math.min(minSum[j-1], minSum[j]) + grid[i][j];
                }
            }
        }
        
        return minSum[m-1];
    }
}
