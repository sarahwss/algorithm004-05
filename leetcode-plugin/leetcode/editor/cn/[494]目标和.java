//给你一个整数数组 nums 和一个整数 target 。 
//
// 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ： 
//
// 
// 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。 
// 
//
// 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,1,1,1,1], target = 3
//输出：5
//解释：一共有 5 种方法让最终目标和为 3 。
//-1 + 1 + 1 + 1 + 1 = 3
//+1 - 1 + 1 + 1 + 1 = 3
//+1 + 1 - 1 + 1 + 1 = 3
//+1 + 1 + 1 - 1 + 1 = 3
//+1 + 1 + 1 + 1 - 1 = 3
// 
//
// 示例 2： 
//
// 
//输入：nums = [1], target = 1
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 20 
// 0 <= nums[i] <= 1000 
// 0 <= sum(nums[i]) <= 1000 
// -1000 <= target <= 1000 
// 
// Related Topics 数组 动态规划 回溯 👍 1515 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public int findTargetSumWays(int[] nums, int target) {
        //        return findTargetSumWays(nums, target, 0, 0);
        // 前i个，总合为j的个数
        int[][] dp = new int[nums.length + 1][1001];
        dp[0][0] = 1;
        int sum = 0;
        for (int i = 1; i <= nums.length; i++) {
            int num = nums[i - 1];
            sum += num;
            for (int j = 0; j <= sum; j++) {

                if (j < num) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j - num] + dp[i - 1][j];
                }
//                System.out.println(i + " " + j + " " + dp[i][j] + " " + sum);
            }
        }
        // sum为正整数，是最大值，-sum是最小值
        // sum-2j = target，sum-target必须是偶数
        if (sum < target || -sum > target || (sum - target) % 2 == 1) {
            return 0;
        }
        return dp[nums.length][(sum - target) / 2];
    }


    int findTargetSumWays(int[] nums, int target, int i, int sum) {
        if (i == nums.length) {
            if (sum == target) {
                return 1;
            }
            return 0;
        }
        int res = 0;
        int num = nums[i];
        res += findTargetSumWays(nums, target, i + 1, sum + num);
        res += findTargetSumWays(nums, target, i + 1, sum - num);
        return res;
    }


}
//leetcode submit region end(Prohibit modification and deletion)
