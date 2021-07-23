package leetcode.editor.cn;//给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
//
// 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。你可以假设 nums1 的空间大小等于 m + n，这样它就有足够的空间保存来自 nu
//ms2 的元素。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
//输出：[1,2,2,3,5,6]
// 
//
// 示例 2： 
//
// 
//输入：nums1 = [1], m = 1, nums2 = [], n = 0
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// nums1.length == m + n 
// nums2.length == n 
// 0 <= m, n <= 200 
// 1 <= m + n <= 200 
// -109 <= nums1[i], nums2[i] <= 109 
// 
// Related Topics 数组 双指针 排序 
// 👍 1018 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution_88 {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int l = m + n - 1;
        int i = m - 1, j = n - 1;
        // 从后往前找最大值，将后面位置填满,直到有一个数组用完，直到所有元素全用完，最多也是j=i+1，不会出现前后索引相交的情况。
        while (i >= 0 && j >= 0) {
            nums1[l--] = nums1[i] >= nums2[j] ? nums1[i--] : nums2[j--];
        }
        // 如果用完的是nums2，则nums1肯定一直没动，数组已完整，不需要多余操作
        // 如果用完的是nums1，则剩余nums2，全部比已经移动完的后面的元素小(全部的nums1)，填入空缺即可，此时k空缺的位置是从0到剩余nums2长度的位置
        if (j >= 0) {
            // 这里l+1和j+1都行
            System.arraycopy(nums2, 0, nums1, 0, j + 1);
        }

    }
}
//leetcode submit region end(Prohibit modification and deletion)
