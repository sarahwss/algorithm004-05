package leetcode.editor.cn;//给你一个字符串 s ，将该字符串中的大写字母转换成相同的小写字母，返回新的字符串。
//
// 
//
// 示例 1： 
//
// 
//输入：s = "Hello"
//输出："hello"
// 
//
// 示例 2： 
//
// 
//输入：s = "here"
//输出："here"
// 
//
// 示例 3： 
//
// 
//输入：s = "LOVELY"
//输出："lovely"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 100 
// s 由 ASCII 字符集中的可打印字符组成 
// 
// Related Topics 字符串 
// 👍 151 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution_709_1 {

    // 大写相对小写的二进制差异：大写的第六位为0，小写的第六位为1，二者第七位都为1，后面5位都从1到26
    // 大小写转换位运算：大小取反：-> ^=32，全变小写-> |=32，全变大写-> &=-32
    public String toLowerCase(String s) {
        int n = s.length();
        char[] arr = s.toCharArray();
        for (int i = 0; i < n; i++) {
            if (arr[i] >= 'A' && arr[i] <= 'Z') {
                arr[i] |= 32;
            }
        }
        return new String(arr);
    }
}

class Solution_709 {

    public String toLowerCase(String s) {
        int d = 'a' - 'A';
        int n = s.length();
        char[] arr = s.toCharArray();
        for (int i = 0; i < n; i++) {
            if (arr[i] >= 'A' && arr[i] <= 'Z') {
                arr[i] += d;
            }
        }
        return new String(arr);

    }
}
//leetcode submit region end(Prohibit modification and deletion)
