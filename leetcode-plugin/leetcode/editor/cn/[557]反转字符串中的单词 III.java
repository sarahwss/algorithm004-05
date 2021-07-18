package leetcode.editor.cn;//给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
//
// 
//
// 示例： 
//
// 输入："Let's take LeetCode contest"
//输出："s'teL ekat edoCteeL tsetnoc"
// 
//
// 
//
// 提示： 
//
// 
// 在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。 
// 
// Related Topics 双指针 字符串 
// 👍 305 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution_557 {

    public String reverseWords(String s) {
        int n = s.length();
        char[] arr = s.toCharArray();
        int i = 0;
        while (i < n) {
            int j = i;
            while (j < n && arr[j] != ' ') {
                j++;
            }
            reverse(arr, i, j - 1);
            // 空格只有一个，不用训循环，从空格位的下一个词开始
            i = j + 1;
        }
        return new String(arr);

    }

    // 注意这种翻转写法
    void reverse(char[] arr, int i, int j) {
        while (i < j) {
            char temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution_557().reverseWords("Let's take LeetCode contest"));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
