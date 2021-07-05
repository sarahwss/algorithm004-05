package leetcode.editor.cn;
//给你一个用字符数组 tasks 表示的 CPU 需要执行的任务列表。其中每个字母表示一种不同种类的任务。任务可以以任意顺序执行，并且每个任务都可以在 1 个
//单位时间内执行完。在任何一个单位时间，CPU 可以完成一个任务，或者处于待命状态。 
//
// 然而，两个 相同种类 的任务之间必须有长度为整数 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。 
//
// 你需要计算完成所有任务所需要的 最短时间 。 
//
// 
//
// 示例 1： 
//
// 
//输入：tasks = ["A","A","A","B","B","B"], n = 2
//输出：8
//解释：A -> B -> (待命) -> A -> B -> (待命) -> A -> B
//     在本示例中，两个相同类型任务之间必须间隔长度为 n = 2 的冷却时间，而执行一个任务只需要一个单位时间，所以中间出现了（待命）状态。 
//
// 示例 2： 
//
// 
//输入：tasks = ["A","A","A","B","B","B"], n = 0
//输出：6
//解释：在这种情况下，任何大小为 6 的排列都可以满足要求，因为 n = 0
//["A","A","A","B","B","B"]
//["A","B","A","B","A","B"]
//["B","B","B","A","A","A"]
//...
//诸如此类
// 
//
// 示例 3： 
//
// 
//输入：tasks = ["A","A","A","A","A","A","B","C","D","E","F","G"], n = 2
//输出：16
//解释：一种可能的解决方案是：
//     A -> B -> C -> A -> D -> E -> A -> F -> G -> A -> (待命) -> (待命) -> A -> (待
//命) -> (待命) -> A
// 
//
// 
//
// 提示： 
//
// 
// 1 <= task.length <= 104 
// tasks[i] 是大写英文字母 
// n 的取值范围为 [0, 100] 
// 
// Related Topics 贪心 数组 哈希表 计数 排序 堆（优先队列） 
// 👍 669 👎 0


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution_621 {

    // 不能用递归法算，递归时间复杂度为O(N^K)
    public int leastInterval(char[] tasks, int n) {
        // 元素计数
        int[] count = new int[26];
        for (char task : tasks) {
            count[task - 'A'] += 1;

        }
        // 重复元素最多的次数
        int maxCount = 0;
        for (int i = 0; i < 26; i++) {
            maxCount = Math.max(maxCount, count[i]);
        }
        // 最多重复次数的元素种类数
        int maxCountNum = 0;
        for (int c : count) {
            if (c == maxCount) {
                maxCountNum++;
            }
        }
        // 两种极端：
        // 1.因为冷却时间过长和冷却次数（元素重复多）过多导致时间过长，其他冷却没那么多次的元素可随意穿插在内部
        // 2.冷却时间不长（极端情况不需要冷却），需要冷却的元素不多（极端情况所有元素都不一样），即使处于中间状态，因为不一样的元素多，重复元素可以插入非重复元素中间
        return Math.max((n + 1) * (maxCount - 1) + maxCountNum, tasks.length);
    }


}
//leetcode submit region end(Prohibit modification and deletion)
