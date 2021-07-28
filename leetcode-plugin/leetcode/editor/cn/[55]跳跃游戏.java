package leetcode.editor.cn;//给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
//
// 数组中的每个元素代表你在该位置可以跳跃的最大长度。 
//
// 判断你是否能够到达最后一个下标。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [2,3,1,1,4]
//输出：true
//解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
// 
//
// 示例 2： 
//
// 
//输入：nums = [3,2,1,0,4]
//输出：false
//解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 3 * 104 
// 0 <= nums[i] <= 105 
// 
// Related Topics 贪心 数组 动态规划 
// 👍 1280 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
// 贪心
class Solution_55_2 {

    public boolean canJump(int[] nums) {
        int n = nums.length;
        // 前面所有元素可以跳到的最远位置
        int furthest = 0;
        // 最后一步不跳，注意n-1不要出现在循环中
        for (int i = 0; i < n - 1; i++) {
            // 前面的元素可以跳到当前位置
            if (i <= furthest
                    // 当前位置可以跳到更远
                    && i + nums[i] > furthest) {
                furthest = i + nums[i];
            }
        }
        // 跳到最远的位置是否到了最后一个位置
        return furthest >= n - 1;
    }
}

// 迭代
class Solution_55_1 {

    public boolean canJump(int[] nums) {
        int n = nums.length;
        // 从当前位置能否跳到最后，默认都是false，减少后面多余赋值
        boolean[] dp = new boolean[n];
        dp[n - 1] = true;
        // 从当前位置跳到后面的位置
        for (int i = n - 2; i >= 0; i--) {
            int num = nums[i];
            // 从小到大尝试，越小希望越大
            for (int j = 1; j <= num; j++) {
                // 不需要判断i+j是否越界，到n-1就会返回
                if (dp[i + j] == true) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[0];
    }
}

// 带备忘录的递归
class Solution_55 {

    public boolean canJump(int[] nums) {
        int n = nums.length;
        Boolean[] demo = new Boolean[n];
        demo[n - 1] = true;
        return canJump(nums, 0, n, demo);
    }

    boolean canJump(int[] nums, int i, int n, Boolean[] demo) {
        // 有，直接返回
        if (demo[i] != null) {
            return demo[i];
        }
        int num = nums[i];
        if (num == 0) {
            return false;
        }
        // 没有值，计算，从大往小尝试
        // 注意这里不能等于0，必须至少跳一步，否则会有死循环
        for (int j = num; j > 0; j--) {
            if (i + j >= n - 1) {
                return true;
            }
            if (demo[i + j] = canJump(nums, i + j, n, demo)) {
                return true;
            }
        }
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
