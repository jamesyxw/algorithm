package puzzle.leetcode.array.twopointers;

import java.util.Arrays;
import java.util.List;

import puzzle.utils.ArrayUtil;

/**
 * Given an array S of n integers, find three integers in S such that the sum is closest to a given number, 
 * target. Return the sum of the three integers. You may assume that each input would have exactly 
 * one solution.

    For example, given array S = {-1 2 1 -4}, and target = 1.

    The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 * @author wangy
 *
 */
public class ThreeSumClosest {
    
    public static void main(String[] args) {
        ThreeSumClosest solution = new ThreeSumClosest();
        List<Integer> inputs = Arrays.asList(-4,-7,-2,2,5,-2,1,9,3,9,4,9,-9,-3,7,4,1,0,8,5,-7,-7);
        
        int result = solution.threeSumClosest(ArrayUtil.convertToArray(inputs), 29);
        System.out.println(result);
    }
    
    public int threeSumClosest(int[] nums, int target) {
        int result = nums[0] + nums[1] + nums[2];
        Arrays.sort(nums);
        
        for(int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            
            int j = i + 1;
            int k = nums.length - 1;
            
            while(j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == target) {
                    result = target;
                    return result;
                } else if (sum > target) {
                    while(k > j && nums[k] == nums[k-1]) {
                        k--;
                    }
                    k--;
                } else {
                    while(j < k && nums[j] == nums[j+1]) {
                        j++;
                    }
                    j++;
                }
                int minDiff = Math.abs(result - target);
                int curDiff = Math.abs(sum - target);
                result = curDiff < minDiff ? sum : result;
            }
        }
        
        return result;
        
    }
}
