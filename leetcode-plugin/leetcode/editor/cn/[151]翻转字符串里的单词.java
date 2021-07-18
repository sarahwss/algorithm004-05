package leetcode.editor.cn;//给你一个字符串 s ，逐个翻转字符串中的所有 单词 。
//
// 单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。 
//
// 请你返回一个翻转 s 中单词顺序并用单个空格相连的字符串。 
//
// 说明： 
//
// 
// 输入字符串 s 可以在前面、后面或者单词间包含多余的空格。 
// 翻转后单词间应当仅用一个空格分隔。 
// 翻转后的字符串中不应包含额外的空格。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "the sky is blue"
//输出："blue is sky the"
// 
//
// 示例 2： 
//
// 
//输入：s = "  hello world  "
//输出："world hello"
//解释：输入字符串可以在前面或者后面包含多余的空格，但是翻转后的字符不能包括。
// 
//
// 示例 3： 
//
// 
//输入：s = "a good   example"
//输出："example good a"
//解释：如果两个单词间有多余的空格，将翻转后单词间的空格减少到只含一个。
// 
//
// 示例 4： 
//
// 
//输入：s = "  Bob    Loves  Alice   "
//输出："Alice Loves Bob"
// 
//
// 示例 5： 
//
// 
//输入：s = "Alice does not even like bob"
//输出："bob like even not does Alice"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 104 
// s 包含英文大小写字母、数字和空格 ' ' 
// s 中 至少存在一个 单词 
// 
//
// 
// 
//
// 
//
// 进阶： 
//
// 
// 请尝试使用 O(1) 额外空间复杂度的原地解法。 
// 
// Related Topics 双指针 字符串 
// 👍 342 👎 0


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)

class Solution_151_1 {

    public String reverseWords(String s) {
        int n = s.length();
        String[] strs = s.split(" ");
        List<String> res = new ArrayList();
        for (int i = strs.length - 1; i >= 0; i--) {
            String str = strs[i];
            if (!str.isEmpty()) {
                res.add(str);
            }
        }
        return String.join(" ", res);
    }

    public static void main(String[] args) {
        System.out.println(new Solution_151_1().reverseWords("the sky is blue"));
        System.out.println(Arrays.toString("a good  example".split(" ")));
    }
}

class Solution_151_2 {

    public String reverseWords(String s) {
        int n = s.length();
        Deque<String> deque = new ArrayDeque<>();
        char arr[] = s.toCharArray();
        int j = -1;
        int i = 0;
        for (; i < n; i++) {
            char c = arr[i];
            if (c != ' ' && j == -1) {
                j = i;
            }
            // 放在下面方便只有一位时继续计算
            if (c == ' ' && j != -1) {
                deque.offerFirst(s.substring(j, i));
                j = -1;
            }
        }
        // 别忘了这一步
        if (j != -1 && j != i) {
            deque.offerFirst(s.substring(j, i));
        }
        return String.join(" ", deque);

    }

    public static void main(String[] args) {
        System.out.println(new Solution_151_2().reverseWords("  blue  "));
    }
}

class Solution_151 {

    public String reverseWords(String s) {
        int n = s.length();
        // 先整体翻转
        char[] arr = s.toCharArray();
        reverse(arr, 0, n - 1);
        // 假装-1位置是上一个空格
        int i = -1;
        while (i < n) {
            // 多余空格的开头，最后变成单词的开头
            // 保存当前i，空格的开头
            int j = ++i;
            // 跳过空格，寻找单词第一个字符，最终停在单词第一个字符处
            while (i < n && arr[i] == ' ') {
                i++;
            }
            // 已经找到末尾,跳出循环，别忘了恢复i，减1是为了去掉多加那个空格
            if (i == n) {
                // 不存在返回-1的可能，至少有一个单词
                i = j - 1;
                break;
            }
            // 去除前面空格，i - j为前面的空格长度，因为j在多余空格的前一个字符
            int space = i - j;
            // 找到单词右端，最后i位于最后一个字符的原来位置
            while (i < n && arr[i] != ' ') {
                // 因为空格往前移动单词，移动前j处是空格
                arr[i - space] = arr[i];
                i++;
            }
            // i在字符串后的空格处
            i = i - space;
            // 翻转单词
            reverse(arr, j, i - 1);
            // 空格移到后面，注意这里是space-1不是space
            while (i + space - 1 < n && space - 1 >= 0) {
                arr[i + space - 1] = ' ';
                space--;
            }

        }
        return new String(Arrays.copyOfRange(arr, 0, i));
    }

    private void reverse(char[] arr, int i, int j) {
        while (i < j) {
            char temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution_151().reverseWords("a good   example"));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
