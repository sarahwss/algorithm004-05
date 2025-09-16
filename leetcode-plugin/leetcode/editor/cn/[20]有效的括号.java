package leetcode.editor.cn;//给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
//
// 有效字符串需满足： 
//
// 
// 左括号必须用相同类型的右括号闭合。 
// 左括号必须以正确的顺序闭合。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "()"
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：s = "()[]{}"
//输出：true
// 
//
// 示例 3： 
//
// 
//输入：s = "(]"
//输出：false
// 
//
// 示例 4： 
//
// 
//输入：s = "([)]"
//输出：false
// 
//
// 示例 5： 
//
// 
//输入：s = "{[]}"
//输出：true 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 104 
// s 仅由括号 '()[]{}' 组成 
// 
// Related Topics 栈 字符串 
// 👍 2512 👎 0


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution_20 {


    public boolean isValid(String s) {
        char[] map = new char[128];
        map[']'] = '[';
        map[')'] = '(';
        map['}'] = '{';
        int n = s.length();
        // n为奇数，一定不匹配
        if ((n & 1) == 1) {
            return false;
        }
        int left = n / 2;
        Deque<Character> deque = new ArrayDeque<>(n);
        char[] arr = s.toCharArray();
        for (int i = 0; i < n; i++) {
            char c = arr[i];
            // 右括号
            if (map[c] != 0) {
                Character str = deque.pollFirst();
                // 左括号不够，不能直接两个比，得先和Null比
                if (str == null || str != map[c]) {
                    // 这里直接return，不是break
                    return false;
                }
                // 左括号
            } else {
                // 左括号没有超再放
                if (deque.size() >= left) {
                    return false;
                }
                deque.offerFirst(c);
            }
        }
        PriorityQueue p = new PriorityQueue();
        Arrays.sort();

        // 左括号多余
        return deque.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(new Solution_20().isValid("(())"));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
