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
//(上箭头符号 ^ 表示选取的字母)
//rabbbit
//^^^^ ^^
//rabbbit
//^^ ^^^^
//rabbbit
//^^^ ^^^
// 
//
// 示例 2： 
//
// 
//输入：s = "babgbag", t = "bag"
//输出：5
//解释：
//如下图所示, 有 5 种可以从 s 中得到 "bag" 的方案。 
//(上箭头符号 ^ 表示选取的字母)
//babgbag
//^^ ^
//babgbag
//^^    ^
//babgbag
//^    ^^
//babgbag
//  ^  ^^
//babgbag
//    ^^^ 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length, t.length <= 1000 
// s 和 t 由英文字母组成 
// 
// Related Topics 字符串 动态规划 
// 👍 552 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution_115 {

    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();
        // 减少无用循环
        if (m == n && !s.equals(t)) {
            return 0;
        }
        // 以s第i个字符为结尾的，长度为j+1的字符串的方法数
        int[][] dp = new int[m][n];
        int sum = 0;
        // 以当前元素为结尾的，长度为j+1的字符串方法数
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) {
                if (s.charAt(i) == t.charAt(j)) {
                    if (j == 0) {
                        dp[i][j] = 1;
                    } else {
                        // 以pre为结尾的，长度为j的字符串方法数
                        for (int k = 0; k < i; k++) {
                            dp[i][j] += dp[k][j - 1];
                        }
                    }
                    // 放在外面，0也可能是n-1
                    if (j == n - 1) {
                        sum += dp[i][j];
                    }
                }
            }
        }
        //        for (int i = 0; i < m; i++) {
        //            System.out.println(Arrays.toString(dp[i]));
        //        }
        return sum;
    }


    public static void main(String[] args) {
        System.out.println(new Solution_115().numDistinct("b", "a"));
    }
}


class Solution_115_1 {

    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();
        // 子字符串长度一定小于等于源字符串，这里确保m>=n
        if (m < n) {
            return 0;
        }
        // s第i个字符到结尾的字符串，t第j个字符到结尾的字符串，前者子字符串中后者出现的次数
        int[][] dp = new int[m + 1][n + 1];
        // 空字符串是所有字符串的子串
        for (int i = 0; i <= m; i++) {
            dp[i][n] = 1;
        }
        // 任何字符串都不是空字符串的子串
        //        for (int j = 0; j < n; j++) {
        //            dp[m][j] = 0;
        //        }
        for (int i = m - 1; i >= 0; i--) {
            char c = s.charAt(i);
            for (int j = n - 1; j >= 0; j--) {
                // 当前字符一致，可以有两种匹配方式
                // 注意这里索引要减1
                if (c == t.charAt(j)) {
                    // 当前字符是否用在后半段，不用的话相当于当前字符不存在，将其后面的部分与t进行匹配，当前字符可以在前半段继续使用
                    // 只有在i<j的情况下，s{i+1:}的长度才能大于等于t{j:}的长度，才可能有匹配方法数
                    // 这里为什么不用[i][j+1]？因为从后往前遍历，重点是当前位置是否和t的后半段匹配，不是的话，不能把当前位置包含进去，否则会重复
                    dp[i][j] = dp[i + 1][j + 1] + dp[i + 1][j];
                    // 字符不一致，只能丢掉当前字符进行匹配。
                } else {
                    dp[i][j] = dp[i + 1][j];
                }
            }

        }
        //        for (int i = 0; i < m; i++) {
        //            System.out.println(Arrays.toString(dp[i]));
        //        }
        return dp[0][0];
    }


    public static void main(String[] args) {
        System.out.println(new Solution_115_1().numDistinct("rabbbit", "rabbit"));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
