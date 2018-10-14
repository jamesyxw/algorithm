package ds.graph;

public class SegmentNode extends TreeNode {
    int start;
    int end;
    Comparable value;
    SegmentNode left;
    SegmentNode right;

    public SegmentNode (Comparable value, int start, int end) {
        super(value);
        this.start = start;
        this.end = end;
    }
}
