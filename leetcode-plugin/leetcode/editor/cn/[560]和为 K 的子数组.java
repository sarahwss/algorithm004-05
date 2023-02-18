//给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的连续子数组的个数 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,1,1], k = 2
//输出：2
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,3], k = 3
//输出：2
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 2 * 10⁴ 
// -1000 <= nums[i] <= 1000 
// -10⁷ <= k <= 10⁷ 
// 
// Related Topics 数组 哈希表 前缀和 👍 1809 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // 用递归会超时，使用递推
    public int subarraySum(int[] nums, int k) {
        int n = nums.length;
        // 前n个数的和
        int[] dp = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            dp[i] = dp[i - 1] + nums[i - 1];
        }
//        System.out.println(Arrays.toString(dp));
        int res = 0;
        // 注意从0开始，子串可能起始索引是1
        for (int i = 0; i < n + 1; i++) {
            // j从1开始，是真实的子串
            for (int j = i + 1; j < n + 1; j++) {
//                System.out.println(i + " " + j + " " + (dp[j] - dp[i]));
                if (dp[j] - dp[i] == k) {
                    res++;
                }
            }
        }
        return res;


    }


}
//leetcode submit region end(Prohibit modification and deletion)
