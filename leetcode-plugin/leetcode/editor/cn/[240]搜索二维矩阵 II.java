//编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性： 
//
// 
// 每行的元素从左到右升序排列。 
// 每列的元素从上到下升序排列。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21
//,23,26,30]], target = 5
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21
//,23,26,30]], target = 20
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// m == matrix.length 
// n == matrix[i].length 
// 1 <= n, m <= 300 
// -10⁹ <= matrix[i][j] <= 10⁹ 
// 每行的所有元素从左到右升序排列 
// 每列的所有元素从上到下升序排列 
// -10⁹ <= target <= 10⁹ 
// 
// Related Topics 数组 二分查找 分治 矩阵 👍 1227 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int i = m - 1;
        int j = 0;
        while (i >= 0 && j < n) {
            if (matrix[i][j] < target) {
                j++;
            } else if (matrix[i][j] > target) {
                i--;
            } else {
                return true;
            }
        }
        return false;

    }

    boolean searchMatrix(int[][] matrix, int target, int startI, int startJ, Boolean[] memo) {
        int m = matrix.length;
        int n = matrix[0].length;
        int i = startI;
        int j = startJ;
        if (memo[i * n + j] != null) {
            return memo[i * n + j];
        }
        int value = matrix[i][j];
        if (value == target) {
            memo[i * n + j] = true;
            return true;
        }
        // 往右或往下找
        if (value < target) {
            // 必须一步一步走，不能用while，且i和j不能同时相加
            if (i < m - 1 && searchMatrix(matrix, target, i + 1, j, memo)) {
                memo[i * n + j] = true;
                return true;
            }
            if (j < n - 1 && searchMatrix(matrix, target, i, j + 1, memo)) {
                memo[i * n + j] = true;
                return true;
            }
        }
        // value>target
        memo[i * n + j] = false;
        return false;


    }
}
//leetcode submit region end(Prohibit modification and deletion)
