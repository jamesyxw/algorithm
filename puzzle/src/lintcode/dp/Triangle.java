package lintcode.dp;

/**
 * Given a triangle, find the minimum path sum from top to bottom. 
 * Each step you may move to adjacent numbers on the row below.
 * 
 * Bonus point if you are able to do this using only O(n) extra space, 
 * where n is the total number of rows in the triangle.
 * 
 * Example:
 * Given the following triangle:

    [
         [2],
        [3,4],
       [6,5,7],
      [4,1,8,3]
    ]
    
    The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
 * 
 * @author wangy
 *
 */
public class Triangle {
    
    public static void main(String[] args) {
        int[][] triangle = {{2}, {3,4},{6,5,7},{4,1,8,3}};
        
        Triangle a = new Triangle();
        System.out.println(a.minimumTotal2(triangle));
    }
    
    /**
     * The trick of this problem to calculate from bottom to top
     * 
     * why? Because it's much easier to keep track of min path sum at the current level
     * if we know the min path sum for one level below
     * this is becuase dp[i] = min(dp[i], dp[i+1]) + triangle[j][i] given we are calculating
     * min path sum for ith cell at level j
     * 
     * 
     * @param triangle: a list of lists of integers.
     * @return: An integer, minimum path sum.
     */
    public int minimumTotal2(int[][] triangle) {
        int rows = triangle.length;
        
        if (rows == 0) {
            return 0;
        }
        
        int n = triangle[rows - 1].length;
        if(n == 0) {
            return 0;
        }
        
        int[] dp = new int[n];
        int min = Integer.MAX_VALUE;
        
        //Initialize dp
        for(int i = 0; i < n; i++) {
            dp[i] = triangle[rows-1][i];
        }
        
        for(int j = rows - 2; j >= 0; j--) {
            for(int i = 0; i < triangle[j].length; i++) {
                dp[i] = Math.min(dp[i], dp[i+1]) + triangle[j][i];
            }
        }
        
        return dp[0];
    }
    
    
    /**
     * previous solution
     * @param triangle: a list of lists of integers.
     * @return: An integer, minimum path sum.
     */
    public int minimumTotal(int[][] triangle) {
        int m = triangle.length;

        if(m == 0){
            return 0;
        }
        //get the bottom level length
        int n = triangle[m-1].length;

        if(n == 0){
            return 0;
        }

        int [] track = new int[n];

        //Bottom up approach

        //initialize the track with all the bottom values
        for(int i = 0; i < n; i++){
            track[i] = triangle[m-1][i];
        }

        //iterate from the bottom to top
        for(int i = m-2; i >= 0; i--){
            //find the min path from the lower level
            for(int j = 0; j < triangle[i].length; j++){
                track[j] = Math.min(track[j], track[j+1]) + triangle[i][j];
            }
            
        }

        return track[0];
    }

}
