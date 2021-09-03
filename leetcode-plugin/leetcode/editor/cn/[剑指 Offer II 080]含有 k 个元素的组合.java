package leetcode.editor.cn;//给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
//
// 
//
// 示例 1: 
//
// 
//输入: n = 4, k = 2
//输出:
//[
//  [2,4],
//  [3,4],
//  [2,3],
//  [1,2],
//  [1,3],
//  [1,4],
//] 
//
// 示例 2: 
//
// 
//输入: n = 1, k = 1
//输出: [[1]] 
//
// 
//
// 提示: 
//
// 
// 1 <= n <= 20 
// 1 <= k <= n 
// 
//
// 
//
// 注意：本题与主站 77 题相同： https://leetcode-cn.com/problems/combinations/ 
// Related Topics 数组 回溯 
// 👍 1 👎 0


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution_剑指_080 {

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        combine(n, k, 1, new LinkedList<>(), res);
        return res;
    }

    private void combine(int n, int k, int i, LinkedList<Integer> list, List<List<Integer>> res) {
        // 不能直接判断n返回，大于n是可能刚好size等于k
        if (list.size() == k) {
            res.add(new ArrayList<>(list));
        } else if (i <= n) {
            combine(n, k, i + 1, list, res);
            list.add(i);
            combine(n, k, i + 1, list, res);
            list.removeLast();
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
