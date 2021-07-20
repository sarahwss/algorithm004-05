package leetcode.editor.cn;//给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
//
// 异位词 指字母相同，但排列不同的字符串。 
//
// 
//
// 示例 1: 
//
// 
//输入: s = "cbaebabacd", p = "abc"
//输出: [0,6]
//解释:
//起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
//起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
// 
//
// 示例 2: 
//
// 
//输入: s = "abab", p = "ab"
//输出: [0,1,2]
//解释:
//起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
//起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
//起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
// 
//
// 
//
// 提示: 
//
// 
// 1 <= s.length, p.length <= 3 * 104 
// s 和 p 仅包含小写字母 
// 
// Related Topics 哈希表 字符串 滑动窗口 
// 👍 565 👎 0


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution_438 {

    // 滑动窗口
    public List<Integer> findAnagrams(String s, String p) {
        int n = s.length();
        int m = p.length();
        int[] dict = new int[26];
        // 名义是window，事实是计数map，只计dict有关的数
        int[] window = new int[26];
        char[] arr1 = s.toCharArray();
        char[] arr2 = p.toCharArray();
        // count记录p中不同字符数大小，valid记录s中不同字符数大小
        int left = 0, right = 0, valid = 0, count = 0;
        for (int i = 0; i < p.length(); i++) {
            int x = arr2[i] - 'a';
            // 别昂了判断后再计数
            if (dict[x] == 0) {
                count++;
            }
            dict[x]++;
        }
        List<Integer> res = new ArrayList<>();
        while (right < n) {
            int x = arr1[right] - 'a';
            // 别忘了判断后再移入
            if (dict[x] != 0) {
                window[x]++;
                if (dict[x] == window[x]) {
                    valid++;
                }
            }
            right++;
            // 因为right已加一，这里是>m，右移的同时左移，只会左移一次
            if (right - left > m) {
                int y = arr1[left] - 'a';
                // 别忘了判断后再移出
                if (dict[y] != 0) {
                    if (dict[y] == window[y]) {
                        valid--;
                    }
                    window[y]--;
                }
                left++;
            }
            // right - left == m
            if (valid == count) {
                res.add(left);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Solution_438().findAnagrams("baa", "aa"));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
