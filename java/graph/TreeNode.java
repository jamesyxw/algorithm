public class TreeNode {
    TreeNode left;
    TreeNode right;
    Comparable value;

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

}