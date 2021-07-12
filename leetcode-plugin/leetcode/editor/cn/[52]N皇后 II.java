package leetcode.editor.cn;//n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
//
// 给你一个整数 n ，返回 n 皇后问题 不同的解决方案的数量。 
//
// 
//
// 
// 
// 示例 1： 
//
// 
//输入：n = 4
//输出：2
//解释：如上图所示，4 皇后问题存在两个不同的解法。
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 9 
// 皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。 
// 
// 
// 
// Related Topics 回溯 
// 👍 275 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution_52 {

    public int totalNQueens(int n) {
        return totalNQueens(n, 0, 0, 0, 0);
    }

    int totalNQueens(int n, int i, int col, int pie, int na) {
        if (i == n) {
            return 1;
        }
        int res = 0;
        // 括号不要少
        int cols = (~(col | pie | na)) & ((1 << n) - 1);
        while (cols != 0) {
            int c = cols & (-cols);
            cols = cols & (cols - 1);
            res += totalNQueens(n, i + 1, col | c, (pie | c) << 1, (na | c) >> 1);
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
