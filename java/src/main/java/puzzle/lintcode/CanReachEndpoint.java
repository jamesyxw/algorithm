package puzzle.lintcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a map size of m*n, 1 means space, 0 means obstacle, 9 means the
 * endpoint. You start at (0,0) and return whether you can reach the endpoint.
 * 
 * @author jamesyxw
 *
 */
public class CanReachEndpoint {
    public static void main(String[] args) {
        int[][] maps = { { 1, 1, 1 }, { 1, 1, 1 }, { 1, 1, 9 } };
        System.out.println(reachEndpoint(maps));
    }

    public static class Index {
        public Integer x;
        public Integer y;

        public Index(Integer x, Integer y) {
            this.x = x;
            this.y = y;
        }
    }

    /**
     * @param map:
     *            the map
     * @return: can you reach the endpoint
     */
    public static boolean reachEndpoint(int[][] map) {
        if (map.length == 0 || map[0].length == 0) {
            return false;
        }

        if (map[0][0] == 0) {
            return false;
        }

        int m = map.length; // vertical axis
        int n = map[0].length; // horizontal axis

        boolean[][] visited = new boolean[m][n];

        // BFS with a queue
        Queue<Index> queue = new LinkedList<Index>();
        int x = 0; // x is horizontal axis
        int y = 0; // y is vertical axis
        queue.add(new Index(x, y));

        while (!queue.isEmpty()) {
            Index index = queue.poll();
            if (map[index.x][index.y] == 9) {
                return true;
            }

            // not first column - check left
            if (index.x > 0) {
                if (map[index.x - 1][index.y] != 0 && !visited[index.x - 1][index.y]) {
                    queue.add(new Index(index.x - 1, index.y));
                    // mark the current index as visited
                    visited[index.x - 1][index.y] = true;
                }
            }

            // not first row - check up
            if (index.y > 0) {
                if (map[index.x][index.y - 1] != 0 && !visited[index.x][index.y - 1]) {
                    queue.add(new Index(index.x, index.y - 1));
                    // mark the current index as visited
                    visited[index.x][index.y - 1] = true;
                }
            }

            // not last column
            if (index.x < n - 1) {
                if (map[index.x + 1][index.y] != 0 && !visited[index.x + 1][index.y]) {
                    queue.add(new Index(index.x + 1, index.y));
                 // mark the current index as visited
                    visited[index.x + 1][index.y] = true;
                }
            }

            // not last row
            if (index.y < m - 1) {
                if (map[index.x][index.y + 1] != 0 && !visited[index.x][index.y + 1]) {
                    queue.add(new Index(index.x, index.y + 1));
                 // mark the current index as visited
                    visited[index.x][index.y + 1] = true;
                }
            }
        }

        return false;
    }
}
