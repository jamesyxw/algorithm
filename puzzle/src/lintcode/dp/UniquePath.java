package lintcode.dp;

/**
 * A robot is located at the top-left corner of a m x n grid.

    The robot can only move either down or right at any point in time. 
    The robot is trying to reach the bottom-right corner of the grid.
    
    How many possible unique paths are there?
    
    Given m = 3 and n = 3, return 6.
    Given m = 4 and n = 5, return 35.
    
 * @author wangy
 *
 */
public class UniquePath {
    /**
     * @param n, m: positive integer (1 <= n ,m <= 100)
     * @return an integer
     */
    public int uniquePaths(int m, int n) {
        //this problem can be solved with dynamic programming, 
        //Assum the # of ways to reach grid[i][j] from grid[0][0] is dp[i][j]
        //Since the robot can only move down or right, 
        //for j = 0 to n, dp[0][j] = 1 
        //for i = 0 to m, dp[i][0] = 1 
        //for i > 0 & j > 0, dp[i][j] = dp[i-1][j] + dp[i][j-1]
        //To optimize for space complexity, we can use only dp[i]

        if (m == 0 || n == 0) {
            return 0;
        }
        
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
        }
        
        for (int j = 1; j < m; j++) {
            for (int i = 1; i < n; i++) {
                dp[i] = dp[i-1] + dp[i];
            }
        }
        
        return dp[n-1];
    }

}
