package leetcode.editor.cn;//给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
//
// 示例: 
//
// 输入: [0,1,0,3,12]
//输出: [1,3,12,0,0] 
//
// 说明: 
//
// 
// 必须在原数组上操作，不能拷贝额外的数组。 
// 尽量减少操作次数。 
// 
// Related Topics 数组 双指针 
// 👍 1132 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution_283 {

    public void moveZeroes(int[] nums) {
        int n = nums.length;
        // 下一个遍历元素
        int i = 0;
        // 下一个非零元素的位置
        int j = 0;
        // 别少了j<n
        while (i < n && j < n) {
            // 找到下一个1
            while (i < n && nums[i] == 0) {
                i++;
            }
            // 上面i变化了，这里需要判断I；已经判断过J,这里不需要判断
            if (i < n) {
                nums[j++] = nums[i++];
            }
        }
        // 剩余位置改成0
        while (j < n) {
            nums[j++] = 0;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
