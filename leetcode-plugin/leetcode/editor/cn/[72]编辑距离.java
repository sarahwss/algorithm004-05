package leetcode.editor.cn;//给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
//
// 你可以对一个单词进行如下三种操作： 
//
// 
// 插入一个字符 
// 删除一个字符 
// 替换一个字符 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：word1 = "horse", word2 = "ros"
//输出：3
//解释：
//horse -> rorse (将 'h' 替换为 'r')
//rorse -> rose (删除 'r')
//rose -> ros (删除 'e')
// 
//
// 示例 2： 
//
// 
//输入：word1 = "intention", word2 = "execution"
//输出：5
//解释：
//intention -> inention (删除 't')
//inention -> enention (将 'i' 替换为 'e')
//enention -> exention (将 'n' 替换为 'x')
//exention -> exection (将 'n' 替换为 'c')
//exection -> execution (插入 'u')
// 
//
// 
//
// 提示： 
//
// 
// 0 <= word1.length, word2.length <= 500 
// word1 和 word2 由小写英文字母组成 
// 
// Related Topics 字符串 动态规划 
// 👍 1691 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution_72_1 {

    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        char[] arr1 = word1.toCharArray();
        char[] arr2 = word2.toCharArray();
        // 从word1的前i个字符转换到word2的前j个字符
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < m + 1; i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i <= n; i++) {
            dp[0][i] = i;
        }
        for (int i = 1; i < m + 1; i++) {
            char c = arr1[i - 1];
            for (int j = 1; j < n + 1; j++) {
                if (c != arr2[j - 1]) {
                    // 修改最后一个字符
                    // 删除i最后一个字符
                    // 删除j最后一个字符，相当于增加i最后一个字符
                    // 别忘了加1
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                } else {
                    // 最后一个字符不需要修改，[i-1][j和[j-1][i]包含[i-1][j-1]，且结果还得加一，所以最小值只考虑dp[i - 1][j - 1]
                    dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        System.out.println(new Solution_72_1().minDistance("horse", "ros"));
    }
}

class Solution_72 {

    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        // 从word1前i个字符变成word2前j个字符的最小部署
        int dp[][] = new int[m + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            dp[0][i] = i;
        }
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // 最后一个字符相同，不用改，这个很重要，不要忘记
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // 删（删除i里一个字符）、增（删除j里一个字符，最后i和j再加回来）、改（将i里最后一个字符改成j里最后一个字符）
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                }
                //                System.out.println(i + " " + j + " " + dp[i][j]);
            }
        }
        return dp[m][n];

    }

    public static void main(String[] args) {
        System.out.println(new Solution_72().minDistance("horse", "ros"));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
