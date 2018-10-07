package puzzle.lintcode.amazon;

import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import puzzle.lintcode.common.TreeNode;

/**
 * Given a binary tree where every node has a unique value, and a target key k, 
 * find the value of the nearest leaf node to target k in the tree.If there are multiple cases, return to the leftmost leaf node.

    Here, nearest to a leaf means the least number of edges travelled on the binary tree to reach any leaf of the tree. 
    Also, a node is called a leaf if it has no children.
    
    1.root represents a binary tree with at least 1 node and at most 1000 nodes.
    2.Every node has a unique node.val in range [1, 1000].
    3.There exists some node in the given binary tree for which node.val == k.
    
    Have you met this question in a real interview?  
    Example
    Example 1:
    
    Given:
    root = {1, 3, 2}, k = 1
    Diagram of binary tree:
              1
             / \
            3   2
    
    Return: 3
    
    Explanation: Either 2 or 3 is the nearest leaf node to the target of 1.3 is the leftmost leaf node.
    Example 2:
    
    Given:
    root = {1}, k = 1
    Return: 1
    
    Explanation: The nearest leaf node is the root node itself.
    Example 3:
    
    Given:
    root = {1,2,3,4,#,#,#,5,#,6}, k = 2
    Diagram of binary tree:
                 1
                / \
               2   3
              /
             4
            /
           5
          /
         6
    
    Return: 3
    Explanation: The leaf node with value 3 (and not the leaf node with value 6) is nearest to the node with value 2.
 * @author jamesyxw
 *
 */
public class ClosestLeafInBinaryTree {
    
    class Node {
        TreeNode node;
        int distance;
        
        public Node(TreeNode node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }
    
    public static void main(String[] args) {
        ClosestLeafInBinaryTree sol = new ClosestLeafInBinaryTree();
        
        TreeNode a1 = new TreeNode(1);
        TreeNode a2 = new TreeNode(2);
        TreeNode a3 = new TreeNode(3);
        
        a1.left = a2;
        a1.right = a3;
        
        System.out.println(sol.findClosestLeaf(a1, 1));
        System.out.println(sol.findClosestLeaf(a1, 2));
        System.out.println(sol.findClosestLeaf(a1, 3));
        
        System.out.println(sol.findClosestLeafLCA(a1, 1));
        System.out.println(sol.findClosestLeafLCA(a1, 2));
        System.out.println(sol.findClosestLeafLCA(a1, 3));
        
        TreeNode a4 = new TreeNode(4);
        TreeNode a5 = new TreeNode(5);
        TreeNode a6 = new TreeNode(6);
        
        a2.left = a4;
        a4.left = a5;
        a5.left = a6;
        
        System.out.println(sol.findClosestLeaf(a1, 2));
        System.out.println(sol.findClosestLeafLCA(a1, 2));
    }
    
    /**
     * This method use the least common ancestor to compute the distance from the leaf node to the target node
     * @param root
     * @param k
     * @return
     */
    public int findClosestLeafLCA(TreeNode root, int k) {
        Map<Integer, Integer> levels = new HashMap<Integer, Integer>();
        
        find(root, 0, levels, k);
        int targetLevel = levels.get(k);
        int[] result = new int[2];
        result[0] = Integer.MAX_VALUE;
        computeDistance(root, k, levels, 0, targetLevel, 0, result);
        
        return result[1];
    }
    
    private void computeDistance(TreeNode root, int k, Map<Integer, Integer> levels, int lca, int targetLevel, int curLevel, int[] result) {
        Integer temp = levels.get(root.val);
        if(temp != null) {
            //this means the root is on the path to target;
            lca = temp;
        }
        
        if(root.left == null && root.right == null) {
            int distance = targetLevel - lca + curLevel - lca;
            if(result[0] > distance ) {
                result[0] = distance;
                result[1] = root.val;
                return;
            }
        }
        
        if(root.left != null) {
            computeDistance(root.left, k, levels, lca, targetLevel, curLevel + 1, result);
        }
        
        if(root.right != null) {
            computeDistance(root.right, k, levels, lca, targetLevel, curLevel + 1, result);
        }
        
    }

    private boolean find(TreeNode root, int level, Map<Integer, Integer> levels, int k) {
        if (root == null) {
            return false;
        }
        
        //if the current node is k or on the path from root to target, record the levels
        if(root.val == k || find(root.left, level + 1, levels, k)  || find(root.right, level + 1, levels, k)) {
            levels.put(root.val, level);
            return true;
        }
        
        return false;
        
    }

    /**
     * Method 1: 
     * Note: this method doesn't guarantee that leftmost node is returned;
     * 
     * Create a hash map to store the mapping from child node to parent node while finding the node with k
     * Once k is found, then we can do BFS starting from k to find the first leaf node
     * 
     * @param root: the root
     * @param k: an integer
     * @return: the value of the nearest leaf node to target k in the tree
     */
    public int findClosestLeaf(TreeNode root, int k) {
        // Write your code here
        Map<TreeNode, TreeNode> back = new HashMap<TreeNode, TreeNode>();
        
        TreeNode target = findTarget(root, k, back);
        
        Deque<TreeNode> queue = new LinkedList<TreeNode>();
        Set<TreeNode> visited = new HashSet<TreeNode>(); //keep tracks of visited nodes
        queue.offer(target);
        visited.add(target);
        
        while(!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur.left == null && cur.right == null) {
                //closet leaf node is found
                return cur.val;
            }
            
            if(back.containsKey(cur) && !visited.contains(back.get(cur))) {
                //add the parent of the current node for backward searching
                queue.offer(back.get(cur));
                visited.add(back.get(cur));
            }
            
            if (cur.left != null && !visited.contains(cur.left)) {
                queue.offer(cur.left);
                visited.add(cur.left);
            }
            
            if (cur.right != null && !visited.contains(cur.right)) {
                queue.offer(cur.right);
                visited.add(cur.right);
            }
            
        }
        
        return -1;
    }

    private TreeNode findTarget(TreeNode root, int k, Map<TreeNode, TreeNode> back) {
        if(root == null) {
            return null;
        }
        
        if(root.val == k) {
            return root;
        }
        
        if(root.left != null) {
            back.put(root.left, root);
            TreeNode left = findTarget(root.left, k, back);
            if (left != null) {
                return left;
            }
        }
        
        if (root.right != null) {
            back.put(root.right, root);
            TreeNode right = findTarget(root.right, k, back);
            if (right != null) {
                return right;
            }
        }
        
        return null;
        
    }

}
