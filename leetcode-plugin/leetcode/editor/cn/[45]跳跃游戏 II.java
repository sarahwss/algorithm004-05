package leetcode.editor.cn;//给你一个非负整数数组 nums ，你最初位于数组的第一个位置。
//
// 数组中的每个元素代表你在该位置可以跳跃的最大长度。 
//
// 你的目标是使用最少的跳跃次数到达数组的最后一个位置。 
//
// 假设你总是可以到达数组的最后一个位置。 
//
// 
//
// 示例 1: 
//
// 
//输入: nums = [2,3,1,1,4]
//输出: 2
//解释: 跳到最后一个位置的最小跳跃数是 2。
//     从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
// 
//
// 示例 2: 
//
// 
//输入: nums = [2,3,0,1,4]
//输出: 2
// 
//
// 
//
// 提示: 
//
// 
// 1 <= nums.length <= 104 
// 0 <= nums[i] <= 1000 
// 
// Related Topics 贪心 数组 动态规划 
// 👍 1073 👎 0


import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import javax.swing.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution_45 {

    public int jump(int[] nums) {
        int n = nums.length;
        int step = 0;
        int last = 0;
        int furthest = 0;
        // 每次都尽量跳最远
        // 跳到最后一维，最后一位不要在循环中
        for (int i = 0; i < n - 1; i++) {
            // 从上一步能跳到当前步
            // 不需要判断上一步是否能到当前步，直到i==last之前，i都是是在furthest之前的
            furthest = Math.max(furthest, i + nums[i]);
            // 当前步已经达到上一步的最远，该加步数了
            if (i == last) {
                step++;
                last = furthest;
            }
        }
        return step;

    }
}
//leetcode submit region end(Prohibit modification and deletion)
