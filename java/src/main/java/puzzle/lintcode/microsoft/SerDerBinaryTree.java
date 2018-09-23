package puzzle.lintcode.microsoft;

import java.util.LinkedList;
import java.util.Queue;

import puzzle.lintcode.common.TreeNode;

/**
 * Design an algorithm and write code to serialize and deserialize a binary tree. 
 * Writing the tree to a file is called 'serialization' 
 * and reading back from the file to reconstruct the exact same binary tree is 'deserialization'.
 * 
 * Example
    An example of testdata: Binary tree {3,9,20,#,#,15,7}, denote the following structure:
    
      3
     / \
    9  20
      /  \
     15   7
    Our data serialization use bfs traversal. This is just for when you got wrong answer and want to debug the input.
    
    You can use other method to do serializaiton and deserialization.
 * 
 * @author jamesyxw
 *
 */
public class SerDerBinaryTree {
    
    public static void main(String[] args) {
        String[] inputs = {"1,2,3", "1,2,3,4", "1,2,#,3,#,4"};
        
        for (int i = 0; i < inputs.length; i++) {
            
            TreeNode root = deserialize(inputs[i]);
            System.out.println("expected: " + inputs[i] + ", after serialization and deserialization: " + serialize(root));
        }
    }
    
    /**
     * This method will be invoked first, you should design your own algorithm 
     * to serialize a binary tree which denote by a root node to a string which
     * can be easily deserialized by your own "deserialize" method later.
     */
    public static String serialize(TreeNode root) {
        //the result will have the format of 1,2,3,#,#,5: delimited by comma ',' and 
        //if split by ',' into a char array, for ith node, left child is at 2i and right child is at 2i + 1
        if (root == null) {
            return "";
        }
        
        //Pre-order traversal
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        StringBuilder sb = new StringBuilder();
        sb.append(root.val + ",");
        
        int size = queue.size();
        while (!queue.isEmpty()) {
            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();
                
                if (current.left != null) {
                    queue.offer(current.left);
                    sb.append(current.left.val);
                } else {
                    sb.append("#");
                }
                
                sb.append(",");
                
                if (current.right != null) {
                    queue.offer(current.right);
                    sb.append(current.right.val);
                } else {
                    sb.append("#");
                }
                
                sb.append(",");
            }
        }
        
        String result = sb.toString();
        
        return result.substring(0, result.length() - 1);
    }

    /**
     * This method will be invoked second, the argument data is what exactly
     * you serialized at method "serialize", that means the data is not given by
     * system, it's given by your own serialize method. So the format of data is
     * designed by yourself, and deserialize it here as you serialize it in 
     * "serialize" method.
     */
    public static TreeNode deserialize(String data) {
        // write your code here
        TreeNode root = null;
        if (data == null || data.isEmpty()) {
            return root;
        }
        
        String[] values = data.split(",");
        if (values[0].equals("#")) {
            return null;
        }
        
        root = new TreeNode(Integer.parseInt(values[0]));
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        int i = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int j = 0; j < size ; j++) {
                TreeNode current = queue.poll();
                if (i < values.length && !values[i].equals("#")) {
                    current.left = new TreeNode(Integer.parseInt(values[i]));
                    queue.add(current.left);
                }
                i++;
                
                if (i < values.length && !values[i].equals("#")) {
                    current.right = new TreeNode(Integer.parseInt(values[i]));
                    queue.add(current.right);
                }
                i++;
            }
        }
        
        
        return root;
    }
}
