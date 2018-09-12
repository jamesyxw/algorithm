package com.leetcode.array.twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.leetcode.array.ArrayUtil;

/**
 * Given an array S of n integers, are there elements a, b, c, and d in S 
 * such that a + b + c + d = target? Find all unique quadruplets in the array 
 * which gives the sum of target.

    Note: The solution set must not contain duplicate quadruplets.
    
    For example, given array S = [1, 0, -1, 0, -2, 2], and target = 0.

    A solution set is:
    [
      [-1,  0, 0, 1],
      [-2, -1, 1, 2],
      [-2,  0, 0, 2]
    ]
 * @author wangy
 *
 */
public class FourSum {
    public static void main(String[] args) {
        FourSum solution = new FourSum();
        List<Integer> inputs = Arrays.asList(0, 1, 1, 1, 1 -3, -2, 3, 4, 1, -5, 2, -1);
        
        List<List<Integer>> result = solution.fourSum(ArrayUtil.convertToArray(inputs), 0);
        ArrayUtil.display(result);
    }
    
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (nums.length < 4 ) {
            return result;
        }
        Arrays.sort(nums);
        
        for(int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i] == nums[i-1]) {
                continue;
            }
            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                int start = j + 1;
                int end = nums.length - 1;
                
                //Optimization
                if (nums[i] + nums[j] + nums[j+1] + nums[j+2] > target) {
                    break;
                } else if (nums[i] + nums[j] + nums[nums.length - 1] + nums[nums.length - 2] < target) {
                    continue;
                }
                
                while (start < end) {
                    int sum = nums[i] + nums[j] + nums[start] + nums[end];
                    if (sum == target) {
                        List<Integer> quadruplet = new ArrayList<Integer>();
                        quadruplet.add(nums[i]);
                        quadruplet.add(nums[j]);
                        quadruplet.add(nums[start]);
                        quadruplet.add(nums[end]);
                        result.add(quadruplet);
                        while(start < end && nums[start] == nums[start + 1]) start++;
                        start++;
                        while(end > start && nums[end] == nums[end - 1]) end--;
                        end--;
                    } else if (sum > target) {
                        while(end > start && nums[end] == nums[end - 1]) end--;
                        end--;
                    } else {
                        while(start < end && nums[start] == nums[start + 1]) start++;
                        start++;
                    }
                }
            }
        }
        
        return result;
    }
}
