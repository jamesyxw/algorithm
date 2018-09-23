package puzzle.lintcode.microsoft;

import java.util.HashMap;
import java.util.Map;

/**
 * Design and implement a data structure for Least Recently Used (LRU) cache. 
 * It should support the following operations: get and set.

    get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
    set(key, value) - Set or insert the value if the key is not already present. 
                      When the cache reached its capacity, 
                      it should invalidate the least recently used item before inserting a new item.
 * @author jamesyxw
 *
 */
public class LRUCache {
    
    public static void main(String[] args) {
        LRUCache cache = new LRUCache(5);
        cache.set(1, 1);
        cache.set(2, 2);
        cache.set(3, 3);
        cache.set(4, 4);
        cache.set(5, 5);
        cache.set(6, 6);
        
        cache.showInfo();
        
        cache.set(7, 7);
        
        cache.showInfo();
        
        cache.get(5);
        
        cache.showInfo();
        
        cache.get(5);
        
        cache.showInfo();

        cache.get(8);
        
        cache.showInfo();
        
        cache.get(6);
        
        cache.showInfo();
        
        cache.set(1, 1);
        
        cache.showInfo();
    }
    
    public class Node {
        public int val;
        public int key;
        public Node prev;
        public Node next;
        public Node(int val, int key) {
            this.val = val;
            this.key = key;
            this.prev = null;
            this.next = null;
        }
        @Override
        public String toString() {
            return String.format("{%s:%s}", key, val);
        }
    }
    
    private Node head = null;
    private Node tail = null;
    private Map<Integer, Node> map = new HashMap<Integer, Node>();
    private final int capacity;
    private int count = 0;
    
    /*
     * @param capacity: An integer
     */public LRUCache(int capacity) {
         this.capacity = capacity;
     }

     /*
      * @param key: An integer
      * @return: An integer
      */
     public int get(int key) {
         if (!map.containsKey(key)) {
             return -1;
         }
         
         Node result = map.get(key);
         //move the node to the front of the queue
         removeNodeFromList(result);
         preppendNodeToList(result);
         return result.val;
     }
     
     /*
      * @param key: An integer
      * @param value: An integer
      * @return: nothing
      */
     public void set(int key, int value) {
         if (count == capacity) {
             map.remove(tail.key);
             removeNodeFromList(tail);
         } 
         
         Node node = new Node(value, key);
         preppendNodeToList(node);
         map.put(key, node);
         
         if (count < capacity) {
             count++;
         }
     }

     private void preppendNodeToList(Node result) {
        if (head == null && tail == null) {
            head = result;
            tail = result;
        } else {
            result.next = head;
            result.prev = null;
            head.prev = result;
            head = result;
        }
    }

    private void removeNodeFromList(Node result) {
        if (result == null) {
            return;
        }
        
        if (result == head) {
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                head = head.next;
                head.prev = null;
            }
        } else if (result == tail) {
            tail = tail.prev;
            tail.next = null;
        } else {
            result.prev.next = result.next;
            result.next.prev = result.prev;
        }
    }

     private void showList() {
         Node current = head;
         System.out.print("Current list: ");
         while (current != null) {
             System.out.print(current.toString() + ",");
             current = current.next;
         }
         System.out.println();
     }
     
     private void showMap() {
         System.out.print("Current Map: ");
         for (Map.Entry<Integer, Node> entry : map.entrySet()) {
             System.out.print(String.format("%s:%s ", entry.getKey(), entry.getValue().toString()));
         }
     }
     
     public void showInfo() {
         System.out.println("capacity: " + capacity);
         this.showList();
         this.showMap();
         System.out.println("\n");
     }
}
