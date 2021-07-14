package leetcode.editor.cn;//给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
//
// 
//
// 
// 
// 示例 1： 
//
// 
//输入：s = "(()"
//输出：2
//解释：最长有效括号子串是 "()"
// 
//
// 示例 2： 
//
// 
//输入：s = ")()())"
//输出：4
//解释：最长有效括号子串是 "()()"
// 
//
// 示例 3： 
//
// 
//输入：s = ""
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length <= 3 * 104 
// s[i] 为 '(' 或 ')' 
// 
// 
// 
// Related Topics 栈 字符串 动态规划 
// 👍 1368 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution_32 {

    public int longestValidParentheses(String s) {
        int n = s.length();
        if (n <= 1) {
            return 0;
        }
        int[] dp = new int[n];
        dp[0] = 0;
        int max = 0;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else {
                    int l = dp[i - 1];
                    if (i - l - 1 >= 0 && s.charAt(i - l - 1) == '(') {
                        // 有可能和前面的连起来
                        dp[i] = l + 2 + (i - l - 2 >= 0 ? dp[i - l - 2] : 0);
                    }
                }
                max = Math.max(dp[i], max);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new Solution_32().longestValidParentheses("()(())"));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
