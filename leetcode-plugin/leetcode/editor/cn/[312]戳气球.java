package leetcode.editor.cn;//有 n 个气球，编号为0 到 n - 1，每个气球上都标有一个数字，这些数字存在数组 nums 中。
//
// 现在要求你戳破所有的气球。戳破第 i 个气球，你可以获得 nums[i - 1] * nums[i] * nums[i + 1] 枚硬币。 这里的 i -
// 1 和 i + 1 代表和 i 相邻的两个气球的序号。如果 i - 1或 i + 1 超出了数组的边界，那么就当它是一个数字为 1 的气球。 
//
// 求所能获得硬币的最大数量。 
//
// 
//示例 1：
//
// 
//输入：nums = [3,1,5,8]
//输出：167
//解释：
//nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
//coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167 
//
// 示例 2： 
//
// 
//输入：nums = [1,5]
//输出：10
// 
//
// 
//
// 提示： 
//
// 
// n == nums.length 
// 1 <= n <= 500 
// 0 <= nums[i] <= 100 
// 
// Related Topics 数组 动态规划 
// 👍 751 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution_312 {

    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n + 2][n + 2];
        int[] vals = new int[n + 2];
        vals[0] = vals[n + 1] = 1;
        for (int i = 1; i < n + 1; i++) {
            vals[i] = nums[i - 1];
        }
        // 开区间左,k在i后面，计算k时i后面的值应该已算好，所以i要从后往前算，迭代开始于最后一列
        for (int i = n - 1; i >= 0; i--) {
            // 开区间右
            for (int j = i + 2; j <= n + 1; j++) {
                // 开区间内的点
                for (int k = i + 1; k < j; k++) {
                    int sum = dp[i][k] + vals[i] * vals[k] * vals[j] + dp[k][j];
                    dp[i][j] = Math.max(dp[i][j], sum);
                }
            }
        }
        return dp[0][n + 1];

    }
}
//leetcode submit region end(Prohibit modification and deletion)
