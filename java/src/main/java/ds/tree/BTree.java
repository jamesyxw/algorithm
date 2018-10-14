package ds.tree;

/**
 * ---------------------------------------------------------------------------------------------------------
 * B-Tree
 * 
 * Using Knuth's definition, a B-tree must satisfy the following conditions:
 * 
    Rule 1: The root can have as few as one element (or even no elements if it also has no children); 
            every other node has at least MINIMUM elements.
            
    Rule 2: The maximum number of elements in a node is twice the value of MINIMUM.
    
    Rule 3: The elements of each B-tree node are stored in a partially filled array, 
            sorted from the smallest element (at index 0) 
            to the largest element (at the final used position of the array).
            
    Rule 4: The number of subtrees below a nonleaf node 
            is always one more than the number of elements in the node. Subtree 0, subtree 1, ...
    
    Rule 5: For any nonleaf node:
            An element at index i is greater than all the elements in subtree number i of the node, and
            An element at index i is less than all the elements in subtree number i + 1 of the node.
    
    Rule 6: Every leaf in a B-tree has the same depth. 
            Thus it ensures that a B-tree avoids the problem of a unbalanced tree.
    ---------------------------------------------------------------------------------------------------------
 *
 */
public class BTree {
    private static final int MINIMUM = 4;
    private static final int MAXIMUM = 2 * MINIMUM;
    private int elementCount;
    private Comparable[] elements = new Comparable[MAXIMUM + 1];
    private int childrenCount;
    private BTree[] children = new BTree[MAXIMUM + 2]; //always 1 more than element counts
    
    public BTree() {
        
    }
    
    /**
     * This method use Loose addition rule such that:
     *  it allows the TreeNode to have at most MAXIMUM + 1 elements
     * @param element
     */
    public void looseAdd(Comparable element) {
        int i = this.findGE(element);
        
        if (i < 0) {
            
        }
        
        if (equal(this.elements[i], element)) {
            //such element already exists
            return;
        } else if (this.childrenCount == 0) {
            this.addElement(element, i);
            this.elementCount++;
        } else {
            this.children[i].looseAdd(element);
            if (this.elementCount == MAXIMUM + 1) {
                //there's an excess element now, the tree needs to be split
                this.split();
            }
        }
    }
    
    /**
     * Check if the current tree contains the input element
     * @param element
     * @return
     * @throws Exception 
     */
    public boolean contains(Comparable element) {       
        int i = this.findGE(element);
        
        if (equal(elements[i], element)) {
            return true;
        } else if (this.isLeaf()) {
            return false;
        } else {
            return this.children[i].contains(element);
        }
    }
    
    public boolean remove(Comparable element) {
        return false;
    }
    
    public boolean isLeaf() {
        return this.childrenCount == 0;
    }
    
    /**
     * Shift all the elements from index to right by 1 
     * insert the element at the index position
     * 
     * @param element
     * @param index
     */
    private void addElement (Comparable element, int index) {
        for (int i = index; i < elementCount; i++) {
            Comparable cur = this.elements[i];
            Comparable next = this.elements[i + 1];
            this.elements[i + 1] = cur;
            cur = next;
        }
        
        this.elements[index] = element;
    }
    
    /**
     * This method is called when the current TreeNode has MAXIMUM + 1 elements because of addition
     * It needs to be split into to, the mid element becomes the new root
     */
    private void split () {
        return;
    }
    
    private boolean less (Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }
    
    private boolean equal (Comparable a, Comparable b) {
        return a.equals(b);
    }
    
    /**
     * This method find the index i of the first element that's greater or equal to the target
     * elements[i] >= target
     * 
     * @param element
     * @return
     */
    private int findGE (Comparable target) {
        if (elementCount == 0) {
            return -1;
        }
        
        int left = 0;
        int right = elementCount - 1;
        
        while (left + 1 < right) {
            int mid = left + (right - left)/2;
            
            if (equal(this.elements[mid], target)) {
                return mid;
            } else if (less(this.elements[mid], target)) {
                left = mid;
            } else {
                right = mid;
            }
        }

        //all elements in the tree is smaller than the target
        if (less(this.elements[right], target)) {
            return elementCount;
        }
        
        //left is smaller than the target
        if (less(this.elements[left], target)) {
            return right;
        }
        
        return left;
    }   
}
