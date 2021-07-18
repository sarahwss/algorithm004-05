package leetcode.editor.cn;//给你一个字符串 s，找到 s 中最长的回文子串。
//
// 
//
// 示例 1： 
//
// 
//输入：s = "babad"
//输出："bab"
//解释："aba" 同样是符合题意的答案。
// 
//
// 示例 2： 
//
// 
//输入：s = "cbbd"
//输出："bb"
// 
//
// 示例 3： 
//
// 
//输入：s = "a"
//输出："a"
// 
//
// 示例 4： 
//
// 
//输入：s = "ac"
//输出："a"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// s 仅由数字和英文字母（大写和/或小写）组成 
// 
// Related Topics 字符串 动态规划 
// 👍 3833 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution_5 {

    public String longestPalindrome(String s) {
        int n = s.length();
        char[] arr = s.toCharArray();
        boolean[][] dp = new boolean[n][n];
        // 单个字符都是回文串
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }
        int max = 1;
        // 字符串长度至少为1
        int a = 0;
        int b = 0;
        // 右端点
        for (int i = 1; i < n; i++) {
            char c = arr[i ];
            // 左端点,倒序，因为需要后面的数据
            for (int k = i - 1; k >= 0; k--) {
                if (arr[k ] == c && (k == i - 1 || dp[k + 1][i - 1])) {
                    dp[k][i] = true;
                    if (i - k + 1 > max) {
                        max = i - k + 1;
                        a = k;
                        b = i;
                    }
                }
            }
        }
        return s.substring(a, b + 1);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
