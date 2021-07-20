package leetcode.editor.cn;//给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。
//
// 
//
// 示例 1: 
//
// 
//输入: s = "aba"
//输出: true
// 
//
// 示例 2: 
//
// 
//输入: s = "abca"
//输出: true
//解释: 你可以删除c字符。
// 
//
// 示例 3: 
//
// 
//输入: s = "abc"
//输出: false 
//
// 
//
// 提示: 
//
// 
// 1 <= s.length <= 105 
// s 由小写英文字母组成 
// 
// Related Topics 贪心 双指针 字符串 
// 👍 371 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution_80 {

    public boolean validPalindrome(String s) {
        int n = s.length();
        if (n == 1) {
            return true;
        }
        char[] arr = s.toCharArray();
        return validPalindrome(arr, false, 0, n - 1);

    }

    boolean validPalindrome(char[] arr, boolean deleted, int i, int j) {
        while (i < j) {
            if (arr[i] != arr[j]) {
                if (deleted) {
                    return false;
                } else if (j - 1 >= i && arr[i] == arr[j - 1] &&
                        (j - 1 - i <= 1 || validPalindrome(arr, true, i + 1, j - 2))) {
                    return true;
                } else if (i + 1 <= j && arr[i + 1] == arr[j] &&
                        (j - i - 1 <= 1 || validPalindrome(arr, true, i + 2, j - 1))) {
                    return true;
                } else {
                    return false;
                }
            }
            i++;
            j--;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new Solution_80().validPalindrome("abcda"));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
