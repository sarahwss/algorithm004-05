package leetcode.editor.cn;//给你一个字符串 s，由若干单词组成，单词之间用空格隔开。返回字符串中最后一个单词的长度。如果不存在最后一个单词，请返回 0 。
//
// 单词 是指仅由字母组成、不包含任何空格字符的最大子字符串。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "Hello World"
//输出：5
// 
//
// 示例 2： 
//
// 
//输入：s = " "
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 104 
// s 仅有英文字母和空格 ' ' 组成 
// 
// Related Topics 字符串 
// 👍 330 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution_58 {

    public int lengthOfLastWord(String s) {
        int n = s.length();
        // 去除末尾的空字符，注意前面要判断越界
        while (n - 1 >= 0 && s.charAt(n - 1) == ' ') {
            // 注意这里是n--
            n--;
        }
        int i = n - 1;
        for (; i >= 0; i--) {
            // 注意这里是空格，不是空字符
            if (s.charAt(i) == ' ') {
                break;
            }
        }
        return n - 1 - i;
    }

    public static void main(String[] args) {
        System.out.println(new Solution_58().lengthOfLastWord(" "));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
