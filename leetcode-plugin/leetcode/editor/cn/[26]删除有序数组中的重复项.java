package leetcode.editor.cn;//给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。
//
// 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。 
//
// 
//
// 说明: 
//
// 为什么返回数值是整数，但输出的答案是数组呢? 
//
// 请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。 
//
// 你可以想象内部操作如下: 
//
// 
//// nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
//int len = removeDuplicates(nums);
//
//// 在函数里修改输入数组对于调用者是可见的。
//// 根据你的函数返回的长度, 它会打印出数组中 该长度范围内 的所有元素。
//for (int i = 0; i < len; i++) {
//    print(nums[i]);
//}
// 
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,1,2]
//输出：2, nums = [1,2]
//解释：函数应该返回新的长度 2 ，并且原数组 nums 的前两个元素被修改为 1, 2 。不需要考虑数组中超出新长度后面的元素。
// 
//
// 示例 2： 
//
// 
//输入：nums = [0,0,1,1,1,2,2,3,3,4]
//输出：5, nums = [0,1,2,3,4]
//解释：函数应该返回新的长度 5 ， 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4 。不需要考虑数组中超出新长度后面的元素。
// 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 3 * 104 
// -104 <= nums[i] <= 104 
// nums 已按升序排列 
// 
//
// 
// Related Topics 数组 双指针 
// 👍 2123 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution_26_1 {

    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        // 不循环元素的下一个位置，从一开始，第一个元素一定不重复
        int j = 1;
        // i是每次循环一定要移动的，第一个元素保留位置
        for (int i = 1; i < n; i++) {
            // 不重复，往里添加
            if (nums[i] != nums[j - 1]) {
                nums[j++] = nums[i];
            }
        }
        return j;
    }
}

class Solution_26 {

    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        // 循环指针
        int i = 0;
        // 不循环元素的下一个位置
        int j = 0;
        while (i < n && j < n) {
            // 重复元素
            if (j >= 1 && nums[i] == nums[j - 1]) {
                i++;
                continue;
            }
            // 不重复，添加不循环元素
            nums[j++] = nums[i++];
        }
        return j;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
