package puzzle.lintcode.microsoft;

import java.util.HashSet;
import java.util.Set;

import puzzle.lintcode.common2.ListNode;

/**
 * Given a linked list, determine if it has a cycle in it.
 * 
 * Example
    Given -21->10->4->5, tail connects to node index 1, return true
    
    Challenge
    Follow up:
    Can you solve it without using extra space?
 * 
 * @author jamesyxw
 *
 */
public class LinkedListCycle {
    
    public static void main(String[] args) {
        ListNode a = new ListNode("1");
        ListNode b = new ListNode("1");
        a.next = b;
        b.next = null;
        
        System.out.println(hasCycle(a));
        
    }
    
    public static boolean hasCycleHashSet(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        
        Set<ListNode> set = new HashSet<ListNode>();
        ListNode cur = head;
        while (cur != null) {
            if (set.contains(cur)) {
                return true;
            }
            
            set.add(cur);
            cur = cur.next;
        }
        
        return false;
    }

    /**
     * @param head: The first node of linked list.
     * @return: True if it has a cycle, or false
     */
    public static boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        
        ListNode slow = head;
        ListNode fast = head;
        
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            
            if (slow == fast) {
                return true;
            }
        }
        
        return false;
    }
}
