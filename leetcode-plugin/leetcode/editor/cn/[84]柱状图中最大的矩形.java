package leetcode.editor.cn;//给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
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
// Related Topics 栈 数组 单调栈 
// 👍 1448 👎 0


import java.util.ArrayDeque;
import java.util.Deque;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution_84 {

    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int max = 0;
        // 存储索引，栈里面放越来越小的高度
        Deque<Integer> deque = new ArrayDeque<>(n);
        // 最后一个元素可以通过i-(-1)计算宽度
        deque.offerFirst(-1);
        // 右边界
        for (int i = 0; i < n; i++) {
            // 等于的时候，前面的该计算了，再往后高度会越来越小，为什么要等于，因为等于也要入栈
            while (deque.peekFirst() != -1 && heights[deque.peekFirst()] >= heights[i]) {
                // 每次循环都要计算，从当前位置到前面的位置重叠计算
                // 注意从i-1开始减，因为deque.peekFirst+1到i-1(不计算i)之间的都满足同一个最小高度（该范围的高度都大上一个弹出元素）
                max = Math.max(max, heights[deque.pollFirst()] * (i - deque.peekFirst() - 1));
            }
            // 将当前索引加入栈，先不计算，栈内可能有比自己更高的元素
            deque.offerFirst(i);
        }
        // 可能剩余元素没算完
        while (deque.peekFirst() != -1) {
            // 这里右端点是n，因为直到最后都大于栈顶元素高度
            max = Math.max(max, heights[deque.pollFirst()] * (n - deque.peekFirst() - 1));
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new Solution_84().largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3}));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
