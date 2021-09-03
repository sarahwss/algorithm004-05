package leetcode.editor.cn;//给定一个只包含整数的有序数组 nums ，每个元素都会出现两次，唯有一个数只会出现一次，请找出这个唯一的数字。
//
// 
//
// 示例 1: 
//
// 
//输入: nums = [1,1,2,3,3,4,4,8,8]
//输出: 2
// 
//
// 示例 2: 
//
// 
//输入: nums =  [3,3,7,7,10,11,11]
//输出: 10
// 
//
// 
//
// 
//
// 
//
// 提示: 
//
// 
// 1 <= nums.length <= 105 
// 0 <= nums[i] <= 105 
// 
//
// 
//
// 进阶: 采用的方案可以在 O(log n) 时间复杂度和 O(1) 空间复杂度中运行吗？ 
//
// 
//
// 注意：本题与主站 540 题相同：https://leetcode-cn.com/problems/single-element-in-a-sorted-
//array/ 
// Related Topics 数组 二分查找 
// 👍 1 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution_剑指070 {

    public int singleNonDuplicate(int[] nums) {
        // 只含唯一个单个数字，则数组长度是奇数，两两分组科f分为n/2+1组，最后一组只有一个元素
        // 分组序号从0开始，n/2结束
        int i = 0;
        int j = nums.length / 2;
        while (i <= j) {
            int mid = (i + j) / 2;
            // 指定分组的起始索引
            int index = mid * 2;
            // 不是最后一个分组
            if (index < nums.length - 1 && nums[index] != nums[index + 1]) {
                // 是第一个分组，或者上一个分组相等
                if (index == 0 || nums[index - 2] == nums[index - 1]) {
                    return nums[index];
                }
                // 不是第一个出现问题的分组
                j = mid - 1;
            } else {
                i = mid + 1;
            }
        }
        // 到最后一个分组的时候，i=j一定跳出循环
        return nums[nums.length - 1];

    }
}
//leetcode submit region end(Prohibit modification and deletion)
