package leetcode.editor.cn;//已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，原数组 nums = [0,1,2,4,5,6,7] 在变
//化后可能得到：
// 
// 若旋转 4 次，则可以得到 [4,5,6,7,0,1,2] 
// 若旋转 7 次，则可以得到 [0,1,2,4,5,6,7] 
// 
//
// 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], 
//..., a[n-2]] 。 
//
// 给你一个元素值 互不相同 的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [3,4,5,1,2]
//输出：1
//解释：原数组为 [1,2,3,4,5] ，旋转 3 次得到输入数组。
// 
//
// 示例 2： 
//
// 
//输入：nums = [4,5,6,7,0,1,2]
//输出：0
//解释：原数组为 [0,1,2,4,5,6,7] ，旋转 4 次得到输入数组。
// 
//
// 示例 3： 
//
// 
//输入：nums = [11,13,15,17]
//输出：11
//解释：原数组为 [11,13,15,17] ，旋转 4 次得到输入数组。
// 
//
// 
//
// 提示： 
//
// 
// n == nums.length 
// 1 <= n <= 5000 
// -5000 <= nums[i] <= 5000 
// nums 中的所有整数 互不相同 
// nums 原来是一个升序排序的数组，并进行了 1 至 n 次旋转 
// 
// Related Topics 数组 二分查找 
// 👍 525 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution_153 {

    public int findMin(int[] nums) {
        int n = nums.length;
        int l = 0;
        int r = n - 1;
        while (l < r) {
            int mid = (l + r) / 2;
            // 二分规律：
            // 当 while (left < right) 时，对应的更新式是 left = middle + 1 ， right = middle
            // 当 while (left <= right) 时，对应的更新式是 left = middle + 1，right = middle - 1
            // 本题由于【当区间长度为1时，即可停止二分查找】，所以是 while (left < right) ，所以是 left = middle + 1，right = middle
            // 为什么r=mid而不是mid-1，因为目标是l在左半段末尾，r在右半段开头，其最小值在r上，所以r上的数据不能丢，至于为什么最终返回l，因为最终l右移和r值一样了
            // mid在右半段
            if (nums[mid] < nums[r]) {
                r = mid;
                // mid在左半段
            } else {
                l = mid + 1;
            }
        }
        return nums[r];

    }
}
//leetcode submit region end(Prohibit modification and deletion)
