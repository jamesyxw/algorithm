package puzzle.lintcode;

import java.util.LinkedList;
import java.util.Queue;

import puzzle.lintcode.common.ArrayUtils;

/**
 * Given a 2D array representing the coordinates on the map, 
 * there are only values 0, 1, 2 on the map. value 0 means that it can pass, 
 * value 1 means not passable, value 2 means target place. 
 * Starting from the coordinates [0,0],You can only go up, down, left and right. 
 * Find the shortest path that can reach the destination, and return the length of the path.
 * 
 * Example
    Given:
    
    [
     [0, 0, 0],
     [0, 0, 1],
     [0, 0, 2]
    ]
    Return: 4
    
    1.The map must exist and is not empty, there is only one target

 * @author jamesyxw
 *
 */
public class ShortestPathToDestination {
    
    public static void main(String[] args) {
        int[][] map = {
                {0, 0, 0},
                {0, 1, 1},
                {0, 0, 2}
        };
        
        System.out.println(shortestPath(map));
        
        System.out.println("\n\n");
        
        int[][] map2= {
                {0, 1},
                {0, 1},
                {0, 2}
        };
        
        System.out.println(shortestPath(map2));
    }
    
    
    public static class Index {
        public int x;
        public int y;
        public Index(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    /**
     * 0: can pass
     * 1: not passable
     * 2: target
     * 
     * @param targetMap: 
     * @return: nothing
     */
    public static int shortestPath(int[][] targetMap) {
        if (targetMap.length == 0 || targetMap[0].length == 0) {
            return 0;
        }
        
        int n = targetMap.length; //vertical axis length
        int m = targetMap[0].length; //horizontal axis length
        
        
        int x = 0; //current index on vertical axis
        int y = 0; //current index on horizontal axis
        
        Queue<Index> queue = new LinkedList<Index>();
        int[][] minMap = new int[n][m];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                minMap[i][j] = Integer.MAX_VALUE;
            }
        }
        
        minMap[0][0] = 0;
        
        queue.add(new Index(0, 0));
        int targetX = -1;
        int targetY = -1;
        
        while(!queue.isEmpty()) {
            Index current = queue.poll();
            x = current.x;
            y = current.y;
            
            if (targetMap[x][y] == 2) {
                targetX = x;
                targetY = y;
            }
            
            //not the first row - up
            if (x > 0) {
                // if moving left is passable and moving from (x,y) to (x-1, y) will reduce the shortest path to (x-1, y)
                // add the (x-1, y) to the queue and update minMap
                if (targetMap[x - 1][y] != 1 && minMap[x-1][y] > minMap[x][y] + 1) {
                    queue.add(new Index(x - 1, y));
                    minMap[x-1][y] = minMap[x][y] + 1;
                }
            }
            
            //not the first column - left
            if (y > 0) {
                if (targetMap[x][y - 1] != 1 && minMap[x][y - 1] > minMap[x][y] + 1) {
                    queue.add(new Index(x, y - 1));
                    minMap[x][y - 1] = minMap[x][y] + 1;
                }
            }
            
            //not the last row - down
            if (x < n - 1) {
                if (targetMap[x + 1][y] != 1 && minMap[x + 1][y] > minMap[x][y] + 1) {
                    queue.add(new Index(x + 1, y));
                    minMap[x + 1][y] = minMap[x][y] + 1;
                }
            }
            
            //not the last column - right
            if (y < m - 1) {
                if (targetMap[x][y + 1] != 1 && minMap[x][y + 1] > minMap[x][y] + 1) {
                    queue.add(new Index(x, y + 1));
                    minMap[x][y + 1] = minMap[x][y] + 1;
                }
            }
        }
        
        if (targetX == -1 && targetY == -1) {
            return -1;
        }
        
        return minMap[targetX][targetY];
    }
}
