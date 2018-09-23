package puzzle.lintcode.microsoft;

import puzzle.lintcode.common.ArrayUtils;

/**
 * Given a 2D board and a word, find if the word exists in the grid.

    The word can be constructed from letters of sequentially adjacent cell, 
    where "adjacent" cells are those horizontally or vertically neighboring. 
    
    The same letter cell may not be used more than once.
    
    Given board =

        [
          "ABCE",
          "SFCS",
          "ADEE"
        ]
        word = "ABCCED", -> returns true,
        word = "SEE", -> returns true,
        word = "ABCB", -> returns false.

 * @author jamesyxw
 *
 */
public class WordSearch {
    
    private static int [][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    
    public static void main(String[] args) {
        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        
        String[] inputs = {"ABCCED", "SEE", "ABCB"};
        
        for (int i = 0; i < inputs.length; i++) {
            System.out.println(inputs[i] + ": " + exist(board, inputs[i]));
        }
    }

    /**
     * @param board: A list of lists of character
     * @param word: A string
     * @return: A boolean
     */
    public static boolean exist(char[][] board, String word) {
        if (board.length == 0 || board[0].length == 0 || word == null || word.isEmpty()) {
            return false;
        }
        
        boolean[][] visited = new boolean[board.length][board[0].length];
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                boolean isFound = search(board, i, j, visited, 0, word);
                if (isFound) {
                    return true;
                }
            }
        }
        
        return false;
    }

    private static boolean search(char[][] board, int i, int j, boolean[][] visited, int current, String word) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
            return false;
        }
        
        if (board[i][j] != word.charAt(current) || visited[i][j] || current >= word.length()) {
            return false;
        }
        
        if (board[i][j] == word.charAt(current) && current == word.length() - 1) {
            return true;
        }
        
        visited[i][j] = true;
        for(int k = 0; k < dir.length; k++) {
            boolean isFound = search(board, i + dir[k][0], j + dir[k][1], visited, current + 1, word);
            if (isFound) {
                return true;
            }
        }
        visited[i][j] = false;
        
        return false;
        
    }
    
}
