package puzzle.lintcode.common;

public class TreeNode implements Node {
	public int val;
	public TreeNode left, right;
	public TreeNode(int val) {
		this.val = val;
		this.left = this.right = null;
	}
	
	@Override
	public String toString() {
        return this.val + "";
	}
	
	@Override
	public Node left() {
	    return this.left;
	}
	
	@Override
	public Node right() {
	    return this.right;
	}
}