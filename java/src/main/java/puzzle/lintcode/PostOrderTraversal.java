package puzzle.lintcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import puzzle.lintcode.common.TreeNode;

/**
 * left -> right -> root
 * @author jamesyxw
 *
 */
public class PostOrderTraversal {
	/**
     * @param root: A Tree
     * @return: Postorder in ArrayList which contains node values.
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        
        if (root == null) {
        	return result;
        }
        
        
        TreeNode prevNode = null;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        
        while (!stack.isEmpty() ) {
        	TreeNode curNode = stack.peek();
        	//Go down the tree to search for a leaf, if so process it and pop stack, otherwise keep going down
        	if (prevNode == null || prevNode.left == curNode || prevNode.right == curNode) {
        		if (curNode.left != null) {
        			stack.push(curNode.left);
        		} else if (curNode.right != null) {
        			stack.push(curNode.right);
        		} else {
        			stack.pop();
        			result.add(curNode.val);
        		}
        	} 
        	//Go up from the left Node
        	else if (curNode.left == prevNode) {
        		if (curNode.right != null) {
        			stack.push(curNode.right);
        		} else {
        			stack.pop();
        			result.add(curNode.val);
        		}
        	} 
        	//Go up from the right Node
        	else if (curNode.right == prevNode) {
        		stack.pop();
        		result.add(curNode.val);
        	}
        	prevNode = curNode;
        }
        
        return result;
    }
}
