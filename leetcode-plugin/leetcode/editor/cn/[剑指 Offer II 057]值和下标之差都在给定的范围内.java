package leetcode.editor.cn;//给你一个整数数组 nums 和两个整数 k 和 t 。请你判断是否存在 两个不同下标 i 和 j，使得 abs(nums[i] - nums[j]) <=
//t ，同时又满足 abs(i - j) <= k 。 
//
// 如果存在则返回 true，不存在返回 false。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,3,1], k = 3, t = 0
//输出：true 
//
// 示例 2： 
//
// 
//输入：nums = [1,0,1,1], k = 1, t = 2
//输出：true 
//
// 示例 3： 
//
// 
//输入：nums = [1,5,9,1,5,9], k = 2, t = 3
//输出：false 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 2 * 104 
// -231 <= nums[i] <= 231 - 1 
// 0 <= k <= 104 
// 0 <= t <= 231 - 1 
// 
//
// 
//
// 注意：本题与主站 220 题相同： https://leetcode-cn.com/problems/contains-duplicate-iii/ 
// Related Topics 数组 桶排序 有序集合 排序 滑动窗口 
// 👍 0 👎 0


import java.util.TreeSet;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution_剑指057_1 {

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        // TODO 区域方式，待完成
        return false;
    }
}

class Solution_剑指057 {

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        // 变成long避免溢出
        TreeSet<Long> treeSet = new TreeSet<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            long num = nums[i];
            // 大于等于给定值的最小建
            Long upper = treeSet.ceiling(num);
            // 别少了值的判断，相加值可能溢出，用减
            // 注意比较的是nums，不是i
            // t很大,计算结果可能溢出
            if (upper != null && upper <= num + t) {
                return true;
            }
            Long lower = treeSet.floor(num);
            // 小于等于给定值的最大建
            // num可能很小，可能溢出
            if (lower != null && lower >= num - t) {
                return true;
            }
            treeSet.add(num);
            // 容器里只保留K个元素
            if (i >= k) {
                // 注意不要用pollFirst，也不要用索引
                // 注意这里要进行类型转换
                treeSet.remove((long) nums[i - k]);
            }
        }
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
