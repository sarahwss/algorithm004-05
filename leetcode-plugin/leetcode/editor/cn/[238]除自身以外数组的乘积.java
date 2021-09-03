package leetcode.editor.cn;//给你一个长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之
//外其余各元素的乘积。 
//
// 
//
// 示例: 
//
// 输入: [1,2,3,4]
//输出: [24,12,8,6] 
//
// 
//
// 提示：题目数据保证数组之中任意元素的全部前缀元素和后缀（甚至是整个数组）的乘积都在 32 位整数范围内。 
//
// 说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。 
//
// 进阶： 
//你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。） 
// Related Topics 数组 前缀和 
// 👍 906 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        int right = 1;
        for (int i = 0; i < n; i++) {
            // 注意这里是[i-1]
            res[i] = (i == 0 ? 1 : res[i - 1]) * nums[i];
        }
        for (int i = n - 1; i >= 0; i--) {
            // 注意这里是[i-1]
            res[i] = right * (i == 0 ? 1 : res[i - 1]);
            right = right * nums[i];
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution_238().productExceptSelf(new int[]{1, 2, 3, 4})));
    }
}

class Solution_238 {

    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] product1 = new int[n + 1];
        product1[0] = 1;
        int[] product2 = new int[n + 1];
        product2[n] = 1;
        int[] res = new int[n];
        for (int i = 1; i <= n; i++) {
            // 注意这里是[i-1]
            product1[i] = product1[i - 1] * nums[i - 1];
            product2[n - i] = product2[n - i + 1] * nums[n - i];
        }
        //        System.out.println(Arrays.toString(product1));
        //        System.out.println(Arrays.toString(product2));
        for (int i = 0; i < n; i++) {
            // 注意这里是[i-1]
            res[i] = product1[i] * product2[i + 1];
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution_238().productExceptSelf(new int[]{1, 2, 3, 4})));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
