package com.leetcode.array.twopointers;

/**
 * Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). 
 * n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). 
 * Find two lines, which together with x-axis forms a container, such that the container contains the most water.
    Note: You may not slant the container and n is at least 2.
 * @author wangy
 *
 */
public class ContainerWithMostWater {
    /**
     * Brute force
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int maxArea = -Integer.MAX_VALUE;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int curArea = (j - i) * Math.min(height[i], height[j]);
                maxArea = Math.max(curArea, maxArea);
            }
        }
        return maxArea;
    }
    
    /**
     * Two Pointers
     * @param height
     * @return
     */
    public int maxArea2(int[] height) {
        int maxArea = -Integer.MAX_VALUE;
        int i = 0, j = height.length - 1;
        while (i < j) {
            int curArea = (j - i) * Math.min(height[i], height[j]);
            maxArea = Math.max(curArea, maxArea);
            if (height[i] < height[j]) {
                i++;
            } else {
                j--;
            }
        }
        
        return maxArea;
    }
}
