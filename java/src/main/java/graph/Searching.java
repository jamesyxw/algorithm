package graph;

import java.util.*;

public class Searching {
    public static List<Node> BFS (Node root) {
        if (root == null) {
            return new ArrayList<Node>();
        }

        List<Node> result = new ArrayList<Node>();
        Queue<Node> queue = new LinkedList<Node>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            cur.isVisited = true;
            result.add(cur);
            for (Node neighbor : cur.neighbors) {
                if (!neighbor.isVisited) {
                    queue.offer(neighbor);
                }
            }
        }

        return result;
    }

    public static List<Node> DFS (Node root) {
        if (root == null) {
            return new ArrayList<Node>();
        }

        List<Node> result = new ArrayList<Node>();
        Stack<Node> stack = new Stack<Node>();
        stack.push(root);

        while(!stack.isEmpty()) {
            Node curNode = stack.pop();
            result.add(curNode);
            for (Node neighbor : curNode.neighbors) {
                stack.push(neighbor);
            }
        }

        return result;
    }

    public static List<Node> DFSRecur (Node root) {
        if (root == null) {
            return new ArrayList<Node>();
        }

        List<Node> result = new ArrayList<Node>();

        Node cur = root;
        root.isVisited = true;
        result.add(root);

        for (Node neighbor : cur.neighbors) {
            DFSUtil(result, neighbor);
        }

        return result;
    }

    private static void DFSUtil (List<Node> result, Node cur) {
        if (cur == null) {
            return;
        }

        for (Node neighbor : cur.neighbors) {
            neighbor.isVisited = true;
            result.add(neighbor);
            DFSUtil(result, neighbor);
        }
    }
}