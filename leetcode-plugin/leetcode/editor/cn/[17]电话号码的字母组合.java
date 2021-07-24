package leetcode.editor.cn;//给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
//
// 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。 
//
// 
//
// 
//
// 示例 1： 
//
// 
//输入：digits = "23"
//输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
// 
//
// 示例 2： 
//
// 
//输入：digits = ""
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：digits = "2"
//输出：["a","b","c"]
// 
//
// 
//
// 提示： 
//
// 
// 0 <= digits.length <= 4 
// digits[i] 是范围 ['2', '9'] 的一个数字。 
// 
// Related Topics 哈希表 字符串 回溯 
// 👍 1409 👎 0


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution_17 {

    public List<String> letterCombinations(String digits) {
        // 这个判断不要少
        if (digits.length() == 0) {
            return Collections.emptyList();
        }
        char[][] map = new char[10][];
        char c = 'a';
        for (int i = 2; i < 10; i++) {
            // 注意数组要提前建好
            map[i] = new char[4];
            for (int j = 0; j < 3; j++) {
                map[i][j] = c++;
            }
            if (i == 7 || i == 9) {
                map[i][3] = c++;
            }
        }
        List<String> res = new ArrayList<>();
        char[] arr = digits.toCharArray();
        // 这里不用循环
        letterCombinations(arr, res, 0, new StringBuffer(), map);
        return res;
    }

    // 使用StringBuffer比String快很多，此时可以回溯
    void letterCombinations(char[] arr, List<String> res, int i, StringBuffer sb, char[][] map) {
        if (i == arr.length) {
            res.add(sb.toString());
            return;
        }
        char[] dict = map[arr[i] - '0'];
        // 注意这里要判断是否是0
        for (int j = 0; j < dict.length && dict[j] != 0; j++) {
            sb.append(dict[j]);
            letterCombinations(arr, res, i + 1, sb, map);
            sb.deleteCharAt(i);
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution_17().letterCombinations("23"));
    }

}
//leetcode submit region end(Prohibit modification and deletion)
