package leetcode.editor.cn;//给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位
//。 
//
// 返回滑动窗口中的最大值。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
//输出：[3,3,5,5,6,7]
//解释：
//滑动窗口的位置                最大值
//---------------               -----
//[1  3  -1] -3  5  3  6  7       3
// 1 [3  -1  -3] 5  3  6  7       3
// 1  3 [-1  -3  5] 3  6  7       5
// 1  3  -1 [-3  5  3] 6  7       5
// 1  3  -1  -3 [5  3  6] 7       6
// 1  3  -1  -3  5 [3  6  7]      7
// 
//
// 示例 2： 
//
// 
//输入：nums = [1], k = 1
//输出：[1]
// 
//
// 示例 3： 
//
// 
//输入：nums = [1,-1], k = 1
//输出：[1,-1]
// 
//
// 示例 4： 
//
// 
//输入：nums = [9,11], k = 2
//输出：[11]
// 
//
// 示例 5： 
//
// 
//输入：nums = [4,-2], k = 2
//输出：[4] 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 105 
// -104 <= nums[i] <= 104 
// 1 <= k <= nums.length 
// 
// Related Topics 队列 数组 滑动窗口 单调队列 堆（优先队列） 
// 👍 1083 👎 0


import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution_239 {

    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        // 注意这里的长度
        int[] res = new int[n - k + 1];
        // 存放最大值得索引
        Deque<Integer> deque = new ArrayDeque<>();
        // 准备初始值加速
        for (int i = 0; i < k; i++) {
            res[0] = addAndGetMax(i, deque, nums);
        }
        // 窗口最后进来的元素，注意这里起始为
        for (int i = k; i < n; i++) {
            // 先移出前面多余元素，注意这里是i-k
            if (deque.peekFirst() != null && deque.peekFirst() == i - k) {
                deque.pollFirst();
            }
            // 注意这里数组下标
            res[i - k + 1] = addAndGetMax(i, deque, nums);
        }
        return res;
    }

    int addAndGetMax(int i, Deque<Integer> deque, int[] nums) {
        // 前面不大于自己的的索引都去掉，注意这里是等于-1和或
        while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
            deque.pollLast();
        }
        // 不用判断，直接入队
        deque.offerLast(i);
        // 队列里第一个是最大的，越靠前的越大
        return nums[deque.peekFirst()];
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution_239().maxSlidingWindow(new int[]{1, 3, 1, 2, 0, 5}, 3)));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
