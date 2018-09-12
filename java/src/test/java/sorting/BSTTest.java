package sorting;


import static org.junit.Assert.*;

import java.util.List;

import graph.BST;
import graph.TreeNode;

import org.junit.Test;

public class BSTTest {

	@Test
	public void empty() {
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
	}
	
	@Test
	public void singleNode() {
		BST bst = new BST();
		bst.insert(7);
		List<TreeNode> preOrder = bst.preOrderTraverse();
        Integer[] preOrderAnswer = new Integer[] { 7 };
        for (int i = 0; i < preOrder.size(); i++) {
        	assertEquals(preOrder.get(i).value, preOrderAnswer[i]);
        }
        List<TreeNode> inOrder = bst.inOrderTraverse();
        Integer[] inOrderAnswer = new Integer[] { 7 };
        for (int i = 0; i < inOrder.size(); i++) {
            assertEquals(inOrder.get(i).value, inOrderAnswer[i]);
        }
        List<TreeNode> postOrder = bst.postOrderTraverse();
        Integer[] postOrderAnswer = new Integer[] { 7 };
        for (int i = 0; i < postOrder.size(); i++) {
        	assertEquals(postOrder.get(i).value, postOrderAnswer[i]);
        }
	}
	
	@Test
	public void multipleNodes() {
		BST bst = new BST();
		bst.insert(7);
		bst.insert(2);
		bst.insert(5);
		bst.insert(9);
		bst.insert(3);
		bst.insert(8);
		bst.insert(6);
		bst.insert(1);
		
		List<TreeNode> preOrder = bst.preOrderTraverse();
        Integer[] preOrderAnswer = new Integer[] { 7,2,1,5,3,6,9,8 };
        assertEquals(preOrder.size(), 8);
        for (int i = 0; i < preOrder.size(); i++) {
//        	System.out.println("preOrder: " + preOrder.get(i).value + "vs. anwswer: " + preOrderAnswer[i]);
        	assert(preOrder.get(i).getValue().compareTo(preOrderAnswer[i]) == 0);
        }
        List<TreeNode> inOrder = bst.inOrderTraverse();
        Integer[] inOrderAnswer = new Integer[] { 1,2,3,5,6,7,8,9 };
        assertEquals(inOrder.size(), 8);
        for (int i = 0; i < inOrder.size(); i++) {
//        	System.out.println("inOrder: " + inOrder.get(i).value + "vs. anwswer: " + inOrderAnswer[i]);
        	assert(inOrder.get(i).getValue().compareTo(inOrderAnswer[i]) == 0);
        }
        List<TreeNode> postOrder = bst.postOrderTraverse();
        Integer[] postOrderAnswer = new Integer[] { 1,3,6,5,2,8,9,7 };
        assertEquals(postOrder.size(), 8);
        for (int i = 0; i < postOrder.size(); i++) {
//        	System.out.println("postOrder: " + postOrder.get(i).value + "vs. anwswer: " + postOrderAnswer[i]);
        	assert(postOrder.get(i).getValue().compareTo(postOrderAnswer[i]) == 0);
        }
	}

}
