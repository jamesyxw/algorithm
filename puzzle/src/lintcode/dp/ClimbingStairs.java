package lintcode.dp;

/**
 * You are climbing a stair case. It takes n steps to reach to the top.
    Each time you can either climb 1 or 2 steps. 
    In how many distinct ways can you climb to the top?
    
    Example
    Given an example n=3 , 1+1+1=2+1=1+2=3
    
    return 3

 * @author wangy
 *
 */
public class ClimbingStairs {
    public static void main(String[] args) {
        ClimbingStairs a = new ClimbingStairs();
        for (int i = 0; i < 20; i++) {
            System.out.println("n = " + i + " it takes " + a.climbStairs(i));
        }
    }
    
    /**
     * @param n: An integer
     * @return: An integer
     */
    public int climbStairs(int n) {
         int[] s = new int[n + 1];
        
        //let s[i] represents the # of ways to reach the top after i steps
        //so obviously s[0] = 0 since there's no ways to reach 0 steps with 1 or 2 steps. 
        //s[1] = 1 since there's only one way to get 1 step, which is to take 1 step exactly. 
        //and also s[i] = s[i -1] + s[i - 2]
        if (n == 0) {
            return 1;
        } else if (n == 1) {
             return 1;
        } else if (n == 2) {
            return 2;
        }
        
        s[0] = 0;
        s[1] = 1;
        s[2] = 2; 
        for (int i = 3; i < n + 1; i++) {
            s[i] = s[i - 1] + s[i - 2];
        }

        return s[n];
    }
    
    /**
     * @param n: An integer
     * @return: An integer
     */
    public int climbStairs_O1(int n) {
        //let s[i] represents the # of ways to reach the top after i steps
        //so obviously s[0] = 0 since there's no ways to reach 0 steps with 1 or 2 steps. 
        //s[1] = 1 since there's only one way to get 1 step, which is to take 1 step exactly. 
        //and also s[i] = s[i -1] + s[i - 2]
        if (n == 0) {
            return 0;
        } else if (n == 1) {
             return 1;
        } else if (n == 2) {
            return 2;
        }
        
        int prev1 = 1;
        int cur = 2;
        for (int i = 3; i < n + 1; i++) {
            int temp = cur;
            cur = cur + prev1;
            prev1 = temp;
            
        }

        return cur;
    }
}
