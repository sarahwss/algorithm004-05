package leetcode.editor.cn;//给定一个数组 nums ，如果 i < j 且 nums[i] > 2*nums[j] 我们就将 (i, j) 称作一个重要翻转对。
//
// 你需要返回给定数组中的重要翻转对的数量。 
//
// 示例 1: 
//
// 
//输入: [1,3,2,3,1]
//输出: 2
// 
//
// 示例 2: 
//
// 
//输入: [2,4,3,5,1]
//输出: 3
// 
//
// 注意: 
//
// 
// 给定数组的长度不会超过50000。 
// 输入数组中的所有数字都在32位整数的表示范围内。 
// 
// Related Topics 树状数组 线段树 数组 二分查找 分治 有序集合 归并排序 
// 👍 291 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public int reversePairs(int[] nums) {
        return mergeSort(nums, 0, nums.length - 1);
    }

    // 如何确保总是小的i和大的j比较？因为merge本身就是按顺序合并和比较的
    // 使用排序的好处是，对于排好序的i，前面的i有j个满足条件，后面的i也至少有J个满足条件，减少多余比较
    private int mergeSort(int[] nums, int l, int r) {
        if (r - l <= 0 || nums.length == 1) {
            return 0;
        }
        int mid = l + (r - l) / 2;
        int count = 0;
        int c1 = mergeSort(nums, l, mid);
        int c2 = mergeSort(nums, mid + 1, r);
        // 这里从l开始，不要写成0
        // j放在循环外层，防止多余比较，因为nums[i越往后越大，所以可定至少有上一循环算出的j的个元素符合条件
        for (int i = l, j = mid + 1; i <= mid; i++) {
            // 已排好序，直接往右移指针就好
            // 注意两个条件顺序
            // 这里用除法，防止溢出
            while (j <= r && nums[i] / 2.0 > nums[j]) {
                j++;
            }
            count += j - (mid + 1);
        }
        merge(nums, l, mid, r);
        return count + c1 + c2;
    }

    private void merge(int[] nums, int l, int mid, int r) {
        int[] arr = new int[r - l + 1];
        int k = 0;
        int i = l;
        int j = mid + 1;
        while (i <= mid && j <= r) {
            if (nums[i] <= nums[j]) {
                arr[k++] = nums[i++];
            } else {
                arr[k++] = nums[j++];
            }
        }
        while (i <= mid) {
            arr[k++] = nums[i++];
        }
        while (j <= r) {
            arr[k++] = nums[j++];
        }
        System.arraycopy(arr, 0, nums, l, r - l + 1);
    }

    public static void main(String[] args) {
        System.out.println(new Solution()
                .reversePairs(new int[]{2147483647, 2147483647, 2147483647, 2147483647, 2147483647, 2147483647}));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
