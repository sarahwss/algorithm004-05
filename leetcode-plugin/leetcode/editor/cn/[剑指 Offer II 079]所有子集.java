package leetcode.editor.cn;//给定一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
//
// 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,3]
//输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [0]
//输出：[[],[0]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10 
// -10 <= nums[i] <= 10 
// nums 中的所有元素 互不相同 
// 
//
// 
//
// 注意：本题与主站 78 题相同： https://leetcode-cn.com/problems/subsets/ 
// Related Topics 位运算 数组 回溯 
// 👍 2 👎 0


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution_剑指_079 {

    public List<List<Integer>> subsets(int[] nums) {

        List<List<Integer>> res = new ArrayList<>();
        subsets(nums, 0, new LinkedList<Integer>(), res);
        return res;

    }

    private <E> void subsets(int[] nums, int i, LinkedList<Integer> list, List<List<Integer>> res) {
        if (i == nums.length) {
            res.add(new ArrayList(list));
            // 别少了return
            return;
        }
        subsets(nums, i + 1, list, res);
        list.add(nums[i]);
        subsets(nums, i + 1, list, res);
        list.removeLast();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
