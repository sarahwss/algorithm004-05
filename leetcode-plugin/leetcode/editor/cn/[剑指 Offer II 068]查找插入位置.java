package leetcode.editor.cn;//给定一个排序的整数数组 nums 和一个整数目标值 target ，请在数组中找到 target ，并返回其下标。如果目标值不存在于数组中，返回它将会被按顺
//序插入的位置。 
//
// 请必须使用时间复杂度为 O(log n) 的算法。 
//
// 
//
// 示例 1: 
//
// 
//输入: nums = [1,3,5,6], target = 5
//输出: 2
// 
//
// 示例 2: 
//
// 
//输入: nums = [1,3,5,6], target = 2
//输出: 1
// 
//
// 示例 3: 
//
// 
//输入: nums = [1,3,5,6], target = 7
//输出: 4
// 
//
// 示例 4: 
//
// 
//输入: nums = [1,3,5,6], target = 0
//输出: 0
// 
//
// 示例 5: 
//
// 
//输入: nums = [1], target = 0
//输出: 0
// 
//
// 
//
// 提示: 
//
// 
// 1 <= nums.length <= 104 
// -104 <= nums[i] <= 104 
// nums 为无重复元素的升序排列数组 
// -104 <= target <= 104 
// 
//
// 
//
// 注意：本题与主站 35 题相同： https://leetcode-cn.com/problems/search-insert-position/ 
// Related Topics 数组 二分查找 
// 👍 0 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution_剑指068 {

    public int searchInsert(int[] nums, int target) {
        int i = 0;
        int j = nums.length - 1;
        while (i <= j) {
            int mid = (i + j) / 2;
            if (nums[mid] >= target) {
                // 比最小值还小，刚好是第一个较大值或相同值
                //注意 这里要和target相比，不是和nums[mid]相比
                if (mid == 0 || nums[mid - 1] < target) {
                    // 注意这里是return mid，不是return i
                    return mid;
                }
                j = mid - 1;
            } else {
                i = mid + 1;
            }
        }
        // 没有找到，插到最后
        return nums.length;

    }
}
//leetcode submit region end(Prohibit modification and deletion)
