package puzzle.lintcode.microsoft;

import puzzle.lintcode.common.TreeNode;

/**
 * Given an integer array with no duplicates. A maximum tree building on this array is defined as follow:

	The root is the maximum number in the array.
	The left subtree is the maximum tree constructed from left part subarray divided by the maximum number.
	The right subtree is the maximum tree constructed from right part subarray divided by the maximum number.
	Construct the maximum tree by the given array and output the root node of this tree.
	
	Example:
	Input: [3,2,1,6,0,5]
	Output: return the tree root node representing the following tree:
	
	      6
	    /   \
	   3     5
	    \    / 
	     2  0   
	       \
	        1

 * @author jamesyxw
 *
 */
public class MaxBinaryTree {

	 /**
     * @param nums: an array
     * @return: the maximum tree
     */
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        TreeNode root = buildMaxBinaryTree(nums, 0, nums.length - 1);
        return root;
    }
    
    private TreeNode buildMaxBinaryTree(int [] nums, int start, int end) {
    	if (start == end) {
    		return new TreeNode(nums[start]);
    	} else if (start > end) {
    		return null;
    	}
    	int maxIndex = findMaxIndex(nums, start, end);
    	TreeNode root = new TreeNode(nums[maxIndex]);
        root.left = buildMaxBinaryTree(nums, start, maxIndex - 1);
        root.right = buildMaxBinaryTree(nums, maxIndex + 1, end);
        return root;
    }
    
    private int findMaxIndex(int[] nums, int start, int end) {
    	int index = start;
    	for (int i = start; i <= end; i++) {
    		if (nums[i] > nums[index]) {
    			index = i;
    		}
    	}
    	return index;
    }
    
}
