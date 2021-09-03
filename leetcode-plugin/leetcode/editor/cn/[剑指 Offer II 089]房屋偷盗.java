package leetcode.editor.cn;//一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响小偷偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被
//小偷闯入，系统会自动报警。 
//
// 给定一个代表每个房屋存放金额的非负整数数组 nums ，请计算 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,3,1]
//输出：4
//解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
//     偷窃到的最高金额 = 1 + 3 = 4 。 
//
// 示例 2： 
//
// 
//输入：nums = [2,7,9,3,1]
//输出：12
//解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
//     偷窃到的最高金额 = 2 + 9 + 1 = 12 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 100 
// 0 <= nums[i] <= 400 
// 
//
// 
//
// 注意：本题与主站 198 题相同： https://leetcode-cn.com/problems/house-robber/ 
// Related Topics 数组 动态规划 
// 👍 0 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution_剑指_089_1 {

    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        // 直到当前房子的最大收获
        int[] dp = new int[2];
        //别忘了初始值
        dp[0] = nums[0];
        // 注意判断长度
        if (n > 1) {
            dp[1] = Math.max(nums[0], nums[1]);
        }
        // 从2开始
        for (int i = 2; i < n; i++) {
            // 不偷当前房子，偷或不偷前一家房子；偷当前房子，偷或不偷前二家房子
            dp[i % 2] = Math.max(dp[(i - 1) % 2], dp[(i - 2) % 2] + nums[i]);
        }
        return dp[(n - 1) % 2];
    }
}

class Solution_剑指_089 {

    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return nums[0];
        }
        // 第i栋，偷或不偷
        // 注意二维要制定长度，这样会有默认初始值
        // 虽然只需要前一个i，但计算需要两步，所以需要数组中最近两个数
        int[][] dp = new int[2][2];
        dp[0][0] = 0;
        dp[0][1] = nums[0];
        // 注意从1开始
        for (int i = 1; i < n; i++) {
            dp[i % 2][0] = Math.max(dp[(i - 1) % 2][0], dp[(i - 1) % 2][1]);
            // 注意所有的状态都可i-1比
            dp[i % 2][1] = nums[i] + dp[(i - 1) % 2][0];
        }
        return Math.max(dp[(n - 1) % 2][0], dp[(n - 1) % 2][1]);
    }

    public static void main(String[] args) {
        System.out.println(new Solution_剑指_089().rob(new int[]{1, 2, 3, 1}));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
