package leetcode.editor.cn;//给定一个可能有重复数字的整数数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合
//。 
//
// candidates 中的每个数字在每个组合中只能使用一次，解集不能包含重复的组合。 
//
// 
//
// 示例 1: 
//
// 
//输入: candidates = [10,1,2,7,6,1,5], target = 8,
//输出:
//[
//[1,1,6],
//[1,2,5],
//[1,7],
//[2,6]
//] 
//
// 示例 2: 
//
// 
//输入: candidates = [2,5,2,1,2], target = 5,
//输出:
//[
//[1,2,2],
//[5]
//] 
//
// 
//
// 提示: 
//
// 
// 1 <= candidates.length <= 100 
// 1 <= candidates[i] <= 50 
// 1 <= target <= 30 
// 
//
// 
//
// 注意：本题与主站 40 题相同： https://leetcode-cn.com/problems/combination-sum-ii/ 
// Related Topics 数组 回溯 
// 👍 1 👎 0


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution_剑指_082 {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        combinationSum2(candidates, 0, target, new LinkedList<>(), res);
        return res;
    }

    private void combinationSum2(int[] candidates, int i, int target, LinkedList<Integer> list,
                                 List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(list));
            // 主语target需要大于0
        } else if (target > 0 && i < candidates.length) {
            // 为避免重复，跳过当前的nums和之后全部的num
            combinationSum2(candidates, getNext(candidates, i), target, list, res);
            list.add(candidates[i]);
            combinationSum2(candidates, i + 1, target - candidates[i], list, res);
            list.removeLast();
        }
    }

    private int getNext(int[] nums, int i) {
        int num = nums[i];
        while (i < nums.length && nums[i] == num) {
            i++;
        }
        return i;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
