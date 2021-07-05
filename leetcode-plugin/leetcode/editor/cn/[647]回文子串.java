package leetcode.editor.cn;//给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
//
// 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。 
//
// 
//
// 示例 1： 
//
// 输入："abc"
//输出：3
//解释：三个回文子串: "a", "b", "c"
// 
//
// 示例 2： 
//
// 输入："aaa"
//输出：6
//解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa" 
//
// 
//
// 提示： 
//
// 
// 输入的字符串长度不会超过 1000 。 
// 
// Related Topics 字符串 动态规划 
// 👍 617 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution_647 {

    public int countSubstrings(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n + 1][n + 1];
        int count = 0;
        for (int i = 1; i < n + 1; i++) {
            dp[i][i] = true;
            count++;
        }
        // 右
        for (int i = 1; i <= n; i++) {
            char c = s.charAt(i - 1);
            // 左，需要先知道j+1的数据，所以要倒序执行
            for (int j = i-1; j >= 1; j--) {
                //                System.out.println(
                //                        "j: " + j + ",i:" + i + ",s.charAt(j - 1): " + s.charAt(j - 1) + ",s.charAt(i - 1): " + c +
                //                                ",dp[j + 1][i - 1]:" + dp[j + 1][i - 1] + " , i -j == 1:" + (i - j == 1));
                if (s.charAt(j - 1) == c && (dp[j + 1][i - 1]
                        // 不一定存在中间的字符串
                        || i - j == 1)) {
                    dp[j][i] = true;
                    count++;
                }
            }
        }
        //        for (int i = 0; i < n + 1; i++) {
        //            for (int j = 0; j < n + 1; j++) {
        //                System.out.print(dp[i][j] + " ");
        //            }
        //            System.out.println();
        //        }

        return count;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
