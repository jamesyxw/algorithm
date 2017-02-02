package graph;

import java.util.Arrays;

public class SegmentTree {
    public static SegmentNode makeSegmentTree(Comparable[] input) {
        try {
            return makeSegmentTree(input, 0, input.length - 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static SegmentNode makeSegmentTree(Comparable[] input, int start, int end) throws Exception {
        if (start == end) {
            if (input.length != 1) {
                throw new Exception ("There's a bug");
            }
            return new SegmentNode(input[0], start, end);
        }

        int mid = input.length/2;

        SegmentNode left = makeSegmentTree(Arrays.copyOfRange(input, 0, mid),
                start, start + mid);
        SegmentNode right = makeSegmentTree(Arrays.copyOfRange(input, mid + 1, input.length),
                start + mid + 1, end);

        Comparable rootValue = left.value.compareTo(right.value) < 0 ? left.value : right.value;
        SegmentNode root = new SegmentNode(rootValue, start, end);
        root.left = left;
        root.right = right;

        return root;
    }

    public static Comparable queryMin(SegmentNode node, int start, int end) {
        //if the query range is the range of the node
        if (start == node.start && end == node.end) {
            return node.value;
        }
        
        //if the query range does not interset with the node range
        if (end < node.start && start > node.end) {
            return null;
        }
        
        SegmentNode left = node.left;
        SegmentNode right = node.right;
        Comparable resultLeft = null;
        Comparable resultRight = null;
        
        if (left.start <= start && start <= left.end) {
            resultLeft = queryMin(left, start, Math.min(end, left.end));
        } 

        if (right.start <= end && end <= right.end) {
            resultRight = queryMin(right, end, Math.max(right.start, start));
        } 
        
        return resultLeft.compareTo(resultRight) < 0 ? resultLeft : resultRight;
    }
}
