package graph;

public class TreeNode {
	public TreeNode left;
    public TreeNode right;
    public Comparable value;

    public TreeNode (Comparable value){
        this.value = value;
    }

    public boolean hasLeft() {
        return left != null;
    }

    public boolean hasRight() {
        return right != null;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }
    
    public Comparable getValue() {
    	return this.value;
    }

}