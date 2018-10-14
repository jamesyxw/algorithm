package puzzle.lintcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import puzzle.lintcode.common.ListUtils;
import puzzle.lintcode.common.TreeNode;

/**
 * Given a binary tree, return the values of its boundary in anti-clockwise direction starting from root. 
 * Boundary includes left boundary, leaves, and right boundary in order without duplicate nodes.

    Left boundary is defined as the path from root to the left-most node. 
    Right boundary is defined as the path from root to the right-most node. 
    If the root doesn't have left subtree or right subtree, then the root itself is left boundary or right boundary. 
    Note this definition only applies to the input binary tree, and not applies to any subtrees.
    
    The left-most node is defined as a leaf node you could reach when you always firstly travel to the left subtree if exists. 
    If not, travel to the right subtree. Repeat until you reach a leaf node.
    
    The right-most node is also defined by the same way with left and right exchanged.
    
    Have you met this question in a real interview?  
    Example
    Given
      1
       \
        2
       / \
      3   4
    
    return
    [1, 3, 4, 2]
    
    Explanation:
    The root doesn't have left subtree, so the root itself is left boundary.
    The leaves are node 3 and 4.
    The right boundary are node 1,2,4. Note the anti-clockwise direction means you should output reversed right boundary.
    So order them in anti-clockwise without duplicates and we have [1,3,4,2].
    Given
             1
       /          \
      2            3
     / \          / 
    4   5        6   
       / \      / \
      7   8    9  10  
           
    return
    [1,2,4,7,8,9,10,6,3]
    
    Explanation:
    The left boundary are node 1,2,4. (4 is the left-most node according to definition)
    The leaves are node 4,7,8,9,10.
    The right boundary are node 1,3,6,10. (10 is the right-most node).
    So order them in anti-clockwise without duplicate nodes we have [1,2,4,7,8,9,10,6,3].
    
 * @author jamesyxw
 *
 */
public class BoundaryOfBinaryTree {
    
    public static void main(String[] args) {
        BoundaryOfBinaryTree sol = new BoundaryOfBinaryTree();
        TreeNode a1 = new TreeNode(1);
        TreeNode a2 = new TreeNode(2);
        TreeNode a3 = new TreeNode(3);
        TreeNode a4 = new TreeNode(4);
        TreeNode a5 = new TreeNode(5);
        TreeNode a6 = new TreeNode(6);
        TreeNode a7 = new TreeNode(7);
        TreeNode a8 = new TreeNode(8);
        TreeNode a9 = new TreeNode(9);
        TreeNode a10 = new TreeNode(10);
        TreeNode a11 = new TreeNode(11);
        
        a1.left = a2;
        a1.right = a3;
        a2.left = a4;
        a2.right = a5;
        a5.left = a7;
        a5.right = a8;
        a3.left = a6;
        a6.left = a9;
        a6.right = a10;
        a8.left = a11;
        
        ListUtils<Integer> util = new ListUtils<Integer>();
        util.displayList(sol.boundaryOfBinaryTree(a1));
        util.displayList(sol.boundaryOfBinaryTreeRecur(a1));
    }
    
    public List<Integer> boundaryOfBinaryTreeRecur(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if(root == null) {
            return res;
        }
        
        res.add(root.val);
        
        left(root.left, res);
        bottom(root.left, res);
        bottom(root.right, res);
        right(root.right, res);
        
        return res;
        
    }
    
    private void right(TreeNode root, List<Integer> res) {
        if(root == null || (root.left == null && root.right == null)) {
            return;
        }
        
        if(root.right != null) {
            right(root.right, res);
        } else {
            right(root.left, res);
        }
        
        res.add(root.val);
    }

    private void bottom(TreeNode root, List<Integer> res) {
        if(root == null) {
            return;
        }
        
        if(root.left == null && root.right == null) {
            res.add(root.val);
        } 
        
        bottom(root.left, res);
        bottom(root.right, res);
        
    }

    private void left(TreeNode root, List<Integer> res) {
        if(root == null || (root.left == null && root.right == null)) {
            return;
        }
        
        res.add(root.val);
        //check if the left most, if 
        if(root.left != null) {
            left(root.left, res);
        } else {
            left(root.right,res);
        }
        
    }

    /**
     * @param root: a TreeNode
     * @return: a list of integer
     */
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        TreeNode cur = root;
        if (root.left != null) {
          //left boundary
            while(cur != null) {
                res.add(cur.val);
                if(cur.left != null) {
                    cur = cur.left;
                } else if (cur.right != null) {
                    cur = cur.right;
                } else {
                    break;
                }
            }
        } else {
            res.add(root.val);
        }
        TreeNode leftMost = cur;
        
        //leaf
        Deque<TreeNode> curQueue = new LinkedList<TreeNode>();
        curQueue.offer(root);
        while(!curQueue.isEmpty()) {
            TreeNode node = curQueue.poll();
            if (node.left == null && node.right == null && node != leftMost) {
                res.add(node.val);
            } 
            
            if (node.left != null) {
                curQueue.offer(node.left);
            }
            
            if(node.right != null) {
                curQueue.offer(node.right);
            }
        }
        
        //right boundary
        Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
        cur = root.right;
        while(cur != null) {
            stack.push(cur);
            if (cur.right != null) {
                cur = cur.right;
            } else if (cur.left != null) {
                cur = cur.left;
            } else {
                break;
            }
        }
        
        //skip the right most leaf node
        if (!stack.isEmpty()) {
            stack.pop();
        }
        while(!stack.isEmpty()) {
            res.add(stack.pop().val);
        }
        
        return res;
    }
    
    
}
