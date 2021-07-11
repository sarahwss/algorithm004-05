package leetcode.editor.cn;//编写一个程序，通过填充空格来解决数独问题。
//
// 数独的解法需 遵循如下规则： 
//
// 
// 数字 1-9 在每一行只能出现一次。 
// 数字 1-9 在每一列只能出现一次。 
// 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图） 
// 
//
// 数独部分空格内已填入了数字，空白格用 '.' 表示。 
//
// 
//
// 
// 
// 
// 示例： 
//
// 
//输入：board = [["5","3",".",".","7",".",".",".","."],["6",".",".","1","9","5","."
//,".","."],[".","9","8",".",".",".",".","6","."],["8",".",".",".","6",".",".","."
//,"3"],["4",".",".","8",".","3",".",".","1"],["7",".",".",".","2",".",".",".","6"
//],[".","6",".",".",".",".","2","8","."],[".",".",".","4","1","9",".",".","5"],["
//.",".",".",".","8",".",".","7","9"]]
//输出：[["5","3","4","6","7","8","9","1","2"],["6","7","2","1","9","5","3","4","8"
//],["1","9","8","3","4","2","5","6","7"],["8","5","9","7","6","1","4","2","3"],["
//4","2","6","8","5","3","7","9","1"],["7","1","3","9","2","4","8","5","6"],["9","
//6","1","5","3","7","2","8","4"],["2","8","7","4","1","9","6","3","5"],["3","4","
//5","2","8","6","1","7","9"]]
//解释：输入的数独如上图所示，唯一有效的解决方案如下所示：
//
//
// 
//
// 
//
// 提示： 
//
// 
// board.length == 9 
// board[i].length == 9 
// board[i][j] 是一位数字或者 '.' 
// 题目数据 保证 输入数独仅有一个解 
// 
// 
// 
// 
// Related Topics 数组 回溯 矩阵 
// 👍 876 👎 0


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution_37 {

    public void solveSudoku(char[][] board) {
        boolean[][] row = new boolean[9][9];
        boolean[][] col = new boolean[9][9];
        boolean[][] box = new boolean[9][9];
        List<Integer> spaces = new LinkedList<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c == '.') {
                    spaces.add(i * 9 + j);
                } else {
                    int n = c - '1';
                    row[i][n] = true;
                    col[j][n] = true;
                    box[i / 3 * 3 + j / 3][n] = true;
                }
            }
        }
        solveSudoku(board, spaces, row, col, box);
    }

    public int getNextPos(List<Integer> spaces, boolean[][] row, boolean[][] col, boolean[][] box) {
        int min = 10;
        int index = -1;
        for (int space : spaces) {
            int i = space / 9;
            int j = space % 9;
            int count = 0;
            for (int k = 0; k < 9; k++) {
                if (row[i][k] || col[j][k] || box[i / 3 * 3 + j / 3][k]) {
                    continue;
                }
                count++;
            }
            if (count < min) {
                min = count;
                index = space;
            }
        }
        return index;
    }


    boolean solveSudoku(char[][] board, List<Integer> spaces, boolean[][] row, boolean[][] col, boolean[][] box) {
        if (spaces.size() == 0) {
            return true;
        }
        int space = getNextPos(spaces, row, col, box);
        int x = space / 9;
        int y = space % 9;
        spaces.remove((Integer) space);
        for (int n = 0; n < 9; n++) {
            if (row[x][n] || col[y][n] || box[x / 3 * 3 + y / 3][n]) {
                continue;
            }
            board[x][y] = (char) ('1' + n);
            row[x][n] = col[y][n] = box[x / 3 * 3 + y / 3][n] = true;
            if (solveSudoku(board, spaces, row, col, box)) {
                System.out.println(true);
                return true;
            }
            row[x][n] = col[y][n] = box[x / 3 * 3 + y / 3][n] = false;
            board[x][y] = '.';
        }
        // 方便外层尝试其他解法
        spaces.add(space);
        return false;
    }

    public static void main(String[] args) {
        char[][] board = new char[][]{{'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'}, {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'}, {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'}, {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'}, {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
        new Solution_37().solveSudoku(board);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + ",");
            }
            System.out.println();

        }
    }


}
//leetcode submit region end(Prohibit modification and deletion)
