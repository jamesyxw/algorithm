package puzzle.leetcode.array.twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import puzzle.utils.ArrayUtil;

/**
 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? 
 * Find all unique triplets in the array which gives the sum of zero.

    Note: The solution set must not contain duplicate triplets.

 * @author wangy
 *
 */
public class ThreeSum {
    public static void main(String[] args) {
        ThreeSum solution = new ThreeSum();
        List<Integer> inputs = Arrays.asList(0, 1, -3, -2, 3, 4, 1, -5, 2, -1);
        
        List<List<Integer>> result = solution.threeSum2(ArrayUtil.convertToArray(inputs));
        ArrayUtil.display(result);
    }
    
    /**
     * Brute force
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
       
        
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i-1]) {
                continue;
            }
            for (int j = i + 1; j < nums.length - 1; j++) {
                if (j > i + 1 && nums[j] == nums[j-1]) {
                    continue;
                }
                for (int k = j + 1; k < nums.length; k++) {
                    if (k > j + 1 && nums[k] == nums[k-1]) {
                        continue;
                    }
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> triplet = new ArrayList<Integer>();
                        triplet.add(nums[i]);
                        triplet.add(nums[j]);
                        triplet.add(nums[k]);
                        result.add(triplet);
                    }
                }
            }
        }
        return result;
    }
    
    public List<List<Integer>> threeSum2(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        
        for (int i = 0; i < nums.length -2; i++) {
            int j = i + 1;
            int k = nums.length - 1;
            if (i > 0 && nums[i] == nums[i-1]) {
                continue;
            }
            while(j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == 0) {
                    List<Integer> triplet = Arrays.asList(nums[i], nums[j], nums[k]);
                    result.add(triplet);
                    while(k-1 > j && nums[k] == nums[k-1]) {
                        k--;
                    }
                    k--;
                    while(j+1 < k && nums[j] == nums[j+1]) {
                        j++;
                    }
                    j++;
                } else if (sum > 0) {
                    while(k-1 > j && nums[k] == nums[k-1]) {
                        k--;
                    }
                    k--;
                } else {
                    while(j+1 < k && nums[j] == nums[j+1]) {
                        j++;
                    }
                    j++;
                }
            }
        }
        
        return result;
    }
    
    
}
