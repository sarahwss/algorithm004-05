package leetcode.editor.cn;//给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
//
// 
//
// 示例 1： 
//
// 
//输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"]
//,["1","0","0","1","0"]]
//输出：6
//解释：最大矩形如上图所示。
// 
//
// 示例 2： 
//
// 
//输入：matrix = []
//输出：0
// 
//
// 示例 3： 
//
// 
//输入：matrix = [["0"]]
//输出：0
// 
//
// 示例 4： 
//
// 
//输入：matrix = [["1"]]
//输出：1
// 
//
// 示例 5： 
//
// 
//输入：matrix = [["0","0"]]
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// rows == matrix.length 
// cols == matrix[0].length 
// 0 <= row, cols <= 200 
// matrix[i][j] 为 '0' 或 '1' 
// 
// Related Topics 栈 数组 动态规划 矩阵 单调栈 
// 👍 966 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution_85 {

    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        // 记录当前行j位置为结尾的最大1矩形宽度
        int[][] dp = new int[m][n];
        int max = 0;
        // 整体准备初始值，初始值提前准备好能提高执行效率
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    dp[i][j] = j == 0 ? 1 : dp[i][j - 1] + 1;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    // 因为min计算，width起始值不能为0
                    int width = dp[i][j];
                    // 本行也要计算面积
                    int area = width;
                    // 从下往上算矩形面积
                    int k = i - 1;
                    for (; k >= 0; k--) {
                        // 为0的话，后续面积都为0
                        if (matrix[k][j] == '0') {
                            break;
                        }
                        // 这里是min不是max
                        width = Math.min(width, dp[k][j]);
                        // 计算max要在最小width计算同时，不放在循环外
                        // 注意这里是i，不是j
                        // 计算当前位置的最大面积
                        area = Math.max(width * (i - k + 1), area);
                    }
                    // 更新最大面积
                    max = Math.max(area, max);
                }
            }
        }
        return max;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
