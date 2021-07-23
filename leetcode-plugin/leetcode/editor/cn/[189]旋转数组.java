package leetcode.editor.cn;//给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
//
// 
//
// 进阶： 
//
// 
// 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。 
// 你可以使用空间复杂度为 O(1) 的 原地 算法解决这个问题吗？ 
// 
//
// 
//
// 示例 1: 
//
// 
//输入: nums = [1,2,3,4,5,6,7], k = 3
//输出: [5,6,7,1,2,3,4]
//解释:
//向右旋转 1 步: [7,1,2,3,4,5,6]
//向右旋转 2 步: [6,7,1,2,3,4,5]
//向右旋转 3 步: [5,6,7,1,2,3,4]
// 
//
// 示例 2: 
//
// 
//输入：nums = [-1,-100,3,99], k = 2
//输出：[3,99,-1,-100]
//解释: 
//向右旋转 1 步: [99,-1,-100,3]
//向右旋转 2 步: [3,99,-1,-100] 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 2 * 104 
// -231 <= nums[i] <= 231 - 1 
// 0 <= k <= 105 
// 
//
// 
// 
// Related Topics 数组 数学 双指针 
// 👍 1039 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution_189_1 {

    public void rotate(int[] nums, int k) {
        int n = nums.length;
        reverse(nums, 0, n - 1);
        // k可能比较大，注意这里是k % n - 1，不是(k-1)%n
        reverse(nums, 0, k % n - 1);
        reverse(nums, k % n, n - 1);
    }

    void reverse(int[] nums, int i, int j) {
        while (i < j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            // 别忘了移动指针
            i++;
            j--;
        }
    }

}

class Solution_189 {

    public void rotate(int[] nums, int k) {
        int n = nums.length;
        int count = 0;
        for (int i = 0; i < k; i++) {
            int pre = nums[i];
            int j = i;
            // 有可能交换元素形成一个闭环，也有可能没有（这种情况一定会一趟循环交换完全不元素），波动其他元素开始循环
            do {
                int next = (j + k) % n;
                // 别忘了存起来
                int temp = nums[next];
                nums[next] = pre;
                pre = temp;
                j = next;
                count++;
            } while (j != i);
            // 有可能一趟循环就把所有元素交换了
            if (count == n) {
                break;
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
