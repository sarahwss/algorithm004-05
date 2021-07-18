package leetcode.editor.cn;//给定一个字符串 S，返回 “反转后的” 字符串，其中不是字母的字符都保留在原地，而所有字母的位置发生反转。
//
// 
//
// 
// 
//
// 示例 1： 
//
// 输入："ab-cd"
//输出："dc-ba"
// 
//
// 示例 2： 
//
// 输入："a-bC-dEf-ghIj"
//输出："j-Ih-gfE-dCba"
// 
//
// 示例 3： 
//
// 输入："Test1ng-Leet=code-Q!"
//输出："Qedo1ct-eeLg=ntse-T!"
// 
//
// 
//
// 提示： 
//
// 
// S.length <= 100 
// 33 <= S[i].ASCIIcode <= 122 
// S 中不包含 \ or " 
// 
// Related Topics 双指针 字符串 
// 👍 81 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution_917 {

    public String reverseOnlyLetters(String s) {
        int n = s.length();
        int i = 0;
        char[] arr = s.toCharArray();
        reverse(arr, 0, n - 1);
        return new String(arr);

    }

    void reverse(char[] arr, int i, int j) {
        while (i < j) {
            while (i < j && !isAlfa(arr[i])) {
                i++;
            }
            while (i < j && !isAlfa(arr[j])) {
                j--;
            }
            // 此处不用判断，最多i==j
            char temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            // 指针移动不要少了
            i++;
            j--;
        }
    }

    boolean isAlfa(char c) {
        return ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'));
    }

    public static void main(String[] args) {
        System.out.println(new Solution_917().reverseOnlyLetters("ab-cd"));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
