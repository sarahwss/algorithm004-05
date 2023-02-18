//整数数组的一个 排列 就是将其所有成员以序列或线性顺序排列。 
//
// 
// 例如，arr = [1,2,3] ，以下这些都可以视作 arr 的排列：[1,2,3]、[1,3,2]、[3,1,2]、[2,3,1] 。 
// 
//
// 整数数组的 下一个排列 是指其整数的下一个字典序更大的排列。更正式地，如果数组的所有排列根据其字典顺序从小到大排列在一个容器中，那么数组的 下一个排列 就
//是在这个有序容器中排在它后面的那个排列。如果不存在下一个更大的排列，那么这个数组必须重排为字典序最小的排列（即，其元素按升序排列）。 
//
// 
// 例如，arr = [1,2,3] 的下一个排列是 [1,3,2] 。 
// 类似地，arr = [2,3,1] 的下一个排列是 [3,1,2] 。 
// 而 arr = [3,2,1] 的下一个排列是 [1,2,3] ，因为 [3,2,1] 不存在一个字典序更大的排列。 
// 
//
// 给你一个整数数组 nums ，找出 nums 的下一个排列。 
//
// 必须 原地 修改，只允许使用额外常数空间。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,3]
//输出：[1,3,2]
// 
//
// 示例 2： 
//
// 
//输入：nums = [3,2,1]
//输出：[1,2,3]
// 
//
// 示例 3： 
//
// 
//输入：nums = [1,1,5]
//输出：[1,5,1]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 100 
// 0 <= nums[i] <= 100 
// 
// Related Topics 数组 双指针 👍 2059 👎 0


import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public void nextPermutation(int[] nums) {
        int n = nums.length;
        int max = 0;
        int i = n - 1;
        // 找到递减序列之前的一个元素，该元素在非严格递减序列中一定存在
        for (; i >= 0; i--) {
            if (nums[i] < max) {
                break;
            }
            max = nums[i];
        }
//        System.out.println(i);
        // 序列严格递减，已是
        // 最大，返回最小
        if (i == -1) {
            Arrays.sort(nums);
        } else { // 递减序列之前的元素存在，替换加翻转
            // 找到递减序列中大于temp的最小值，交换位置
            int temp = nums[i];
            int j = i + 1;
            while (j + 1 < n && nums[j + 1] > temp) {
                j++;
            }
            nums[i] = nums[j];
            nums[j] = temp;
            // 递减序列翻转，成递增序列
            reverse(nums, i + 1, n - 1);
        }

    }

    private void reverse(int[] nums, int l, int r) {
        // 注意这里是小于不是不等于
        while (l < r) {
            int temp = nums[l];
            nums[l++] = nums[r];
            nums[r--] = temp;
        }

    }
}
//leetcode submit region end(Prohibit modification and deletion)
