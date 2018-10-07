package puzzle.lintcode.amazon;

import puzzle.lintcode.common.TreeNode;

/**
 * Given a binary tree with n nodes, your task is to check if it's possible to partition the tree to two trees 
 * which have the equal sum of values after removing exactly one edge on the original tree.

    The range of tree node value is in the range of [-100000, 100000].
    1 <= n <= 10000

    Example
    Given     
        5
       / \
      10 10
        /  \
       2   3
    
    return True
    Explanation: 
        5
       / 
      10
          
    Sum: 15
    
       10
      /  \
     2    3
    
    Sum: 15
    Given   
        1
       / \
      2  10
        /  \
       2   20
    
    return False
    
    Explanation: 
    You can't split the tree into two trees with equal sum after removing exactly one edge on the tree.
    
 * @author jamesyxw
 *
 */
public class EqualTreePartition {
    
    public static void main(String[] args) {
        EqualTreePartition sol = new EqualTreePartition();
        TreeNode a1 = new TreeNode(5);
        TreeNode a2 = new TreeNode(10);
        TreeNode a3 = new TreeNode(10);
        TreeNode a4 = new TreeNode(2);
        TreeNode a5 = new TreeNode(3);
        
        a1.left = a2;
        a1.right = a3;
        a3.left = a4;
        a3.right = a5;
        System.out.println(sol.checkEqualTree(a1));
        
        TreeNode b1 = new TreeNode(1);
        TreeNode b2 = new TreeNode(2);
        TreeNode b3 = new TreeNode(10);
        TreeNode b4 = new TreeNode(2);
        TreeNode b5 = new TreeNode(20);
        
        b1.left = b2;
        b1.right = b3;
        b3.left = b4;
        b3.right = b5;
        System.out.println(sol.checkEqualTree(b1));
    }
    
    /**
     * @param root: a TreeNode
     * @return: return a boolean
     */
    public boolean checkEqualTree(TreeNode root) {
        // write your code here
        if (root == null) {
            return false;
        }
        
        int totalSum = sum(root);
        
        return checkEqual(root, totalSum);
    }

    private boolean checkEqual(TreeNode current, int totalSum) {
        if (current == null) {
            return false;
        }
        
        int leftSum = sum(current.left);
        int rightSum = sum(current.right);
        
        //Check if the sum of the left and right subtree is half of the total sum
        //be careful that left and right child cannot be null, since it's impossible to partition the tree against the null node
        if(leftSum * 2 == totalSum && current.left != null) {
            return true;
        } else if (rightSum * 2 == totalSum && current.right != null) {
            return true;
        }
        
        return checkEqual(current.left, totalSum) || checkEqual(current.right, totalSum);
    }
    
    private int sum(TreeNode node) {
        if(node == null) {
            return 0;
        }
        
        return node.val + sum(node.left) + sum(node.right);
    }
    

}
