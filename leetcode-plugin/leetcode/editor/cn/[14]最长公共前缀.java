package leetcode.editor.cn;//编写一个函数来查找字符串数组中的最长公共前缀。
//
// 如果不存在公共前缀，返回空字符串 ""。 
//
// 
//
// 示例 1： 
//
// 
//输入：strs = ["flower","flow","flight"]
//输出："fl"
// 
//
// 示例 2： 
//
// 
//输入：strs = ["dog","racecar","car"]
//输出：""
//解释：输入不存在公共前缀。 
//
// 
//
// 提示： 
//
// 
// 0 <= strs.length <= 200 
// 0 <= strs[i].length <= 200 
// strs[i] 仅由小写英文字母组成 
// 
// Related Topics 字符串 
// 👍 1692 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution_14_1 {

    // 横向搜索，一个个字符串收缩公共串长度
    public String longestCommonPrefix(String[] strs) {
        int n = strs.length;
        if (n == 0) {
            return "";
        }
        String common = strs[0];
        // 从第一个字符串开始
        for (int i = 1; i < n; i++) {
            String s = strs[i];
            int l = Math.min(common.length(), s.length());
            int j = 0;
            for (; j < l; j++) {
                if (common.charAt(j) != s.charAt(j)) {
                    break;
                }
            }
            common = common.substring(0, j);
        }
        return common;
    }
}

class Solution_14 {

    // 纵向搜索，一个个长度尝试
    public String longestCommonPrefix(String[] strs) {
        int n = strs.length;
        if (n == 0) {
            return "";
        }
        String first = strs[0];
        int i = 0;
        // 第i个字符
        for (; i < first.length(); i++) {
            char c = first.charAt(i);
            //从1开始，因为0已经符合
            int j = 1;
            for (; j < n; j++) {
                String s = strs[j];
                // 注意这里要判断数组越界，每个字符串长度不同
                if (s.length() < i + 1 || s.charAt(i) != c) {
                    // 直接返回，不需要两次break
                    return first.substring(0, i);
                }
            }
        }
        return first;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
