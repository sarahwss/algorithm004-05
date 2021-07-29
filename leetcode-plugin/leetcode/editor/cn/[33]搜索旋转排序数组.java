package leetcode.editor.cn;//整数数组 nums 按升序排列，数组中的值 互不相同 。
//
// 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[
//k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2
//,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。 
//
// 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [4,5,6,7,0,1,2], target = 0
//输出：4
// 
//
// 示例 2： 
//
// 
//输入：nums = [4,5,6,7,0,1,2], target = 3
//输出：-1 
//
// 示例 3： 
//
// 
//输入：nums = [1], target = 0
//输出：-1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 5000 
// -10^4 <= nums[i] <= 10^4 
// nums 中的每个值都 独一无二 
// 题目数据保证 nums 在预先未知的某个下标上进行了旋转 
// -10^4 <= target <= 10^4 
// 
//
// 
//
// 进阶：你可以设计一个时间复杂度为 O(log n) 的解决方案吗？ 
// Related Topics 数组 二分查找 
// 👍 1477 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution_33_2 {

    public int search(int[] nums, int target) {
        int n = nums.length;
        if (nums[0] == target) {
            return 0;
        }
        if (nums[n - 1] == target) {
            return n - 1;
        }
        int l = 0;
        int r = nums.length - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            // 与mid相关的注意加上nums[]
            // 以nums[mid]所在的段为基础
            // mid在前半段，则需要处理后面两端，关系为或，注意第一个条件要有等号
            if (nums[mid] >= nums[0] && (nums[mid] < target || target < nums[0]) ||
                    // mid在后半段，则右移需要处理后面一段，关系为与
                    nums[mid] < nums[0] && (nums[mid] < target && target < nums[0])) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return -1;
    }
}

class Solution_33_1 {

    public int search(int[] nums, int target) {
        int n = nums.length;
        if (nums[0] == target) {
            return 0;
        }
        if (nums[n - 1] == target) {
            return n - 1;
        }
        int l = 0;
        int r = nums.length - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            // 与mid相关的注意加上nums[]
            // target > nums[0]时，nums[mid]>target不需要判断是否在前半段，一定在前半段
            if ((target > nums[0] && (nums[mid] > target || nums[mid] < nums[0])) ||
                    // target < nums[0]时，nums[mid] > target还需要判断是否在后半段
                    (target < nums[0] && nums[mid] > target && nums[mid] < nums[0])) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return -1;
    }
}

class Solution_33 {

    public int search(int[] nums, int target) {
        int n = nums.length;
        if (nums[0] == target) {
            return 0;
        }
        if (nums[n - 1] == target) {
            return n - 1;
        }
        int l = 0;
        int r = nums.length - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            // 与mid相关的注意加上nums[]
            // target > nums[0]时，nums[mid]<target还需要判断是否在前半段
            if ((target > nums[0] && nums[mid] < target && nums[mid] >= nums[0]) ||
                    // target < nums[0]时，nums[mid] < target无需判断在前半段还是后半段，一定在后半段
                    (target < nums[0] && (nums[mid] < target || nums[mid] >= nums[0]))) {
                //                System.out.println(mid + " " + nums[mid] + ",l=" + (mid + 1) + ",r=" + r);
                l = mid + 1;
            } else {
                //                System.out.println(mid + " " + nums[mid] + ",l=" + l + ",r=" + (mid - 1));
                r = mid - 1;
            }
        }
        return -1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
