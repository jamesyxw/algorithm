package puzzle.lintcode.amazon;

import java.util.ArrayDeque;
import java.util.Deque;

import puzzle.lintcode.common.TreeNode;
import puzzle.lintcode.common.TreeUtils;

/**
 * You need to construct a binary tree from a string consisting of parenthesis and integers.

The whole input represents a binary tree. It contains an integer followed by zero, one or two pairs of parenthesis. 
The integer represents the root's value and a pair of parenthesis contains a child binary tree with the same structure.

You always start to construct the left child node of the parent first if it exists.

There will only be '(', ')', '-' and '0' ~ '9' in the input string.
An empty tree is represented by "" instead of "()".
Have you met this question in a real interview?  
Example
Given s = "4(2(3)(1))(6(5))", return the tree root node representing the following tree:

       4
     /   \
    2     6
   / \   / 
  3   1 5   
 * @author jamesyxw
 *
 */
public class ConstructBinaryTreeFromString {
    
    public static void main(String[] args) {
        ConstructBinaryTreeFromString sol = new ConstructBinaryTreeFromString();
        TreeNode root = sol.str2tree("4");
        TreeUtils util = new TreeUtils();
        util.preorder(root);
        util.inorder(root);
        
        root = sol.str2tree("4(2(3)(1))(6(5))");
        util.preorder(root);
        util.inorder(root);
    }

    /**
     * @param s: a string
     * @return: a root of this tree
     */
    public TreeNode str2tree(String s) {
        TreeNode root = null;
        if(s == null || s.isEmpty()) {
            return root;
        }
        
        int n = s.length();
        
        Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
        int sign = 1;
        StringBuilder sb = new StringBuilder();
        
        for(int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if(ch == '-') {
                sign = -1;
            } else if (ch >= '0' && ch <= '9') {
                sb.append(ch);
            } else if ((ch == '(' || ch == ')') && !sb.toString().isEmpty()) {
                int num = Integer.valueOf(sb.toString()) * sign;
                TreeNode node = new TreeNode(num);
                if(root == null) {
                    root = node;
                }
                stack.push(node);
                sb = new StringBuilder();
                sign = 1;
            }
            
            if (ch == ')') {
                TreeNode cur = stack.pop();
                TreeNode parent = stack.peek();
                if (parent.left == null) {
                    parent.left = cur;
                } else {
                    parent.right = cur;
                }
            }
        }
        
        return root;
    }
}
