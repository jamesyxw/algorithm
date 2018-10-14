package puzzle.lintcode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import puzzle.lintcode.common.TreeNode;

/**
 * Design an iterator over a binary search tree with the following rules:

    Elements are visited in ascending order (i.e. an in-order traversal)
    next() and hasNext() queries run in O(1) time in average.
    
    For the following binary search tree, in-order traversal by using iterator is [1, 6, 10, 11, 12]

       10
     /    \
    1      11
     \       \
      6       12
    Challenge
    Extra memory usage O(h), h is the height of the tree.
    
    Super Star: Extra memory usage O(1)
    
 * @author jamesyxw
 *
 */
public class BSTIterator {

    Stack<TreeNode> stack = new Stack<TreeNode>();
    Queue<TreeNode> queue = new LinkedList<TreeNode>();
    
    public static void main(String[] args) {
        TreeNode a = new TreeNode(10);
        TreeNode b = new TreeNode(1);
        TreeNode c = new TreeNode(11);
        TreeNode d = new TreeNode(6);
        TreeNode e = new TreeNode(12);
        
        a.left = b;
        a.right = c;
        b.right = d;
        c.right = e;
        
        BSTIterator iterator = new BSTIterator(a);
        while(iterator.hasNext()) {
            System.out.print(iterator.next().val + ",");
        }
    }
    
    /*
     * @param root: The root of binary tree.
     */
    public BSTIterator(TreeNode root) {
        //In order traversal: left -> root -> right
        if (root == null) {
            return;
        }
        
        TreeNode current = root;
        
        while(!stack.isEmpty() || current != null) {
            
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            
            //current is null;
            current = stack.pop();
            
            queue.add(current);
            
            current = current.right;
        }
    }

    /*
     * @return: True if there has next node, or false
     */
    public boolean hasNext() {
        // write your code here
        return !queue.isEmpty();
    }

    /*
     * @return: return next node
     */
    public TreeNode next() {
        // write your code here
        return queue.poll();
    }
}
