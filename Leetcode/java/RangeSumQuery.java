/*

Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

Example:
Given nums = [-2, 0, 3, -5, 2, -1]

sumRange(0, 2) -> 1
sumRange(2, 5) -> -1
sumRange(0, 5) -> -3
Note:
You may assume that the array does not change.
There are many calls to sumRange function.

*/

import java.util.*;

public class RangeSumQuery {

    //sums of first i elments for leftRightSum[i]
    ArrayList<Integer> leftRightSum = new ArrayList<Integer>();

    public static void main(String[] args){
        

        int[] nums = {-2, 0, 3, -5, 2, -1};

        RangeSumQuery sol = new RangeSumQuery(nums);

        System.out.println(sol.sumRange(0, 2));
        System.out.println(sol.sumRange(2, 5));
        System.out.println(sol.sumRange(0, 5));
    }

    public RangeSumQuery(int[] nums) {

        int sum = 0;
        leftRightSum.add(sum);

        //compute the sum of first i elements for i = 0... nums.length
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
            leftRightSum.add(sum);
        }
    }

    //sum from i to j is the difference between sum from 0 .. i and 0 .. j
    public int sumRange(int i, int j) {
        return leftRightSum.get(j + 1) - leftRightSum.get(i);
    }
}