package leetcode.editor.cn;//给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回
// -1。 
//
// 你可以认为每种硬币的数量是无限的。 
//
// 
//
// 示例 1： 
//
// 
//输入：coins = [1, 2, 5], amount = 11
//输出：3 
//解释：11 = 5 + 5 + 1 
//
// 示例 2： 
//
// 
//输入：coins = [2], amount = 3
//输出：-1 
//
// 示例 3： 
//
// 
//输入：coins = [1], amount = 0
//输出：0
// 
//
// 示例 4： 
//
// 
//输入：coins = [1], amount = 1
//输出：1
// 
//
// 示例 5： 
//
// 
//输入：coins = [1], amount = 2
//输出：2
// 
//
// 
//
// 提示： 
//
// 
// 1 <= coins.length <= 12 
// 1 <= coins[i] <= 231 - 1 
// 0 <= amount <= 104 
// 
//
// 
//
// 注意：本题与主站 322 题相同： https://leetcode-cn.com/problems/coin-change/ 
// Related Topics 广度优先搜索 数组 动态规划 
// 👍 2 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution_剑指_103_2 {

    public int coinChange(int[] coins, int amount) {
        // 前i种硬币凑出指定面额j
        int[][] dp = new int[coins.length + 1][amount + 1];
        // 求最小值，初始化最大值
        Arrays.fill(dp[0], amount + 1);
        // 注意这里要从0开始
        for (int i = 0; i < coins.length + 1; i++) {
            dp[i][0] = 0;
        }
        // coins按从小到大排列，越靠前的计算的步长越短，适合后面的迭代使用
        for (int j = 1; j < coins.length + 1; j++) {
            int coin = coins[j - 1];
            for (int i = amount; i > 0; i--) {
                // 不使用当前硬币
                dp[j][i] = dp[j - 1][i];
                // 使用当前硬币
                // 注意这里小于的是i，不是amount
                for (int k = 1; k * coin <= i; k++) {
                    // 这里别少了min计算
                    // 注意这里是dp[j】不是dp[amount]
                    dp[j][i] = Math.min(dp[j - 1][i - k * coin] + k, dp[j][i]);
                }
            }
        }
        return dp[coins.length][amount] > amount ? -1 : dp[coins.length][amount];
    }

    public static void main(String[] args) {
        System.out.println(new Solution_剑指_103_2().coinChange(new int[]{1, 2, 5}, 11));
    }
}

// 上面解法的空间简化版
class Solution_剑指_103 {

    public int coinChange(int[] coins, int amount) {
        // 简化的二维，前i种硬币凑出指定面额j
        int[] dp = new int[amount + 1];
        // 求最小值，初始化最大值
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        // coins按从小到大排列，越靠前的计算的步长越短，适合后面的迭代使用
        for (int coin : coins) {
            for (int i = amount; i > 0; i--) {
                // 注意这里小于的是i，不是amount
                for (int k = 1; k * coin <= i; k++) {
                    // 这里别少了min计算
                    // 注意这里是dp[j】不是dp[amount]
                    dp[i] = Math.min(dp[i - k * coin] + k, dp[i]);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
}

class Solution_剑指_103_1 {

    public int coinChange(int[] coins, int amount) {
        // 凑出面值的最少硬币数
        int[] dp = new int[amount + 1];
        dp[0] = 0;
        // 最少凑面值coin，且i-coin一定大于等于0
        for (int i = 1; i < amount + 1; i++) {
            dp[i] = amount + 1;
            for (int coin : coins) {
                // 注意这里有等号
                if (i >= coin) {
                    // 注意这里要加1
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
