package puzzle.lintcode.microsoft;

import puzzle.lintcode.common.LinkedListUtils;
import puzzle.lintcode.common.ListNode;

/**
 * You have two numbers represented by a linked list, where each node contains a single digit. 
 * The digits are stored in reverse order, such that the 1's digit is at the head of the list. 
 * Write a function that adds the two numbers and returns the sum as a linked list.
 * 
 * Example
	Given 7->1->6 + 5->9->2. That is, 617 + 295.
	
	Return 2->1->9. That is 912.
	
	Given 3->1->5 and 5->9->2, return 8->0->8.
 * 
 * @author jamesyxw
 *
 */
public class AddTwoNum {
	
	public static void main(String[] args) {
//		ListNode l1 = LinkedListUtils.buildList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
//		ListNode l2 = LinkedListUtils.buildList(0, 9, 8, 7, 6, 5, 4, 3, 2, 1);
//		LinkedListUtils.displayLinkedList(l1);
//		LinkedListUtils.displayLinkedList(l2);
//		
//		ListNode sum = addLists(l1, l2);
//		LinkedListUtils.displayLinkedList(sum);
////		
//		System.out.println();
//		
//		ListNode l3 = LinkedListUtils.buildList(9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9);
//		ListNode l4 = LinkedListUtils.buildList(9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9);
//		LinkedListUtils.displayLinkedList(l3);
//		LinkedListUtils.displayLinkedList(l4);
//		
//		ListNode sum2 = addLists(l3, l4);
//		System.out.println();
//		LinkedListUtils.displayLinkedList(sum2);
		
		System.out.println();
		
		ListNode l5 = LinkedListUtils.buildList(0);
		ListNode l6 = LinkedListUtils.buildList(9,9);
		LinkedListUtils.displayLinkedList(l5);
		LinkedListUtils.displayLinkedList(l6);
		
		ListNode sum3 = addLists(l5, l6);
		System.out.println();
		LinkedListUtils.displayLinkedList(sum3);
	}
	

    /**
     * @param l1: the first list
     * @param l2: the second list
     * @return: the sum list of l1 and l2 
     */
    public static ListNode addLists(ListNode l1, ListNode l2) {
        ListNode result = null;
        
        if (l1 == null && l2 == null) {
        	return null;
        }
        
        if (l1 == null) {
        	return l2;
        }
        
        if (l2 == null) {
        	return l1;
        }
        
        ListNode cur1 = l1;
        ListNode cur2 = l2;
        
        ListNode cur = result;
        boolean isCarried = false;
        while (cur1 != null && cur2 != null) {
        	int currentSum = isCarried ? cur1.val + cur2.val + 1 : cur1.val + cur2.val;
        	if (currentSum > 9) {
        		isCarried = true;
        	} else {
        		isCarried = false;
        	}
        	int currentDigit = currentSum % 10;
        	
        	if (result == null) {
        		result = new ListNode(currentDigit);
        		cur = result;
        	} else {
        		cur.next = new ListNode(currentDigit);
        		cur = cur.next;
        	}
        	
        	cur1 = cur1.next;
        	cur2 = cur2.next;
        }
        
        if (cur1 != null) {
        	while (cur1 != null) {
        		int currentSum = isCarried ? cur1.val + 1 : cur1.val;
        		if (currentSum > 9) {
            		isCarried = true;
            	} else {
            		isCarried = false;
            	}
            	int currentDigit = currentSum % 10;
            	
            	if (result == null) {
            		result = new ListNode(currentDigit);
            		cur = result;
            	} else {
            		cur.next = new ListNode(currentDigit);
            		cur = cur.next;
            	}
            	
            	cur1 = cur1.next;
        	}
        } else if (cur2 != null) {
        	while (cur2 != null) {
        		int currentSum = isCarried ? cur2.val + 1 : cur2.val;
        		if (currentSum > 9) {
            		isCarried = true;
            	} else {
            		isCarried = false;
            	}
            	int currentDigit = currentSum % 10;
            	
            	if (result == null) {
            		result = new ListNode(currentDigit);
            		cur = result;
            	} else {
            		cur.next = new ListNode(currentDigit);
            		cur = cur.next;
            	}
            	
            	cur2 = cur2.next;
        	}
        }
        
        if (isCarried) {
        	cur.next = new ListNode(1);
        }
        
        return result;
    }
    
    
}
