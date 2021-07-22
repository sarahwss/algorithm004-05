package leetcode.editor.cn;//假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
//
// 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？ 
//
// 注意：给定 n 是一个正整数。 
//
// 示例 1： 
//
// 输入： 2
//输出： 2
//解释： 有两种方法可以爬到楼顶。
//1.  1 阶 + 1 阶
//2.  2 阶 
//
// 示例 2： 
//
// 输入： 3
//输出： 3
//解释： 有三种方法可以爬到楼顶。
//1.  1 阶 + 1 阶 + 1 阶
//2.  1 阶 + 2 阶
//3.  2 阶 + 1 阶
// 
// Related Topics 记忆化搜索 数学 动态规划 
// 👍 1729 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution_70_2 {

    public int climbStairs(int n) {
        int[] dp = new int[n + 1];
        // 为了得到dp[2]==2
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}

class Solution_70 {

    int[] memo;

    public int climbStairs(int n) {
        memo = new int[n];
        return climb(n);
    }

    int climb(int n) {
        if (n == 1 || n == 2) {
            return n;
        }
        if (memo[n - 1] != 0) {
            return memo[n - 1];
        }
        memo[n - 1] = climb(n - 1) + climb(n - 2);
        return memo[n - 1];
    }
}

class Solution_70_1 {


    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }
        int[] dp = new int[n];
        // 楼梯一级
        dp[0] = 1;
        // 楼梯二级
        dp[1] = 2;
        for (int i = 2; i < n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n - 1];

    }

}
//leetcode submit region end(Prohibit modification and deletion)
