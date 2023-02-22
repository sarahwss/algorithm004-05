//给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,5,11,5]
//输出：true
//解释：数组可以分割成 [1, 5, 5] 和 [11] 。 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,3,5]
//输出：false
//解释：数组不能分割成两个元素和相等的子集。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 200 
// 1 <= nums[i] <= 100 
// 
// Related Topics 数组 动态规划 👍 1633 👎 0


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public boolean canPartition(int[] nums) {
        int sum = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }

        if (sum % 2 == 1) {
            return false;
        }
        int target = sum / 2;

        int[] sums = new int[target + 1];
        Arrays.fill(sums, -2);
        sums[0] = -1; // 别少了

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (num > target) {
                continue;
            }
            if (sums[target - num] > -2) {
                //                System.out.println(i + " " + sums[target - num] + " " + target + " " + num);
                return true;
            }
            for (int j = 0; j < sums.length; j++) {
                if (sums[j] > -2 && j + num < target && sums[j + num] == -2 && sums[j] < i) {
                    //                                          sums[j] < i防止重复相加，sums[j + num] == -2防止相同元素重复赋值
                    // 注意 j + num < target顺序要靠前
                    sums[j + num] = i;
                    //                    System.out.println(i + " " + j + " " + Arrays.toString(sums) + target);

                }
            }
            //            System.out.println(i + " " + Arrays.toString(sums) + target);
        }
        return false;

    }
}
//leetcode submit region end(Prohibit modification and deletion)
