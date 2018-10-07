package puzzle.lintcode.amazon;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) 
 * You may assume all four edges of the grid are surrounded by water.

    Count the number of distinct islands. An island is considered to be the same as another if and only if 
    one island can be translated (and not rotated or reflected) to equal the other.
    
    Notice that:
    
    11
    1
    and
    
     1
    11
    are considered different island shapes, because we do not consider reflection / rotation.
    
    The length of each dimension in the given grid does not exceed 50.
    
    Have you met this question in a real interview?  
    
    Example
    Given grid = 
    [
    [1,1,0,0,0],
    [1,1,0,0,0],
    [0,0,0,1,1],
    [0,0,0,1,1]
    ]
    return 1
    Given grid = 
    [
    [1,1,0,1,1],
    [1,0,0,0,0],
    [0,0,0,0,1],
    [1,1,0,1,1]
    ]
    return 3
 * @author jamesyxw
 *
 */
public class NumOfDistinctIslands {
    
    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    public static void main(String[] args) {
        NumOfDistinctIslands sol = new NumOfDistinctIslands();
        
        int[][] grid = {
                        {1,1,0,1,1},
                        {1,0,0,0,0},
                        {0,0,0,0,1},
                        {1,1,0,1,1}
                        };
        
        System.out.println(sol.numberofDistinctIslands(grid));
        
    }

    /**
     * @param grid: a list of lists of integers
     * @return: return an integer, denote the number of distinct islands
     */
    public int numberofDistinctIslands(int[][] grid) {
        // write your code here
        Set<String> set = new HashSet<String>();
        
        int n = grid.length; //rows
        int m = grid[0].length; //columns
        boolean[][] visited = new boolean[n][m];
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(grid[i][j] == 1 && !visited[i][j]) {
                    StringBuilder sb = new StringBuilder();
                    dfs(i, j, grid, visited, sb, i, j);
                    set.add(sb.toString());
                }
            }
        }
        
        return set.size();
    }

    private void dfs(int i, int j, int[][] grid, boolean[][] visited, StringBuilder sb, int startI, int startJ) {
        if(i >= grid.length || i < 0 || j >= grid[0].length || j < 0 || visited[i][j] || grid[i][j] == 0) {
            return;
        }
        
        int relativeI = i - startI;
        int relativeJ = j - startJ;
        sb.append(relativeI + "," + relativeJ + " ");
        visited[i][j] = true;
        
        for(int[] dir : dirs) {
            dfs(i + dir[0], j + dir[1], grid, visited, sb, startI, startJ);
        }
    }
}
