package puzzle.lintcode.microsoft;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import puzzle.lintcode.common.TreeNode;

/**
 * Flatten a binary tree to a fake "linked list" in pre-order traversal.
   Here we use the right pointer in TreeNode as the next pointer in ListNode.

Example
              1
               \
     1          2
    / \          \
   2   5    =>    3
  / \   \          \
 3   4   6          4
                     \
                      5
                       \
                        6
                        
 Challenge
	Do it in-place without any extra memory.
                        
 * @author jamesyxw
 *
 */
public class FlatBinaryTreeToLinkedList {
	
	/**
     * @param root: a TreeNode, the root of the binary tree
     * @return: nothing
     */
    public void flatten(TreeNode root) {
    	if (root == null) {
    		return;
    	}
        List<TreeNode> list = new ArrayList<TreeNode>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        
        stack.push(root);
        while(!stack.isEmpty()) {
        	TreeNode current = stack.pop();
        	list.add(current);
        	
        	if (current.right != null) {
        		stack.push(current.right);
        	}
        	
        	if (current.left != null) {
        		stack.push(current.left);
        	}
        }
        
        TreeNode current = list.get(0);
        
        for (int i = 1; i < list.size(); i++) {
        	current.right = list.get(i);
        	current.left = null;
        	current = list.get(i);
        }
    }
}
