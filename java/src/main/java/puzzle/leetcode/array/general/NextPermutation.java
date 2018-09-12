package puzzle.leetcode.array.general;

import java.util.Arrays;

import puzzle.utils.ArrayUtil;

/**
 * Implement next permutation, which rearranges numbers into the lexicographically 
 * next greater permutation of numbers.

    If such arrangement is not possible, it must rearrange it as the lowest possible order 
    (ie, sorted in ascending order).
    
    The replacement must be in-place, do not allocate extra memory.
    
    Here are some examples. Inputs are in the left-hand column and its corresponding outputs 
    are in the right-hand column.
    
    1,2,3 ¡ú 1,3,2
    3,2,1 ¡ú 1,2,3
    1,1,5 ¡ú 1,5,1
    
 * @author wangy
 *
 */
public class NextPermutation {
    public static void main(String[] args) {
        NextPermutation solution = new NextPermutation();
        int[] input1 = ArrayUtil.convertToArray(Arrays.asList(1, 2, 3));
        solution.nextPermutation(input1);
        ArrayUtil.display(input1);
        
        int[] input2 = ArrayUtil.convertToArray(Arrays.asList(3, 2, 1));
        solution.nextPermutation(input2);
        ArrayUtil.display(input2);
        
        int[] input3 = ArrayUtil.convertToArray(Arrays.asList(1, 1, 5));
        solution.nextPermutation(input3);
        ArrayUtil.display(input3);
        
        int[] input4 = ArrayUtil.convertToArray(Arrays.asList(1, 2, 3, 4));
        solution.nextPermutation(input4);
        ArrayUtil.display(input4);
        
        int[] input5 = ArrayUtil.convertToArray(Arrays.asList(1, 1, 4, 3, 3, 2, 3));
        solution.nextPermutation(input5);
        ArrayUtil.display(input5);
        
        int[] input6 = ArrayUtil.convertToArray(Arrays.asList(1, 2));
        solution.nextPermutation(input6);
        ArrayUtil.display(input6);
        
        int[] input7 = ArrayUtil.convertToArray(Arrays.asList(2, 3, 1));
        solution.nextPermutation(input7);
        ArrayUtil.display(input7);
        
//        int[] input8 = ArrayUtil.convertToArray(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8));
//        ArrayUtil.display(input8);
//        reverse(input8, 0, 2);
//        ArrayUtil.display(input8);
    }
    public void nextPermutation(int[] nums) {
        if (nums.length <= 1) {
            return;
        }
        int len = nums.length;
        
        if (nums[len - 1] > nums[len - 2]) {
            swap(nums, len - 1, len -2);
            return;
        }
        
        int i = nums.length - 1;
        while (i > 0 && nums[i] <= nums[i-1]) {
            i--;
        }
        
        reverse(nums, i, nums.length -1);
        
        if (i > 0) {
            int j = i - 1;
            while(i < nums.length && nums[j] >= nums[i]) {
                i++;
            }
            swap(nums, i, j);
        }
    }
    
    public static void reverse(int[] input, int i, int j) {
        if (i < 0 || i >= input.length || j < i || j >= input.length) {
            throw new IllegalArgumentException();
        }
        
        for(int k = i; k < i + (j - i + 1)/2; k++) {
            swap(input, k, j - (k-i));
        }
    }
    
    public static void swap(int[] input, int i, int j) {
        int temp = input[i];
        input[i] = input[j];
        input[j] = temp;
    }
}
