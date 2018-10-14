package puzzle.lintcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import puzzle.lintcode.common.TreeNode;

public class PreOrderTraversal {

    public List<Integer> preorder(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        
        if (root == null) {
            return result;
        }
        
        Stack<TreeNode> s = new Stack<TreeNode>();
        s.push(root);
        
        while(!s.isEmpty()) {
            TreeNode cur = s.pop();
            result.add(cur.val);
            
            if (cur.right != null) {
                s.push(cur.right);
            }
            
            if (cur.left != null) {
                s.push(cur.left);
            }
        }
        
        return result;
    }
}
