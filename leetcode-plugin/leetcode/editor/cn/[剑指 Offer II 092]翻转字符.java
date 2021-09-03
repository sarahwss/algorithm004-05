package leetcode.editor.cn;//如果一个由 '0' 和 '1' 组成的字符串，是以一些 '0'（可能没有 '0'）后面跟着一些 '1'（也可能没有 '1'）的形式组成的，那么该字符串是 单
//调递增 的。 
//
// 我们给出一个由字符 '0' 和 '1' 组成的字符串 s，我们可以将任何 '0' 翻转为 '1' 或者将 '1' 翻转为 '0'。 
//
// 返回使 s 单调递增 的最小翻转次数。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "00110"
//输出：1
//解释：我们翻转最后一位得到 00111.
// 
//
// 示例 2： 
//
// 
//输入：s = "010110"
//输出：2
//解释：我们翻转得到 011111，或者是 000111。
// 
//
// 示例 3： 
//
// 
//输入：s = "00011000"
//输出：2
//解释：我们翻转得到 00000000。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 20000 
// s 中只包含字符 '0' 和 '1' 
// 
//
// 
//
// 注意：本题与主站 926 题相同： https://leetcode-cn.com/problems/flip-string-to-monotone-in
//creasing/ 
// Related Topics 字符串 动态规划 
// 👍 2 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution_剑指_092 {

    public int minFlipsMonoIncr(String s) {
        int n = s.length();
        if (n == 0) {
            return 0;
        }
        char[] arr = s.toCharArray();
        // 第i个字符，翻转后是0或1
        int[][] dp = new int[2][2];
        dp[0][0] = (arr[0] == '0' ? 0 : 1);
        dp[0][1] = (arr[0] == '1' ? 0 : 1);
        for (int i = 1; i < n; i++) {
            dp[i % 2][0] = dp[(i - 1) % 2][0] + (arr[i] == '0' ? 0 : 1);
            dp[i % 2][1] = Math.min(dp[(i - 1) % 2][0], dp[(i - 1) % 2][1]) + (arr[i] == '1' ? 0 : 1);
        }
        return Math.min(dp[(n - 1) % 2][0], dp[(n - 1) % 2][1]);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
