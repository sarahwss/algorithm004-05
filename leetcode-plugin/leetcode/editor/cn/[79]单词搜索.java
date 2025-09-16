//给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。 
//
// 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。 
//
// 
//
// 示例 1： 
//
// 
//输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = 
//"ABCCED"
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = 
//"SEE"
//输出：true
// 
//
// 示例 3： 
//
// 
//输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = 
//"ABCB"
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// m == board.length 
// n = board[i].length 
// 1 <= m, n <= 6 
// 1 <= word.length <= 15 
// board 和 word 仅由大小写英文字母组成 
// 
//
// 
//
// 进阶：你可以使用搜索剪枝的技术来优化解决方案，使其在 board 更大的情况下可以更快解决问题？ 
// Related Topics 数组 回溯 矩阵 👍 1539 👎 0


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(board, word, i * n + j, 0)) {
                    return true;
                }
            }
        }
//        for (int i = 0; i < m; i++) {
//            System.out.println(Arrays.toString(board[i]));
//        }
        return false;

    }

    boolean dfs(char[][] board, String word, int idx, int l) {

        int m = board.length;
        int n = board[0].length;

        int i = idx / n;
        int j = idx % n;
//        System.out.println(board[i][j]);
        if (word.charAt(l) != board[i][j]) {
            return false;
        }
        char c = board[i][j];
        board[i][j] = '0';
        if (l == word.length() - 1) {
            board[i][j] = c;
            return true;
        }
        int[] dx = new int[]{1, -1, 0, 0};
        int[] dy = new int[]{0, 0, 1, -1};
        for (int k = 0; k < 4; k++) {
            int x = i + dx[k];
            int y = j + dy[k];
            int d = x * n + y;
            if (x < 0 || x >= m || y < 0 || y >= n) {
                continue;
            }
            if (board[x][y] != '0') {
                if (dfs(board, word, d, l + 1)) {
                    board[i][j] = c;
                    return true;
                }
            }
        }
        board[i][j] = c;
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
