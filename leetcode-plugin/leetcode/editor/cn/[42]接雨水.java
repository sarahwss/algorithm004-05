package leetcode.editor.cn;//给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
//输出：6
//解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 
// 
//
// 示例 2： 
//
// 
//输入：height = [4,2,0,3,2,5]
//输出：9
// 
//
// 
//
// 提示： 
//
// 
// n == height.length 
// 0 <= n <= 3 * 104 
// 0 <= height[i] <= 105 
// 
// Related Topics 栈 数组 双指针 动态规划 单调栈 
// 👍 2511 👎 0


import java.util.ArrayDeque;
import java.util.Deque;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution_42 {

    public int trap(int[] height) {
        int n = height.length;
        Deque<Integer> deque = new ArrayDeque<>(n);
        int res = 0;
        for (int r = 0; r < n; r++) {
            int h = height[r];
            // 等于也弹出，因为需要严格递减的栈元素,注意这里peek之后要计算height
            while (!deque.isEmpty() && height[deque.peekFirst()] <= h) {
                int bottom = deque.pollFirst();
                Integer l = deque.peekFirst();
                // 注意这里要判断一下
                if (l != null) {
                    // 注意这里都要计算height
                    res += (Math.min(height[l], h) - height[bottom]) * (r - l - 1);
                }
            }
            // 前面都是高于自己的柱子
            deque.offerFirst(r);
        }
        // 可能剩余严格递减的柱子，不用管
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
