package leetcode.editor.cn;//一条基因序列由一个带有8个字符的字符串表示，其中每个字符都属于 "A", "C", "G", "T"中的任意一个。
//
// 假设我们要调查一个基因序列的变化。一次基因变化意味着这个基因序列中的一个字符发生了变化。 
//
// 例如，基因序列由"AACCGGTT" 变化至 "AACCGGTA" 即发生了一次基因变化。 
//
// 与此同时，每一次基因变化的结果，都需要是一个合法的基因串，即该结果属于一个基因库。 
//
// 现在给定3个参数 — start, end, bank，分别代表起始基因序列，目标基因序列及基因库，请找出能够使起始基因序列变化为目标基因序列所需的最少变
//化次数。如果无法实现目标变化，请返回 -1。 
//
// 注意： 
//
// 
// 起始基因序列默认是合法的，但是它并不一定会出现在基因库中。 
// 如果一个起始基因序列需要多次变化，那么它每一次变化之后的基因序列都必须是合法的。 
// 假定起始基因序列与目标基因序列是不一样的。 
// 
//
// 
//
// 示例 1： 
//
// 
//start: "AACCGGTT"
//end:   "AACCGGTA"
//bank: ["AACCGGTA"]
//
//返回值: 1
// 
//
// 示例 2： 
//
// 
//start: "AACCGGTT"
//end:   "AAACGGTA"
//bank: ["AACCGGTA", "AACCGCTA", "AAACGGTA"]
//
//返回值: 2
// 
//
// 示例 3： 
//
// 
//start: "AAAAACCC"
//end:   "AACCCCCC"
//bank: ["AAAACCCC", "AAACCCCC", "AACCCCCC"]
//
//返回值: 3
// 
// Related Topics 广度优先搜索 哈希表 字符串 
// 👍 80 👎 0


import com.sun.org.apache.bcel.internal.generic.FADD;

import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution_433 {

    public int minMutation(String start, String end, String[] bank) {
        int n = bank.length;
        boolean flag = false;
        Set<String> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.add(bank[i]);
        }
        if (!set.contains(end)) {
            return -1;
        }
        int l = start.length();
        Deque<String> q1 = new ArrayDeque<>();
        Deque<String> q2 = new ArrayDeque<>();
        Map<String, Integer> v1 = new HashMap<>();
        Map<String, Integer> v2 = new HashMap<>();
        q1.offer(start);
        // 变化次数，首字母没有变化
        v1.put(start, 0);
        q2.offer(end);
        v2.put(end, 1);
        char[] genes = new char[]{'A', 'C', 'G', 'T'};
        while (!q1.isEmpty() && !q2.isEmpty()) {
            if (v1.size() > v2.size()) {
                Deque<String> q = q1;
                q1 = q2;
                q2 = q;
                Map<String, Integer> v = v1;
                v1 = v2;
                v2 = v;
            }
            String s = q1.poll();
            char[] arr = s.toCharArray();
            for (int i = 0; i < l; i++) {
                char temp = arr[i];
                for (int j = 0; j < 4; j++) {
                    arr[i] = genes[j];
                    String str = new String(arr);
                    if (set.contains(str)) {
                        Integer count2 = v2.get(str);
                        if (count2 != null) {
                            return v1.get(s) + count2;
                        }
                        if (v1.get(str) == null) {
                            q1.add(str);
                            v1.put(str, v1.get(s) + 1);
                        }
                    }
                }
                arr[i] = temp;
            }
        }
        return -1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
