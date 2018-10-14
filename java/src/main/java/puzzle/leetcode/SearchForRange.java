package puzzle.leetcode;

import java.util.Arrays;

import puzzle.utils.ArrayUtil;

/**
 * Given an array of integers sorted in ascending order, 
 * find the starting and ending position of a given target value.

    Your algorithm's runtime complexity must be in the order of O(log n).

    If the target is not found in the array, return [-1, -1].

    For example,
    Given [5, 7, 7, 8, 8, 10] and target value 8,
    return [3, 4].
    
 * @author wangy
 *
 */
public class SearchForRange {
    public static void main(String[] args) {
        SearchForRange solution = new SearchForRange();
        
        int[] input1 = ArrayUtil.convertToArray(Arrays.asList(5, 7, 7, 8, 8, 10));
        ArrayUtil.display(solution.searchRange(input1, 8));
        
        int[] input2 = ArrayUtil.convertToArray(Arrays.asList(1));
        ArrayUtil.display(solution.searchRange(input2, 1));
        
        int[] input3 = ArrayUtil.convertToArray(Arrays.asList(1, 1));
        ArrayUtil.display(solution.searchRange(input3, 1));
        
        int[] input4 = ArrayUtil.convertToArray(Arrays.asList(1, 1));
        ArrayUtil.display(solution.searchRange(input4, 2));
        
        
        int[] input5 = ArrayUtil.convertToArray(Arrays.asList(5, 5, 5, 5, 5, 5));
        ArrayUtil.display(solution.searchRange(input5, 5));
        
        int[] input6 = ArrayUtil.convertToArray(Arrays.asList(1,4));
        ArrayUtil.display(solution.searchRange(input6, 4));
        
        int[] input7 = ArrayUtil.convertToArray(Arrays.asList(1,2,3));
        ArrayUtil.display(solution.searchRange(input7, 2));
    }
    
    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[2];
        result[0] = -1;
        result[1] = -1;
        
        if (nums.length == 0) {
            return result;
        }
        
        if (nums.length == 1) {
            if (nums[0] == target) {
                result[0] = 0;
                result[1] = 0;
            }
            return result;
        }
        
        int start = 0;
        int end = nums.length - 1;
        
        while (start < end - 1) {
            int mid = start + (end - start)/2;
            if (nums[mid] == target && (mid == 0 || nums[mid - 1] != target)) {
                result[0] = mid;
                break;
            }
            
            if (nums[mid] == target || nums[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
//            System.out.println("    1. " + start + " " + mid + " " + end);
        }
        
        //cannot find the start, return directly
        if (result[0] == -1) {
            if (nums[start] != target && nums[end] != target) {
                return result;
            }
            result[0] = nums[start] == target ? start : end;
        }
        
        //searching the ending
        start = result[0];
        end = nums.length - 1;
        
        while (start < end - 1) {
            int mid = start + (end - start)/2;
            if (nums[mid] == target && (mid == nums.length - 1 || nums[mid + 1] != target)) {
                result[1] = mid;
                break;
            }
            
            if (nums[mid] == target || nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
//            System.out.println("    2. " + start + " " + mid + " " + end);
        }
        
        if (result[1] == -1) {
            result[1] = nums[end] == target ? end : start;
        }
        
        return result;
    }
}
