package leetcode.editor.cn;//给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。
//
// '?' 可以匹配任何单个字符。
//'*' 可以匹配任意字符串（包括空字符串）。
// 
//
// 两个字符串完全匹配才算匹配成功。 
//
// 说明: 
//
// 
// s 可能为空，且只包含从 a-z 的小写字母。 
// p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。 
// 
//
// 示例 1: 
//
// 输入:
//s = "aa"
//p = "a"
//输出: false
//解释: "a" 无法匹配 "aa" 整个字符串。 
//
// 示例 2: 
//
// 输入:
//s = "aa"
//p = "*"
//输出: true
//解释: '*' 可以匹配任意字符串。
// 
//
// 示例 3: 
//
// 输入:
//s = "cb"
//p = "?a"
//输出: false
//解释: '?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
// 
//
// 示例 4: 
//
// 输入:
//s = "adceb"
//p = "*a*b"
//输出: true
//解释: 第一个 '*' 可以匹配空字符串, 第二个 '*' 可以匹配字符串 "dce".
// 
//
// 示例 5: 
//
// 输入:
//s = "acdcb"
//p = "a*c?b"
//输出: false 
// Related Topics 贪心 递归 字符串 动态规划 
// 👍 719 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution_44 {

    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        char[] arr1 = s.toCharArray();
        char[] arr2 = p.toCharArray();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 0; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (arr2[j - 1] == '*') {
                    // 匹配0个或多个
                    dp[i][j] = dp[i][j - 1] || (i > 0 ? dp[i - 1][j] : false);
                } else {
                    if (i > 0 && (arr2[j - 1] == '?' || arr2[j - 1] == arr1[i - 1])) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                }

            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        System.out.println(new Solution_44().isMatch("aa", "a"));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
