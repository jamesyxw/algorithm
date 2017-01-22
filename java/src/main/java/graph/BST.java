package graph;

import java.util.*;

public class BST {
    TreeNode root;

    public BST () {
        this.root = null;
    }

    public void insert (Comparable value) {
        if (value == null) {
            return;
        }
        
        TreeNode newNode = new TreeNode(value);
        
        if (root == null) {
            root = newNode;
            return;
        }

        TreeNode currentNode = root;

        //new node is smaller than the currentNode, go left;
        //else, go right
        while (currentNode != null) {
            if (isLess(newNode, currentNode)) {
                //new node's value is smaller than the current node's value
                if (currentNode.left == null) {
                    //put the new node at the left of the current node
                    currentNode.left = newNode;
                    return;
                } else {
                    //move the current node pointer to its left child
                    currentNode = currentNode.left;
                }
            } else {
                //new node's value is smaller than the current node's value
                if (currentNode.right == null) {
                    //put the new node at the right of the current node
                    currentNode.right = newNode;
                    return;
                } else {
                    //move the current node pointer to its left child
                    currentNode = currentNode.right;
                }
            }
        }
    }

    public TreeNode search (Comparable value) {
        if (value == null || root == null) {
            return null;
        }

        TreeNode currentNode = root;

        while (currentNode != null) {
            if (value.compareTo(currentNode.value) < 0) {
                currentNode = currentNode.left;
            } else {
                currentNode = currentNode.right;
            }
        }

        if (currentNode == null) {
            return null;
        }

        return currentNode;
    }

    /**
     * root -> left -> right
     **/
    public List<TreeNode> preOrderTraverse () {
        if (root == null) {
            return new ArrayList<TreeNode>();
        }
        List<TreeNode> result = new ArrayList<TreeNode>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);

        while (!stack.empty()) {
            TreeNode cur = stack.pop();
            result.add(cur);

            if (cur.right != null) {
                stack.push(cur.right);
            }

            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
        return result;
    }

    /**
     * left -> root -> right
     * 
    **/
    public List<TreeNode> inOrderTraverse () {
        if (root == null) {
            return new ArrayList<TreeNode>();
        }

        TreeNode curNode = root;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        List<TreeNode> result = new ArrayList<TreeNode>();

        while (!stack.empty() || curNode != null) {
            //keep pushing node on the left to the stack until hit leaf node
            //This guarantee the left ones after always before the right ones
            if (curNode != null) {
                stack.push(curNode);
                curNode = curNode.left;
            } else {
            	//Finally curNode hit the leaf node
                //So pop the last node in the stack and add to the List
                //Go the right node of the last popped node
                curNode = stack.pop();
                result.add(curNode);
                curNode = curNode.right;
            }
        }
        return result;
    }

    /**
     * left -> right -> root
     * 
    **/
    public List<TreeNode> postOrderTraverse () {
        if (root == null) {
            return new ArrayList<TreeNode>();
        }

        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode curNode = root;
        TreeNode lastPoppedNode = null;
        List<TreeNode> result = new ArrayList<TreeNode>();

        while (!stack.empty() || curNode != null) {
            //get the left nodes into the stack
            if (curNode != null) {
                stack.push(curNode);
                curNode = curNode.left;
            } else {
                //Node curNode hit the leaf, now go back the previous node and go to its right node
                TreeNode peekedNode = stack.peek();
                
                //Make sure don't go back to the one that has been popped
                if (peekedNode.right != null && peekedNode.right != lastPoppedNode) {
                    curNode = peekedNode.right;
                } else {
                    lastPoppedNode = stack.pop();
                    result.add(lastPoppedNode);
                }
            }
        }

        return result;
    }

    private boolean isLess(TreeNode a, TreeNode b) {
        return a.value.compareTo(b.value) < 0;
    }
}