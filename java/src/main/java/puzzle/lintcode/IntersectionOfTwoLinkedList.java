package puzzle.lintcode;

import puzzle.lintcode.common.ListNode;

/**
 * Write a program to find the node at which the intersection of two singly linked lists begins.
 * 
 * Example
    The following two linked lists:
    
    A:          a1 → a2
                        \
                         c1 → c2 → c3
                        /
    B:     b1 → b2 → b3
    begin to intersect at node c1.
    
    Challenge
    Your code should preferably run in O(n) time and use only O(1) memory.
    
 * @author jamesyxw
 *
 */
public class IntersectionOfTwoLinkedList {
    
    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        ListNode d = new ListNode(4);
        ListNode e = new ListNode(5);
        
        a.next = b;
        b.next = c;
        d.next = c;
        c.next = e;
        
        ListNode intersect = getIntersectionNode(a, d);
        System.out.println(intersect.val);
    }

    /**
     * @param headA: the first list
     * @param headB: the second list
     * @return: a ListNode
     */
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        
        int lengthA = length(headA);
        int lengthB = length(headB);
        
        int diff = Math.abs(lengthA - lengthB);
        ListNode currentA = headA;
        ListNode currentB = headB;
        
        if (lengthA > lengthB) {
            for(int i = 0; i < diff; i++) {
                currentA = currentA.next;
            }
        } else {
            for(int i = 0; i < diff; i++) {
                currentB = currentB.next;
            }
        }
        
        while (currentA != currentB) {
            currentA = currentA.next;
            currentB = currentB.next;
        }
        
        if (currentA == currentB) {
            return currentA;
        } 
        
        return null;
    }
    
    private static int length (ListNode head) {
        int length = 0;
        ListNode current = head;
        while(current != null) {
            length++;
            current = current.next;
        }
        return length;
    }
}
