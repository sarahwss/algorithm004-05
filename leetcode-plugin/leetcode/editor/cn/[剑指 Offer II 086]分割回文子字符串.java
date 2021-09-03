package leetcode.editor.cn;//给定一个字符串 s ，请将 s 分割成一些子串，使每个子串都是 回文串 ，返回 s 所有可能的分割方案。
//
// 回文串 是正着读和反着读都一样的字符串。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "google"
//输出：[["g","o","o","g","l","e"],["g","oo","g","l","e"],["goog","l","e"]]
// 
//
// 示例 2： 
//
// 
//输入：s = "aab"
//输出：[["a","a","b"],["aa","b"]]
// 
//
// 示例 3： 
//
// 
//输入：s = "a"
//输出：[["a"]  
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 16 
// s 仅由小写英文字母组成 
// 
//
// 
//
// 注意：本题与主站 131 题相同： https://leetcode-cn.com/problems/palindrome-partitioning/ 
// Related Topics 字符串 动态规划 回溯 
// 👍 3 👎 0


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution_剑指_086 {

    public String[][] partition(String s) {

        List<LinkedList<String>> res = new ArrayList();
        partition(s, 0, new LinkedList<>(), res);
        String[][] r = new String[res.size()][];
        for (int i = 0; i < res.size(); i++) {
            List<String> list = res.get(i);
            r[i] = list.toArray(new String[list.size()]);
        }
        return r;

    }

    private void partition(String s, int i, LinkedList<String> list, List<LinkedList<String>> res) {
        if (i == s.length()) {
            res.add(new LinkedList(list));
            return;
        }
        for (int j = i; j < s.length(); j++) {
            if (isPalindrome(s, i, j)) {
                list.add(s.substring(i, j + 1));
                // 注意这里是j+1，不是I=1
                partition(s, j + 1, list, res);
                list.removeLast();
            }
        }

    }

    private boolean isPalindrome(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i++) != s.charAt(j--)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new Solution_剑指_086().partition("google"));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
