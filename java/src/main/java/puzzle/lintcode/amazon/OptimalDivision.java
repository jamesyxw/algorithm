package puzzle.lintcode.amazon;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a list of positive integers, the adjacent integers will perform the float division. For example, [2,3,4] -> 2 / 3 / 4.

    However, you can add any number of parenthesis at any position to change the priority of operations. 
    You should find out how to add parenthesis to get the maximum result, and return the corresponding expression in string format. 
    Your expression should NOT contain redundant parenthesis.
    
    1.The length of the input array is [1, 10].
    2.Elements in the given array will be in range [2, 1000].
    3.There is only one optimal division for each test case.
    
    Example
    Input: [1000,100,10,2]
    Output: "1000/(100/10/2)"
    Explanation:
    1000/(100/10/2) = 1000/((100/10)/2) = 200
    However, the bold parenthesis in "1000/((100/10)/2)" are redundant, 
    since they don't influence the operation priority. So you should return "1000/(100/10/2)". 
    
    Other cases:
    1000/(100/10)/2 = 50
    1000/(100/(10/2)) = 50
    1000/100/10/2 = 0.5
    1000/100/(10/2) = 2
    
 * @author jamesyxw
 *
 */
public class OptimalDivision {
    
    public static void main(String[] args) {
        int[] inputs = {10000, 1000, 100, 10, 2};
        OptimalDivision sol = new OptimalDivision();
        System.out.println(sol.optimalDivision(inputs));
    }

    /**
     * @param nums: an array
     * @return: the corresponding expression in string format
     */
    public String optimalDivision(int[] nums) {
       int n = nums.length;
       StringBuilder sb = new StringBuilder();
       if(n == 1) {
           sb.append(nums[0]);
           return sb.toString();
       }
       
       if(n == 2) {
           sb.append(nums[0]).append("/").append(nums[1]);
           return sb.toString();
       }
       
       sb.append(nums[0]).append("/(");
       for(int i = 1; i < n - 1; i++) {
           sb.append(nums[i]).append("/");
       }
       
       sb.append(nums[n-1]).append(")");
       
       return sb.toString();
    }

    
}
