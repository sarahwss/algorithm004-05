package leetcode.editor.cn;//给定一个字符串 s 和一个字符串 t ，计算在 s 的子序列中 t 出现的个数。
//
// 字符串的一个 子序列 是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。（例如，"ACE" 是 "ABCDE" 的一个子序列
//，而 "AEC" 不是） 
//
// 题目数据保证答案符合 32 位带符号整数范围。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "rabbbit", t = "rabbit"
//输出：3
//解释：
//如下图所示, 有 3 种可以从 s 中得到 "rabbit" 的方案。
//rabbbit
//rabbbit
//rabbbit 
//
// 示例 2： 
//
// 
//输入：s = "babgbag", t = "bag"
//输出：5
//解释：
//如下图所示, 有 5 种可以从 s 中得到 "bag" 的方案。 
//babgbag
//babgbag
//babgbag
//babgbag
//babgbag
// 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length, t.length <= 1000 
// s 和 t 由英文字母组成 
// 
//
// 
//
// 注意：本题与主站 115 题相同： https://leetcode-cn.com/problems/distinct-subsequences/ 
// Related Topics 字符串 动态规划 
// 👍 1 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution_剑指_097 {

    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();
        if (m < n) {
            return 0;
        }
        int[][] dp = new int[m + 1][n + 1];
        dp[0][0] = 1;
        for (int i = 0; i < m; i++) {
            dp[i + 1][0] = 1;
            // 注意这里是<=i，不是<n
            // 注意不要少了j < t.length()
            for (int j = 0; j <= i && j < t.length(); j++) {
                // 注意这里是t.charAt(j）,不是
                // 注意没有max计算，是加计算
                // 注意第二种情况是dp[i][j + 1】,不是dp[i][j】,舍弃掉i
                dp[i + 1][j + 1] = s.charAt(i) == t.charAt(j) ? dp[i][j] + dp[i][j + 1] : dp[i][j + 1];
            }
        }
        return dp[m][n];

    }
}
//leetcode submit region end(Prohibit modification and deletion)
