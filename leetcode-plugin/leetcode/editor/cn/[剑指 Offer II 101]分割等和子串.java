package leetcode.editor.cn;//给定一个非空的正整数数组 nums ，请判断能否将这些数字分成元素和相等的两部分。
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,5,11,5]
//输出：true
//解释：nums 可以分割成 [1, 5, 5] 和 [11] 。 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,3,5]
//输出：false
//解释：nums 不可以分为和相等的两部分
// 
//
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 200 
// 1 <= nums[i] <= 100 
// 
//
// 
//
// 注意：本题与主站 416 题相同： https://leetcode-cn.com/problems/partition-equal-subset-sum
/// 
// Related Topics 数组 动态规划 
// 👍 3 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution_剑指_101_1 {

    public boolean canPartition(int[] nums) {

        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 == 1) {
            return false;
        }
        int target = sum / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        // 注意这里判断条件含等号
        for (int i = 1; i <= nums.length; i++) {
            // 从右向左填充， dp[i][j] 可替换dp[i - 1][j],，因为后面不会再用刀
            for (int j = target; j >= 0; j--) {
                // 替换，不使用本物品
                // 使用本物品
                // 注意这里判断的是<= 不是<=target
                if (!dp[j] && nums[i - 1] <= j) {
                    // 注意这里是j-不是target-
                    dp[j] = dp[j - nums[i - 1]];
                }
            }
        }
        return dp[target];

    }
}


class Solution_剑指_101 {

    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 == 1) {
            return false;
        }
        int target = sum / 2;
        boolean[][] dp = new boolean[nums.length + 1][target + 1];
        // 注意这里判断条件含等号
        for (int i = 0; i <= nums.length; i++) {
            dp[i][0] = true;
        }
        // 注意这里判断条件含等号
        for (int i = 1; i <= nums.length; i++) {
            for (int j = 1; j <= target; j++) {
                // 不使用本物品
                dp[i][j] = dp[i - 1][j];
                //使用本物品
                // 注意这里判断的是<= 不是<=target
                if (!dp[i][j] && nums[i - 1] <= j) {
                    // 注意这里是j-不是target-
                    dp[i][j] = dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        return dp[nums.length][target];

    }
}
//leetcode submit region end(Prohibit modification and deletion)
