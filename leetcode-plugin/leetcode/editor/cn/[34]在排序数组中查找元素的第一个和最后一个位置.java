//给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。 
//
// 如果数组中不存在目标值 target，返回 [-1, -1]。 
//
// 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [5,7,7,8,8,10], target = 8
//输出：[3,4] 
//
// 示例 2： 
//
// 
//输入：nums = [5,7,7,8,8,10], target = 6
//输出：[-1,-1] 
//
// 示例 3： 
//
// 
//输入：nums = [], target = 0
//输出：[-1,-1] 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 10⁵ 
// -10⁹ <= nums[i] <= 10⁹ 
// nums 是一个非递减数组 
// -10⁹ <= target <= 10⁹ 
// 
// Related Topics 数组 二分查找 👍 2147 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) {
            return new int[]{-1, -1};
        }
        // j是target第一个元素q前一个
        int i = 0, j = nums.length - 1;
        while (i <= j) {
            int mid = (i + j) / 2;
//            System.out.println(i + ": " + nums[i] + " " + j + ": " + nums[j] + " " + mid + ": " + nums[mid]);
            if (nums[mid] < target) {
                i = mid + 1;
//                System.out.println("i:" + i);

            } else if (nums[mid] >= target) {
                j = mid - 1;
//                System.out.println("j:" + j);

            }
        }
//        System.out.println(i + " " + j);

        // i是target最后一个元素下一个
        while (i <= nums.length - 1 && nums[i] == target) {
            i++;
        }
//        System.out.println(i + " " + j);
        // 未找到
        if (i - j == 1) {
            return new int[]{-1, -1};
        }
        return new int[]{j + 1, i - 1};

    }
}
//leetcode submit region end(Prohibit modification and deletion)
