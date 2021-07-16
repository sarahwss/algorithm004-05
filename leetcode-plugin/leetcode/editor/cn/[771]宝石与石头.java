package leetcode.editor.cn;//给定字符串J 代表石头中宝石的类型，和字符串 S代表你拥有的石头。 S 中每个字符代表了一种你拥有的石头的类型，你想知道你拥有的石头中有多少是宝石。
//
// J 中的字母不重复，J 和 S中的所有字符都是字母。字母区分大小写，因此"a"和"A"是不同类型的石头。 
//
// 示例 1: 
//
// 输入: J = "aA", S = "aAAbbbb"
//输出: 3
// 
//
// 示例 2: 
//
// 输入: J = "z", S = "ZZ"
//输出: 0
// 
//
// 注意: 
//
// 
// S 和 J 最多含有50个字母。 
// J 中的字符不重复。 
// 
// Related Topics 哈希表 字符串 
// 👍 653 👎 0


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution_771_2 {

    public int numJewelsInStones(String jewels, String stones) {
        int m = jewels.length();
        int n = stones.length();
        // A码值65,z码值 122
        int[] j = new int[58];
        for (int i = 0; i < m; i++) {
            j[jewels.charAt(i) - 'A'] = 1;
        }
        int count = 0;
        for (int i = 0; i < n; i++) {
            count += j[stones.charAt(i) - 'A'];
        }
        return count;
    }
}

class Solution_771_1 {

    public int numJewelsInStones(String jewels, String stones) {
        int m = jewels.length();
        int n = stones.length();
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < m; i++) {
            set.add(jewels.charAt(i));
        }
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (set.contains(stones.charAt(i))) {
                count++;
            }
        }
        return count;
    }
}

class Solution_771 {

    public int numJewelsInStones(String jewels, String stones) {
        int m = jewels.length();
        int n = stones.length();
        char[] a1 = jewels.toCharArray();
        char[] a2 = stones.toCharArray();
        Arrays.sort(a1);
        Arrays.sort(a2);
        System.out.println(Arrays.toString(a1));
        System.out.println(Arrays.toString(a2));
        int count = 0;
        int i = 0;
        int j = 0;
        for (; i < m; i++) {
            char jewel = a1[i];
            while (j < n && jewel > a2[j]) {
                j++;
            }
            while (j < n && jewel == a2[j]) {
                count++;
                j++;
            }
        }
        return count;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
