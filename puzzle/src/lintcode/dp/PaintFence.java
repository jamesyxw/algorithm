package lintcode.dp;

/**
 * There is a fence with n posts, each post can be painted with one of the k colors.
    
    You have to paint all the posts such that no more than 
    two adjacent fence posts have the same color.
    
    Return the total number of ways you can paint the fence.
    
    Given n=3, k=2 return 6

          post 1,   post 2, post 3
    way1    0         0       1 
    way2    0         1       0
    way3    0         1       1
    way4    1         0       0
    way5    1         0       1
    way6    1         1       0

 * @author wangy
 *
 */
public class PaintFence {
    
    public static void main(String[] args) {
        PaintFence a = new PaintFence();
        int n = 2, k = 3;
        System.out.println("n=" + n + ", k=" + k + " numWays=" + a.numWays(n, k));
        
        n = 3;
        k = 2;
        System.out.println("n=" + n + ", k=" + k + " numWays=" + a.numWays(n, k));
    }
    
    /**
     * @param n non-negative integer, n posts
     * @param k non-negative integer, k colors
     * @return an integer, the total number of ways
     */
    public int numWays(int n, int k) {
        //Assume dp[i] represents the max # of ways to paint i-th post
        //for i-th post, there are two scenarios:
        //1. paint the different color as i - 1 post, which give it f[i - 1] * (k - 1) options
        //2. paint the same color at i -1 post, which means i - 1 and i - 2 have to be different color,
        //  this then becomes the 1st situation since i - 1 can have f[i - 2] * (k - 1) options, 
        //  so total ways to paint n posts have (k - 1) * (f[i - 1] + f[i -2]) where i = n - 1;
        
        //since # of ways to paint i-th post is only related to (i-1)th and (i-2), we can optimize
        //with rolling array
        
        //impossbile case
        if(n > 2 && k == 1) {
            return 0;
        }
        
        if (n == 0) {
            return 0;
        }
        
        
        int[] dp = new int[3];
        dp[0] = k;
        dp[1] = k * k;
        int factor = k - 1;
        for (int i = 2; i < n; i++) {
            dp[i % 3] = factor * (dp[(i - 1) % 3] + dp[(i - 2) % 3]);
        }
        
        return dp[(n - 1) % 3];
    }
}
