package leetcode.editor.cn;//给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
//
// 你可以假设数组是非空的，并且给定的数组总是存在多数元素。 
//
// 
//
// 示例 1： 
//
// 
//输入：[3,2,3]
//输出：3 
//
// 示例 2： 
//
// 
//输入：[2,2,1,1,1,2,2]
//输出：2
// 
//
// 
//
// 进阶： 
//
// 
// 尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。 
// 
// Related Topics 数组 哈希表 分治 计数 排序 
// 👍 1075 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution_169_1 {

    public int majorityElement(int[] nums) {
        // 有一半的元素需要折半计数，一半的元素折半直接返回
        return majorityElement(nums, 0, nums.length - 1);
    }

    int majorityElement(int[] nums, int l, int r) {
        // 别少了递归出口
        if (l == r) {
            return nums[l];
        }
        int mid = l + (r - l) / 2;
        int left = majorityElement(nums, l, mid);
        int right = majorityElement(nums, mid + 1, r);
        if (left == right) {
            return left;
        }
        int count1 = count(nums, left, l, r);
        int count2 = count(nums, right, l, r);
        return count1 > count2 ? left : right;
    }

    private int count(int[] nums, int left, int l, int r) {
        int count = 0;
        for (int i = l; i <= r; i++) {
            if (nums[i] == left) {
                count++;
            }
        }
        return count;
    }
}

class Solution_169 {

    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
