package puzzle.lintcode.common;

public class LinkedListUtils {

	public static ListNode buildList(int...digits) {
    	if (digits.length == 0) {
    		return null;
    	}
    	ListNode head = null;
    	ListNode current = null;
    	for (int i = 0; i < digits.length; i++) {
    		if (head == null) {
    			head = new ListNode(digits[i]);
    			current = head;
    		} else {
    			current.next = new ListNode(digits[i]);
    			current = current.next;
    		}
    	}
    	
    	return head;
    }
    
    public static void displayLinkedList(ListNode head) {
    	ListNode current = head;
    	StringBuilder sb = new StringBuilder();
    	while (current != null) {
    		if (current.next == null) {
    			sb.append(current.val);
    		} else {
    			sb.append(current.val + "->");
    		}
    		current = current.next;
    	}
    	
    	System.out.println(sb.toString() + "->null");
    }
}
