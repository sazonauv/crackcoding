package poc;

import java.util.Stack;

/**
 * Created by slava on 13/11/17.
 */
public class BoardWordSearch {

    boolean[][] visited;
    char[][] board;
    char[] chars;

    public BoardWordSearch(char[][] board) {
        this.board = board;
        visited = new boolean[board.length][board[0].length];
    }

    private void clear() {
        for (int i=0; i<visited.length; i++) {
            for (int j=0; j<visited[0].length; j++) {
                visited[i][j] = false;
            }
        }
    }

    public boolean search(char[] chars) {
        this.chars = chars;
        for (int i=0; i<board.length; i++) {
            for (int j=0; j<board[0].length; j++) {
                if (localSearch(i, j, 0)) {
                    return true;
                }
                clear();
            }
        }
        return false;
    }

    private boolean localSearch(int i, int j, int k) {
        if (i<0 || j<0 || i>=board.length || j>=board[0].length) {
            return false;
        }
        if (board[i][j] != chars[k] || visited[i][j]) {
            return false;
        }
        visited[i][j] = true;
        if (k == chars.length-1) {
            return true;
        }
        return localSearch(i, j-1, k+1)
                || localSearch(i-1, j, k+1)
                || localSearch(i, j+1, k+1)
                || localSearch(i+1, j, k+1);
    }

}
