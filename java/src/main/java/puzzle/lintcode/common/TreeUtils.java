package puzzle.lintcode.common;

import java.util.ArrayDeque;
import java.util.Deque;

public class TreeUtils {
    
    public static void main(String[] args) {
        TreeNode a1 = new TreeNode(1);
        TreeNode b2 = new TreeNode(2);
        TreeNode c3 = new TreeNode(3);
        TreeNode d4 = new TreeNode(4);
        TreeNode e5 = new TreeNode(5);
        TreeNode f6 = new TreeNode(6);
        
        /*
         *          1
         *         / \
         *        2   3
         *       / \   \
         *      4   5   6
         */
        
        a1.left = b2;
        a1.right = c3;
        b2.left = d4;
        b2.right = e5;
        c3.right = f6;
        
        TreeUtils sol = new TreeUtils();
        sol.preorder(a1);
        sol.inorder(a1);
        sol.postorder(a1);
        
        
    }

    public void preorder(Node node) {
        System.out.println("start of preorder");
        if (node == null) {
            return;
        }
        
        Deque<Node> stack = new ArrayDeque<Node>();
        stack.add(node);
        
        while(!stack.isEmpty()) {
            Node cur = stack.pop();
            System.out.print(cur.toString() + ",");
            
            if (cur.right() != null) {
                stack.push(cur.right());
            }
            
            if (cur.left() != null) {
                stack.push(cur.left());
            }
        }
        System.out.println("\nend of preorder");
    }
    
    public void inorder(Node root) {
        System.out.println("start of inorder");
        if(root == null) {
            return;
        }
        
        Deque<Node> stack = new ArrayDeque<Node>();
        Node cur = root;
        
        while (!stack.isEmpty() || cur != null) {
            if(cur != null) {
                stack.push(cur);
                cur = cur.left();
            } else {
                 cur = stack.pop();
                System.out.print(cur.toString() + ",");
                cur = cur.right();
            }
        }
        System.out.println("\nend of inorder");
    }
    
    public void postorder(Node root) {
        System.out.println("start of postorder");
        if(root == null) {
            return;
        }
        
        Deque<Node> stack = new ArrayDeque<Node>();
        stack.push(root);
        Node prev = null;
        
        while(!stack.isEmpty()) {
            Node cur = stack.peek();
            
            if(prev == null || prev.left() == cur || prev.right() == cur) {
                if (cur.left() != null) {
                    //go left if possible
                    stack.push(cur.left());
                } else if (cur.right() != null) {
                    //go right if left doesn't exist
                    stack.push(cur.right());
                } else {
                    //cur is leaf node
                    stack.pop();
                    System.out.print(cur.toString() + ",");
                }
            } else if (cur.left() == prev) {
                //go up from left
                if (cur.right() != null) {
                    stack.push(cur.right());
                } else {
                    //cur is leaf node
                    stack.pop();
                    System.out.print(cur.toString() + ",");
                }
                
            } else if (cur.right() == prev) {
                //go up from right, cur's child nodes are already visited
                stack.pop();
                System.out.print(cur.toString() + ",");
            }
            
            prev = cur;
        }
        
        System.out.println("\nend of postorder");
    }
}
