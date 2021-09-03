package leetcode.editor.cn;//给定一个字符串 s，请将 s 分割成一些子串，使每个子串都是回文串。
//
// 返回符合要求的 最少分割次数 。 
//
// 
// 
// 
//
// 示例 1： 
//
// 
//输入：s = "aab"
//输出：1
//解释：只需一次分割就可将 s 分割成 ["aa","b"] 这样两个回文子串。
// 
//
// 示例 2： 
//
// 
//输入：s = "a"
//输出：0
// 
//
// 示例 3： 
//
// 
//输入：s = "ab"
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 2000 
// s 仅由小写英文字母组成 
// 
// 
// 
//
// 
//
// 注意：本题与主站 132 题相同： https://leetcode-cn.com/problems/palindrome-partitioning-ii
/// 
// Related Topics 字符串 动态规划 
// 👍 1 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution_剑指_094 {

    public int minCut(String s) {
        int n = s.length();
        boolean[][] isPail = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            char a = s.charAt(i);
            for (int j = 0; j <= i; j++) {
                char b = s.charAt(j);
                if (a == b && (i - j <= 1 || isPail[j + 1][i - 1])) {
                    isPail[j][i] = true;
                }
            }
        }
        // 前k个长度最小分割数
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            // 不用分割，最小为0
            if (isPail[0][i]) {
                dp[i] = 0;
            } else {
                // 别少了这个初始最大值
                dp[i] = i;
                // 注意这里是<=i
                for (int j = 1; j <= i; j++) {
                    if (isPail[j][i]) {
                        // 注意这李是j-1,不是j
                        dp[i] = Math.min(dp[i], dp[j - 1] + 1);
                    }
                }
            }
        }
        return dp[n - 1];
    }


}
//leetcode submit region end(Prohibit modification and deletion)
