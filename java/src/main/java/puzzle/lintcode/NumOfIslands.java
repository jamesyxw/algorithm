package puzzle.lintcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a boolean 2D matrix, 0 is represented as the sea, 1 is represented as the island. 
 * If two 1 is adjacent, we consider them in the same island. We only consider up/down/left/right adjacent.

   Find the number of islands.
   
    Example
    Given graph:
    
    [
      [1, 1, 0, 0, 0],
      [0, 1, 0, 0, 1],
      [0, 0, 0, 1, 1],
      [0, 0, 0, 0, 0],
      [0, 0, 0, 0, 1]
    ]
    return 3.
   
 * @author jamesyxw
 *
 */
public class NumOfIslands {
    
    public class Location {
        int x;
        int y;
        public Location(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    public static void main(String[] args) {
        NumOfIslands sol = new NumOfIslands();
        
        boolean[][] grid = {
                {true, true, false, false, false},
                {false, true, false, false, true},
                {false, false, false, true, true},
                {false, false, false, false, false},
                {false, false, false, false, true}
        };
        
        System.out.println(sol.numIslands(grid));
    }
    /**
     * @param grid: a boolean 2D matrix
     * @return: an integer
     */
    public int numIslands(boolean[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        // write your code here
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                Queue<Location> queue = new LinkedList<Location>();
                if (grid[i][j] && !visited[i][j]) {
                    queue.offer(new Location(i, j));
                    while (!queue.isEmpty()) {
                        Location loc = queue.poll();
                        for(int k = 0; k < dir.length; k++) {
                            int x = loc.x + dir[k][0];
                            int y = loc.y + dir[k][1];
                            if(x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] && !visited[x][y]) {
                                queue.offer(new Location(x, y));
                                visited[x][y] = true;
                            }
                        }
                    }
                    count++;
                }
            }
        }
        
        return count;
    }
}
