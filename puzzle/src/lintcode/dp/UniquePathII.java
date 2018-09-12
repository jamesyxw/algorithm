package lintcode.dp;

/**
 * Follow up for "Unique Paths":

Now consider if some obstacles are added to the grids. How many unique paths would there be?

An obstacle and empty space is marked as 1 and 0 respectively in the grid.

For example,
There is one obstacle in the middle of a 3x3 grid as illustrated below.

[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]
The total number of unique paths is 2.

 * @author wangy
 *
 */
public class UniquePathII {
    
    public static void main(String[] args) {
        int[][] grid = {{0,0,0}, {0,1,0}, {0,0,0}};
        UniquePathII a = new UniquePathII();
        System.out.println("number of ways: " + a.uniquePathsWithObstacles(grid));
    }
    
    /**
     * @param obstacleGrid: A list of lists of integers
     * @return: An integer
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
      //let grid be n x m
        //for j = 0, i = 0; dp[i] = 0
        //for j = 0, i = 1 to m - 1, dp[i] = 1 given there's no obstacle, 
        //  - if there's obstacle at k, then dp[i] = 0 for i > k
        //for j = 1 to n-1, i = 0, dp[0] = 1 given there's no obstacle
        //  - if there's obstacle at h, then dp[0] = 0 for j > h
        //for 0 < j < n, 0 < i < m,
        //  - if grid[j][i] != obstacle, dp[i] = dp[i] + dp[i-1]
        //  - if grid[j][i] == obstacle, dp[i] = 0;
        
        int n = obstacleGrid.length;
        if (n == 0) {
            return 0;
        }
        
        int m = obstacleGrid[0].length;
        if (m == 0) {
            return 0;
        }
        
        int[] dp = new int[m];
        
        boolean obstacled = obstacleGrid[0][0] == 1;
        dp[0] = obstacled? 0 : 1;
        System.out.print(dp[0] + ", ");
        for (int i = 1; i < m; i++) {
            if(!obstacled && obstacleGrid[0][i] == 1) {
                obstacled = true;
            } 
            
            if (obstacled) {
                dp[i] = 0;
            } else {
                dp[i] = 1;
            }
            System.out.print(dp[i] + ", ");
        }
        System.out.println();
        
        obstacled = obstacleGrid[0][0] == 1;
        for (int j = 1; j < n; j++) {
            if (obstacleGrid[j][0] == 1) {
                obstacled = true;
            }
            dp[0] = obstacled ? 0 : 1;
            System.out.print(dp[0] + ", ");
            for (int i = 1; i < m; i++) {
                if (obstacleGrid[j][i] == 1) {
                    dp[i] = 0;
                } else {
                    dp[i] = dp[i] + dp[i - 1];
                }
                System.out.print(dp[i] + ", ");
            }
            System.out.println();
        }
        
        return dp[m-1];
    }
    
}
