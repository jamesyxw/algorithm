package puzzle.lintcode;

import puzzle.lintcode.common.ArrayUtils;

/**
 * Given an array of n integers where n > 1, nums, 
 * return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].
 * 
 * Solve it without division and in O(n).
 * 
 * Example
    For example, given [1,2,3,4], return [24,12,8,6].
    
    Challenge
    Could you solve it with constant space complexity? 
    (Note: The output array does not count as extra space for the purpose of space complexity analysis.)
 * 
 * @author jamesyxw
 *
 */
public class ProductOfArrayExceptSelf {

    public static void main(String[] args) {
        int[] nums = {1,2,3,4};
        ProductOfArrayExceptSelf sol = new ProductOfArrayExceptSelf();
        ArrayUtils.display(sol.productExceptSelf(nums));
    }

    /**
     * @param nums: an array of integers
     * @return: the product of all the elements of nums except nums[i].
     */
    public int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];

        res[0] = 1;
        int n = nums.length;

        //first go from left to right
        //starting at i=1, res[i] stores the product from nums[0] to nums[i-1]
        for(int i = 1; i < n; i++) {
            res[i] = res[i-1] * nums[i-1];
        }

        //then go from right to left,
        //res[n-1] already has the correct value, create a variable k which represents the product from n-1 to i, initialize k=nums[n-1]
        //starting at i=n-2, update res[i] with res[i] = res[i] * k, then update k = k * nums[i]
        int k = nums[n-1];
        for(int j = n-2; j >= 0; j--) {
            res[j] = res[j] * k;
            k = nums[j] * k;
        }

        return res;
    }

}
