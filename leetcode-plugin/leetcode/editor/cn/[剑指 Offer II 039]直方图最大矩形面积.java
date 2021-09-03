package leetcode.editor.cn;//给定非负整数数组 heights ，数组中的数字用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
//
// 求在该柱状图中，能够勾勒出来的矩形的最大面积。 
//
// 
//
// 示例 1: 
//
// 
//
// 
//输入：heights = [2,1,5,6,2,3]
//输出：10
//解释：最大的矩形为图中红色区域，面积为 10
// 
//
// 示例 2： 
//
// 
//
// 
//输入： heights = [2,4]
//输出： 4 
//
// 
//
// 提示： 
//
// 
// 1 <= heights.length <=105 
// 0 <= heights[i] <= 104 
// 
//
// 
//
// 注意：本题与主站 84 题相同： https://leetcode-cn.com/problems/largest-rectangle-in-histog
//ram/ 
// Related Topics 栈 数组 单调栈 
// 👍 2 👎 0


import java.util.ArrayDeque;
import java.util.Deque;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution_剑指039 {

    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        // 构造单调递增栈
        Deque<Integer> deque = new ArrayDeque<>(n);
        deque.offerFirst(-1);
        int maxArea = 0;
        for (int i = 0; i < n; i++) {
            int h = heights[i];
            while (deque.peekFirst() != -1 && heights[deque.peekFirst()] >= h) {
                // 注意这里高度不要少了从heights取
                maxArea = Math.max(maxArea, heights[deque.pollFirst()] * (i - deque.peekFirst() - 1));
            }
            deque.offerFirst(i);
        }
        while (deque.peekFirst() != -1) {
            maxArea = Math.max(maxArea, heights[deque.pollFirst()] * ((n - deque.peekFirst() - 1)));
        }
        return maxArea;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
