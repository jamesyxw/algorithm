package puzzle.lintcode;

import puzzle.lintcode.common.ListNode;

/**
 * Implement an algorithm to delete a node in the middle of a singly linked list, given only access to that node.

	Have you met this question in a real interview?  
	Example
	Linked list is 1->2->3->4, and given node 3, delete the node in place 1->2->4

 * @author jamesyxw
 *
 */
public class DeleteNodeInLinkedList {

	/*
     * @param node: the node in the list should be deletedt
     * @return: nothing
     */
    public void deleteNode(ListNode node) {
        if (node == null) {
        	return;
        }
        
        
        ListNode current = node;
        ListNode prev = null;
        while (current.next != null) {
        	current.val = current.next.val;
        	prev = current;
        	current = current.next;
        }
        
        prev.next = null;
        current = null;
        
    }
}
