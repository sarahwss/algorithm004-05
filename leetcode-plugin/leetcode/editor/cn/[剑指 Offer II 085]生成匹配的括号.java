package leetcode.editor.cn;//正整数 n 代表生成括号的对数，请设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
//
// 
//
// 示例 1： 
//
// 
//输入：n = 3
//输出：["((()))","(()())","(())()","()(())","()()()"]
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：["()"]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 8 
// 
//
// 
//
// 注意：本题与主站 22 题相同： https://leetcode-cn.com/problems/generate-parentheses/ 
// Related Topics 字符串 动态规划 回溯 
// 👍 3 👎 0


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution_剑指_085 {

    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        generateParenthesis(n, 0, 0, 0, new StringBuilder(), res);
        return res;
    }

    private void generateParenthesis(int n, int i, int left, int right, StringBuilder stringBuilder, List<String> res) {
        if (i == n * 2) {
            res.add(stringBuilder.toString());
            return;
        }
        if (left < n) {
            stringBuilder.append('(');
            generateParenthesis(n, i + 1, left + 1, right, stringBuilder, res);
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        if (right < left) {
            stringBuilder.append(")");
            generateParenthesis(n, i + 1, left, right + 1, stringBuilder, res);
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
