package ds.graph;

import java.util.*;

public class Test {
    public static void main(String[] args) {
        BSTTest();
    }

    public static void BSTTest() {
        BST bst = new BST();
        List<TreeNode> preOrder = bst.preOrderTraverse();
        for (TreeNode node : preOrder) {
            assert(false);
        }
        List<TreeNode> inOrder = bst.inOrderTraverse();
        for (TreeNode node : inOrder) {
            assert(false);
        }
        List<TreeNode> postOrder = bst.postOrderTraverse();
        for (TreeNode node : postOrder) {
            assert(false);
        }

        bst.insert(7);
        preOrder = bst.preOrderTraverse();
        Integer[] preOrderAnswer = new Integer[] { 7 };
        for (int i = 0; i < preOrder.size(); i++) {
            assert(preOrder.get(i).value.equals(preOrderAnswer[i]));
        }
        inOrder = bst.inOrderTraverse();
        Integer[] inOrderAnswer = new Integer[] { 7 };
        for (int i = 0; i < inOrder.size(); i++) {
            assert(inOrder.get(i).value.equals(inOrderAnswer[i]));
        }
        postOrder = bst.postOrderTraverse();
        Integer[] postOrderAnswer = new Integer[] { 7 };
        for (int i = 0; i < postOrder.size(); i++) {
            assert(postOrder.get(i).value.equals(postOrderAnswer[i]));
        }

    }
}