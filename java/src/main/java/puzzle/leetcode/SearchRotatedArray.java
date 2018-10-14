package puzzle.leetcode;

import java.util.Arrays;

import puzzle.utils.ArrayUtil;

/**
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

    (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
    
    You are given a target value to search. If found in the array return its index, otherwise return -1.
    
    You may assume no duplicate exists in the array.


 * @author wangy
 *
 */
public class SearchRotatedArray {
    public static void main(String[] args) {
        SearchRotatedArray solution = new SearchRotatedArray();
        
        int[] input1 = ArrayUtil.convertToArray(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8));
        System.out.println(solution.search(input1, 4));
        
        int[] input2 = ArrayUtil.convertToArray(Arrays.asList(4, 5, 6, 7, 0, 1, 2));
        System.out.println(solution.search(input2, 1));
        
        int[] input3 = ArrayUtil.convertToArray(Arrays.asList(1));
        System.out.println(solution.search(input3, 1));
        
        int[] input4 = ArrayUtil.convertToArray(Arrays.asList(2, 1));
        System.out.println(solution.search(input4, 1));
        
        int[] input5 = ArrayUtil.convertToArray(Arrays.asList(1, 3, 5));
        System.out.println(solution.search(input5, 6));
    }
    
    public int search(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }
        int start = 0;
        int end = nums.length - 1;
        
        while (start < end - 1) {
            
            int mid = start + (end - start)/2;
            if (target == nums[mid]) {
                return mid;
            }
            
            
            if (nums[start] < nums[mid]) {
              //first half is not rotated
                if (target >= nums[start] && target <= nums[mid]) {
                    //target is in the 1st half
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {
                //second half is not rotated
                if (target >= nums[mid] && target <= nums[end]) {
                    //target is within 2nd half
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }
        
        if (nums[start] == target ) {
            return start;
        } else if (nums[end] == target) {
            return end;
        }
        
        return -1;
    }
}
