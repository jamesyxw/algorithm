package puzzle.lintcode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

import puzzle.lintcode.common.TreeNode;

/**
 * Given a binary tree, write a function to get the maximum width of the given tree. 
 * The width of a tree is the maximum width among all levels. 
 * The binary tree has the same structure as a full binary tree, but some nodes are null.

    The width of one level is defined as the length between the end-nodes 
    (the leftmost and right most non-null nodes in the level, 
    where the null nodes between the end-nodes are also counted into the length calculation.
    
    Description
Given a binary tree, write a function to get the maximum width of the given tree. The width of a tree is the maximum width among all levels. The binary tree has the same structure as a full binary tree, but some nodes are null.

The width of one level is defined as the length between the end-nodes (the leftmost and right most non-null nodes in the level, where the null nodes between the end-nodes are also counted into the length calculation.

Answer will in the range of 32-bit signed integer.

Have you met this question in a real interview?  
Example
Example 1:

Input: 

           1
         /   \
        3     2
       / \     \  
      5   3     9 

Output: 4
Explanation: The maximum width existing in the third level with the length 4 (5,3,#,9).
Example 2:

Input: 

          1
         /  
        3    
       / \       
      5   3     

Output: 2
Explanation: The maximum width existing in the third level with the length 2 (5,3).
Example 3:

Input: 

          1
         / \
        3   2 
       /        
      5      

Output: 2
Explanation: The maximum width existing in the second level with the length 2 (3,2).
Example 4:

Input: 

          1
         / \
        3   2
       /     \  
      5       9 
     /         \
    6           7
Output: 8
Explanation:The maximum width existing in the fourth level with the length 8 (6,#,#,#,#,#,#,7).

 * @author jamesyxw
 *
 */
public class MaxWidthBinaryTree {
    
    public class Pos {
        public int index;
        public TreeNode node;
        public Pos(int index, TreeNode node) {
            this.index = index;
            this.node = node;
        }
    }
    
    public static void main(String[] args) {
        MaxWidthBinaryTree sol = new MaxWidthBinaryTree();
    }
    /**
     * @param root: the root
     * @return: the maximum width of the given tree
     */
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        Deque<Pos> q = new LinkedList<Pos>();
        q.offer(new Pos(0, root));
        
        int max = Integer.MIN_VALUE;
        while (!q.isEmpty()) {
            int size = q.size();
            int width = q.peekLast().index - q.peekFirst().index + 1;
            max = Math.max(max, width);
            
            for(int i = 0; i < size; i++) {
                Pos pos = q.poll();
                if(pos.node.left != null) {
                    q.offer(new Pos(pos.index * 2 + 1, pos.node.left));
                } 
                
                if(pos.node.right != null) {
                    q.offer(new Pos(pos.index * 2 + 2, pos.node.right));
                }
            }
        }
        
        return max;
    }
}
