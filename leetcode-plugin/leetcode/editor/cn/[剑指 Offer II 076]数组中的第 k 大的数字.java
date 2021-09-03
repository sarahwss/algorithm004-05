package leetcode.editor.cn;//给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
//
// 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。 
//
// 
//
// 示例 1: 
//
// 
//输入: [3,2,1,5,6,4] 和 k = 2
//输出: 5
// 
//
// 示例 2: 
//
// 
//输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
//输出: 4 
//
// 
//
// 提示： 
//
// 
// 1 <= k <= nums.length <= 104 
// -104 <= nums[i] <= 104 
// 
//
// 
//
// 注意：本题与主站 215 题相同： https://leetcode-cn.com/problems/kth-largest-element-in-an-
//array/ 
// Related Topics 数组 分治 快速选择 排序 堆（优先队列） 
// 👍 2 👎 0


import java.util.Random;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution_剑指_076 {

    public int findKthLargest(int[] nums, int k) {
        int target = nums.length - k;
        int start = 0;
        int end = nums.length - 1;
        int index = -1;
        while (index != target) {
            if (index > target) {
                end = index - 1;
            } else {
                start = index + 1;
            }
            index = patition(nums, start, end);
        }
        return nums[index];
    }

    private int patition(int[] nums, int start, int end) {
        int pivot = new Random().nextInt(end - start + 1) + start;
        swap(nums, end, pivot);
        // 上一个小于目标的位置
        int small = start - 1;
        for (int j = start; j < end; j++) {
            if (nums[j] < nums[end]) {
                small++;
                swap(nums, small, j);
            }
        }
        small++;
        swap(nums, small, end);
        return small;
    }

    private void swap(int[] nums, int i, int j) {
        if (i >= 0 && i < nums.length) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
