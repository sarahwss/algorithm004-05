package leetcode.editor.cn;//给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重
//复的三元组。 
//
// 注意：答案中不可以包含重复的三元组。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [-1,0,1,2,-1,-4]
//输出：[[-1,-1,2],[-1,0,1]]
// 
//
// 示例 2： 
//
// 
//输入：nums = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：nums = [0]
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 3000 
// -105 <= nums[i] <= 105 
// 
// Related Topics 数组 双指针 排序 
// 👍 3537 👎 0


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)

class Solution_15 {

    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < n - 2; i++) {
            // 注意这里是break
            if (nums[i] > 0) {
                break;
            }
            // 第一个数字去重
            if (i - 1 >= 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            // 第二个数
            int l = i + 1;
            int r = n - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    // 先增减方便后面循环条件
                    l++;
                    r--;
                    // 添加结果成功再去重，防止漏掉结果
                    while (l < r && nums[l] == nums[l - 1]) {
                        l++;
                    }
                    while (l < r && nums[r] == nums[r + 1]) {
                        r--;
                    }
                } else if (sum < 0) {
                    l++;
                } else {
                    r--;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Solution_15().threeSum(new int[]{1, 2, -2, -1}));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
