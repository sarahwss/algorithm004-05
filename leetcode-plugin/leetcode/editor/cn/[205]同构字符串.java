package leetcode.editor.cn;//给定两个字符串 s 和 t，判断它们是否是同构的。
//
// 如果 s 中的字符可以按某种映射关系替换得到 t ，那么这两个字符串是同构的。 
//
// 每个出现的字符都应当映射到另一个字符，同时不改变字符的顺序。不同字符不能映射到同一个字符上，相同字符只能映射到同一个字符上，字符可以映射到自己本身。 
//
// 
//
// 示例 1: 
//
// 
//输入：s = "egg", t = "add"
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：s = "foo", t = "bar"
//输出：false 
//
// 示例 3： 
//
// 
//输入：s = "paper", t = "title"
//输出：true 
//
// 
//
// 提示： 
//
// 
// 可以假设 s 和 t 长度相同。 
// 
// Related Topics 哈希表 字符串 
// 👍 369 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution_205 {

    public boolean isIsomorphic(String s, String t) {
        int n = s.length();
        char[] arr1 = s.toCharArray();
        char[] arr2 = t.toCharArray();
        // 存放字符和字符的映射，s到t的映射
        int[] map1 = new int[128];
        int[] map2 = new int[128];
        Arrays.fill(map1, -1);
        Arrays.fill(map2, -1);
        for (int i = 0; i < n; i++) {
            // 不判断是否已有，优先映射最后的，则下一个循环会将前面映射有问题的提前找出来
            map1[arr1[i]] = arr2[i];
        }
        for (int i = 0; i < n; i++) {
            int right = arr2[i];
            int left = map2[right];
            if (left == -1) {
                left = arr1[i];
                //，和左map冲突，不相等因为左边map一个映射多个，取最后一个映射
                if (map1[left] != right) {
                    return false;
                }
            } else {
                // 和自己冲突，不相等因为右边一个映射多个，取第一个映射
                if (left != arr1[i]) {
                    return false;
                }
            }
            map2[right] = left;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new Solution_205().isIsomorphic("ege", "add"));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
