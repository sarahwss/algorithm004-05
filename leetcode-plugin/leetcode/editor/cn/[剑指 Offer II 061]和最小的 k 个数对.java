package leetcode.editor.cn;//给定两个以升序排列的整数数组 nums1 和 nums2 , 以及一个整数 k 。
//
// 定义一对值 (u,v)，其中第一个元素来自 nums1，第二个元素来自 nums2 。 
//
// 请找到和最小的 k 个数对 (u1,v1), (u2,v2) ... (uk,vk) 。 
//
// 
//
// 示例 1: 
//
// 
//输入: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
//输出: [1,2],[1,4],[1,6]
//解释: 返回序列中的前 3 对数：
//    [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
// 
//
// 示例 2: 
//
// 
//输入: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
//输出: [1,1],[1,1]
//解释: 返回序列中的前 2 对数：
//     [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
// 
//
// 示例 3: 
//
// 
//输入: nums1 = [1,2], nums2 = [3], k = 3 
//输出: [1,3],[2,3]
//解释: 也可能序列中所有的数对都被返回:[1,3],[2,3]
// 
//
// 
//
// 提示: 
//
// 
// 1 <= nums1.length, nums2.length <= 104 
// -109 <= nums1[i], nums2[i] <= 109 
// nums1, nums2 均为升序排列 
// 1 <= k <= 1000 
// 
//
// 
//
// 注意：本题与主站 373 题相同：https://leetcode-cn.com/problems/find-k-pairs-with-smallest-
//sums/ 
// Related Topics 数组 堆（优先队列） 
// 👍 3 👎 0


import com.sun.corba.se.impl.activation.ProcessMonitorThread;

import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution_剑指061_1 {

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        // 使用最小堆
        // TODO 最小堆
        return Collections.emptyList();
    }
}

class Solution_剑指061 {

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        // 使用最大堆
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((e1, e2) -> e2[0] + e2[1] - e1[0] - e1[1]);
        int m = Math.min(k, nums1.length);
        int n = Math.min(k, nums2.length);
        for (int i = 0; i < m; i++) {
            int num1 = nums1[i];
            for (int j = 0; j < n; j++) {
                int num2 = nums2[j];
                if (priorityQueue.size() < k) {
                    priorityQueue.offer(new int[]{num1, num2});
                } else {
                    int[] max = priorityQueue.peek();
                    if (max[0] + max[1] > num1 + num2) {
                        priorityQueue.poll();
                        priorityQueue.offer(new int[]{num1, num2});
                    }
                }
            }
        }
        List<List<Integer>> res = new ArrayList<>();
        for (int[] e : priorityQueue) {
            res.add(Arrays.asList(e[0], e[1]));
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
