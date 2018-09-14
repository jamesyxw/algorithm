package puzzle.lintcode.problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import puzzle.lintcode.common.TreeNode;

/**
 * left -> root -> right
 * @author jamesyxw
 *
 */
public class InOrderTraversal {
	/**
     * @param root: A Tree
     * @return: Inorder in ArrayList which contains node values.
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        
        if (root == null) {
        	return result;
        }
        
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode curNode = root;
        
        while(!stack.isEmpty() || curNode != null) {
        	if (curNode != null) {
        		stack.push(curNode);
        		curNode = curNode.left;
        	} else {
        		curNode = stack.pop();
        		result.add(curNode.val);
        		curNode = curNode.right;
        	}
        }
        
        return result;
    }
}
