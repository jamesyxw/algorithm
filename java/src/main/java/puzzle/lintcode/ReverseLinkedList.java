package puzzle.lintcode;

import puzzle.lintcode.common.LinkedListUtils;
import puzzle.lintcode.common.ListNode;

/**
 * Reverse a linked list.
 * For linked list 1->2->3, the reversed linked list is 3->2->1
 * 
 * Reverse it in-place and in one-pass
 * 
 * @author jamesyxw
 *
 */
public class ReverseLinkedList {
	
	public static void main(String[] args) {
		ListNode l1 = LinkedListUtils.buildList(1,2,3,4);
		LinkedListUtils.displayLinkedList(l1);
		ListNode l1Reversed = reverse(l1);
		LinkedListUtils.displayLinkedList(l1Reversed);
		
	}

	/**
     * @param head: n
     * @return: The new head of reversed linked list.
     */
    public static ListNode reverse(ListNode head) {
    	if (head == null || head.next == null) {
    		return head;
    	}
    	
        ListNode current = head;
        ListNode prev = null;
        ListNode next = head.next;
        
        
        while (next != null) {
        	current.next = prev;
        	prev = current;
        	current = next;
        	next = next.next;
        }
        
        current.next = prev;
        
        return current;
    }
}
