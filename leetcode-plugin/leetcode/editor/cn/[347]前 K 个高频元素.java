//给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。 
//
// 
//
// 示例 1: 
//
// 
//输入: nums = [1,1,1,2,2,3], k = 2
//输出: [1,2]
// 
//
// 示例 2: 
//
// 
//输入: nums = [1], k = 1
//输出: [1] 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10⁵ 
// k 的取值范围是 [1, 数组中不相同的元素的个数] 
// 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的 
// 
//
// 
//
// 进阶：你所设计算法的时间复杂度 必须 优于 O(n log n) ，其中 n 是数组大小。 
// Related Topics 数组 哈希表 分治 桶排序 计数 快速选择 排序 堆（优先队列） 👍 1459 👎 0


import sun.jvm.hotspot.opto.HaltNode;

import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> numToCount = new HashMap<>();
        int maxCount = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int count = numToCount.getOrDefault(num, 0) + 1;
            numToCount.put(num, count);
            maxCount = Math.max(maxCount, count);
        }
        TreeMap<Integer, List<Integer>> countToNum = new TreeMap<>();
        for (int n : numToCount.keySet()) {
            countToNum.putIfAbsent(numToCount.get(n), new ArrayList<>());
            countToNum.get(numToCount.get(n)).add(n);
        }


        int[] res = new int[k];
        // i>=与k无关，可能有空列表
        for (Integer key : countToNum.descendingKeySet()) {
            if (k == 0) {
                break;
            }
//            System.out.println(countToNum.get(key));
            for (int num : countToNum.get(key)) {
                res[--k] = num;
            }
        }
        return res;
    }


}
//leetcode submit region end(Prohibit modification and deletion)
