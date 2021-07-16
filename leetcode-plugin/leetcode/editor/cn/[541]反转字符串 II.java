package leetcode.editor.cn;//给定一个字符串 s 和一个整数 k，你需要对从字符串开头算起的每隔 2k 个字符的前 k 个字符进行反转。
//
// 
// 如果剩余字符少于 k 个，则将剩余字符全部反转。 
// 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。 
// 
//
// 
//
// 示例: 
//
// 输入: s = "abcdefg", k = 2
//输出: "bacdfeg"
// 
//
// 
//
// 提示： 
//
// 
// 该字符串只包含小写英文字母。 
// 给定字符串的长度和 k 在 [1, 10000] 范围内。 
// 
// Related Topics 双指针 字符串 
// 👍 130 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution_54 {

    public String reverseStr(String s, int k) {
        int n = s.length();
        char[] arr = s.toCharArray();
        // 起点
        for (int i = 0; i < n; i += 2 * k) {
            int len = Math.min(k, n - i);
            int l = (len + 1) / 2;
            for (int j = 0; j < l; j++) {
                char temp = arr[i + j];
                arr[i + j] = arr[i + len - 1 - j];
                arr[i + len - 1 - j] = temp;
            }
        }
        return new String(arr);

    }
}
//leetcode submit region end(Prohibit modification and deletion)
