package leetcode.editor.cn;//给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
//
// 
// '.' 匹配任意单个字符 
// '*' 匹配零个或多个前面的那一个元素 
// 
//
// 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。 
// 
//
// 示例 1： 
//
// 
//输入：s = "aa" p = "a"
//输出：false
//解释："a" 无法匹配 "aa" 整个字符串。
// 
//
// 示例 2: 
//
// 
//输入：s = "aa" p = "a*"
//输出：true
//解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
// 
//
// 示例 3： 
//
// 
//输入：s = "ab" p = ".*"
//输出：true
//解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
// 
//
// 示例 4： 
//
// 
//输入：s = "aab" p = "c*a*b"
//输出：true
//解释：因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
// 
//
// 示例 5： 
//
// 
//输入：s = "mississippi" p = "mis*is*p*."
//输出：false 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length <= 20 
// 0 <= p.length <= 30 
// s 可能为空，且只包含从 a-z 的小写字母。 
// p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。 
// 保证每次出现字符 * 时，前面都匹配到有效的字符 
// 
// Related Topics 递归 字符串 动态规划 
// 👍 2247 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution_10_1 {

    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        char[] arr1 = s.toCharArray();
        char[] arr2 = p.toCharArray();
        // 前i个字符串是否满足前j个正则
        boolean[][] dp = new boolean[m + 1][n + 1];
        // 别少了初始值
        dp[0][0] = true;
        // 从第0行开始，因为第0的初始值不完全，存在i=0仍然匹配的情况，如p==a*
        for (int i = 0; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char pattern = arr2[j - 1];
                // z*，必须和后面的*一起匹配
                if (pattern == '*') {
                    // 本次匹配0个，写出来，不管是否能匹配都考虑匹配0个的情况，不只在不能匹配的情况
                    dp[i][j] = dp[i][j - 2];
                    if (matches(arr1, arr2, i, j - 1)) {
                        // i处字符匹配的话，i-1处的字符也可以匹配j处的，因为可以多次匹配，为dp[i - 1][j]（这里面包含i-1匹配0个的情况）
                        dp[i][j] = dp[i][j] || dp[i - 1][j];
                    }
                } else {  // 单个匹配
                    if (matches(arr1, arr2, i, j)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                }
            }
        }
        return dp[m][n];
    }

    boolean matches(char[] arr1, char[] arr2, int i, int j) {
        if (i == 0) {
            return false;
        }
        return arr2[j - 1] == '.' || arr1[i - 1] == arr2[j - 1];
    }

    public static void main(String[] args) {
        System.out.println(new Solution_10_1().isMatch("aa", "a"));
    }
}

class Solution_10 {

    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        // pattern,从后往前匹配，因为可能出现a*a这样类似的模式，先确保后面的能匹配上
        int j = m - 1;
        for (int i = n - 1; i >= 0; i--) {
            if (match(s, p, 0)) {
                return true;
            }
        }
        return false;
    }

    boolean match(String s, String p, int i) {
        if (i == s.length() && p.isEmpty()) {
            return true;
        }
        if (p.isEmpty()) { // 多余字符，p不为空串时不一定为false，可能是a*b*的情况
            return false;
        }
        char pattern = p.charAt(0);
        if (p.length() > 1 && p.charAt(1) == '*') { // a*匹配0个，p多余时要走此情况
            if (match(s, p.length() > 2 ? p.substring(2) : "", i)) {
                return true;
            }
        }
        if (i == s.length()) { // !p.isEmpty()，多余正则
            return false;
        }
        char c = s.charAt(i);
        if (pattern == '.' || c == pattern) {// 匹配上
            if (p.length() > 1 && p.charAt(1) == '*') {// a*匹配第一个
                if (match(s, p, i + 1)) { // 当p开头是.时，p依然不变化，可匹配任意字符
                    return true;
                }
            } else { // 匹配单个
                if (match(s, p.length() > 1 ? p.substring(1) : "", i + 1)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new Solution_10().isMatch("ab", ".*"));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
