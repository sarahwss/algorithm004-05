package leetcode.editor.cn;//数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
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
// Related Topics 字符串 动态规划 回溯 
// 👍 1863 👎 0


import java.util.LinkedList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution_22 {

    public List<String> generateParenthesis(int n) {
        List<String> res = new LinkedList<>();
        DFS(n, 0, "", res);
        return res;


    }

    void DFS(int n, int left, String str, List<String> res) {
        int l = str.length();
        if (str.length() == n * 2) {
            res.add(str);
            return;
        }
        int right = l - left;
        if (left < n) {
            DFS(n, left + 1, str + "(", res);
        }
        if (right < left) {
            DFS(n, left, str + ")", res);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
