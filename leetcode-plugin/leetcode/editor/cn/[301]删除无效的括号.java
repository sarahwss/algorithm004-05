//给你一个由若干括号和字母组成的字符串 s ，删除最小数量的无效括号，使得输入的字符串有效。 
//
// 返回所有可能的结果。答案可以按 任意顺序 返回。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "()())()"
//输出：["(())()","()()()"]
// 
//
// 示例 2： 
//
// 
//输入：s = "(a)())()"
//输出：["(a())()","(a)()()"]
// 
//
// 示例 3： 
//
// 
//输入：s = ")("
//输出：[""]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 25 
// s 由小写英文字母以及括号 '(' 和 ')' 组成 
// s 中至多含 20 个括号 
// 
// Related Topics 广度优先搜索 字符串 回溯 👍 819 👎 0


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public List<String> removeInvalidParentheses(String s) {
        int l = 0;
        int r = 0;
        // 统计结果与顺序有关，不能到最后再加减
        for (char c : s.toCharArray()) {
            if (c == '(') {
                l++;
            } else if (c == ')') {// 必须有l>=r
                if (l == 0) {
                    r++;
                } else {
                    l--;
                }
            }
        }
        List<String> res = new ArrayList<>();
        removeInvalidParentheses(s, 0, l, r, res);
        return res;
    }

    /**
     * 删除i个字符
     */
    public void removeInvalidParentheses(String str, int start, int ll, int rr, List<String> res) {
        // 不删除
        if (ll == 0 && rr == 0) {
            if (isValid(str)) {
                res.add(str);
            }
            return;
        }

        // 选出一个删除
        for (int j = start; j < str.length(); j++) {// bisited一定满足
            char c = str.charAt(j);
            // 字符串不用删除
            if (Character.isLetter(c)) {
                continue;
            }
            String ss = str.substring(0, j) + str.substring(j + 1);
            // 需要删除的字符多于剩余字符
            if (ll + rr > str.length() - start) {
                return;
            }
            // 删除一个右括号
            if (rr > 0 && c == ')') {
                removeInvalidParentheses(ss, j, ll, rr - 1, res);
            }
            // 删除一个左括号
            if (ll > 0 && c == '(') {
                removeInvalidParentheses(ss, j, ll - 1, rr, res);
            }
            // 跳过重复字符
            while (j + 1 < str.length() && str.charAt(j + 1) == c) {
                j++;
            }

        }

    }

    private boolean isValid(String str) {
        int cnt = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '(') {
                cnt++;
            } else if (c == ')') {
                cnt--;
                if (cnt < 0) {
                    return false;
                }
            }

        }
        return cnt == 0;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
