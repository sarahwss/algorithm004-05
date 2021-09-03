package leetcode.editor.cn;//给定一个由 0 和 1 组成的矩阵 matrix ，找出只包含 1 的最大矩形，并返回其面积。
//
// 注意：此题 matrix 输入格式为一维 01 字符串数组。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：matrix = ["10100","10111","11111","10010"]
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
//输入：matrix = ["0"]
//输出：0
// 
//
// 示例 4： 
//
// 
//输入：matrix = ["1"]
//输出：1
// 
//
// 示例 5： 
//
// 
//输入：matrix = ["00"]
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
//
// 
//
// 注意：本题与主站 85 题相同（输入参数格式不同）： https://leetcode-cn.com/problems/maximal-rectangle
/// 
// Related Topics 栈 数组 动态规划 矩阵 单调栈 
// 👍 1 👎 0


import java.util.ArrayDeque;
import java.util.Deque;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution_剑指040 {

    public int maximalRectangle(String[] matrix) {
        if (matrix.length == 0 || matrix[0].length() == 0) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length();
        int[] heights = new int[n];
        int max = 0;
        for (int i = 0; i < m; i++) {
            String s = matrix[i];
            for (int j = 0; j < n; j++) {
                if (s.charAt(j) == '1') {
                    heights[j]++;
                } else {
                    heights[j] = 0;
                }
            }
            // z注意要写在外层循环
            max = Math.max(max, maximalRectangle(heights));
        }
        return max;
    }

    private int maximalRectangle(int[] heights) {
        int n = heights.length;
        Deque<Integer> deque = new ArrayDeque<>(n);
        deque.offerFirst(-1);
        int max = 0;
        for (int i = 0; i < n; i++) {
            int h = heights[i];
            while (deque.peekFirst() != -1 && heights[deque.peekFirst()] >= h) {
                max = Math.max(max, heights[deque.pollFirst()] * (i - deque.peekFirst() - 1));
            }
            deque.offerFirst(i);
        }
        while (deque.peekFirst() != -1) {
            max = Math.max(max, heights[deque.pollFirst()] * (n - deque.peekFirst() - 1));
        }
        return max;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
