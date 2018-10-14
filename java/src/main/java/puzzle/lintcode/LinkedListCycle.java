package puzzle.lintcode;

import java.util.HashSet;
import java.util.Set;

import puzzle.lintcode.common.ListNode;


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
        ListNode<String> a = new ListNode<String>("1");
        ListNode<String> b = new ListNode<String>("1");
        a.next = b;
        b.next = null;
        
        System.out.println(hasCycle(a));
        
    }
    
    public static boolean hasCycleHashSet(ListNode<String> head) {
        if (head == null || head.next == null) {
            return false;
        }
        
        Set<ListNode<String>> set = new HashSet<ListNode<String>>();
        ListNode<String> cur = head;
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
    public static boolean hasCycle(ListNode<String> head) {
        if (head == null || head.next == null) {
            return false;
        }
        
        ListNode<String> slow = head;
        ListNode<String> fast = head;
        
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
