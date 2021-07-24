package leetcode.editor.cn;//给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
//
// 你可以按 任何顺序 返回答案。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 4, k = 2
//输出：
//[
//  [2,4],
//  [3,4],
//  [2,3],
//  [1,2],
//  [1,3],
//  [1,4],
//] 
//
// 示例 2： 
//
// 
//输入：n = 1, k = 1
//输出：[[1]] 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 20 
// 1 <= k <= n 
// 
// Related Topics 数组 回溯 
// 👍 634 👎 0


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution_77_1 {

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        combine(n, k, 1, res, new ArrayList<>());
        return res;
    }

    void combine(int n, int k, int i, List<List<Integer>> res, List<Integer> list) {
        // 放在前面，不要漏掉结果
        if (list.size() == k) {
            res.add(new ArrayList<>(list));
            // 这里return不要忘了
            return;
        }
        int need = k - list.size();
        // 注意这里需要+1,i还没有用
        int remain = n - i + 1;
        //        System.out.println(list + " " + i + " " + need + " " + remain);
        // 剩余元素不够，直接返回
        if (i > n || need > remain) {
            return;
        }
        // 因为必须凑齐k个元素，并不是每个递归都有加和不加的自由，会引来冗余递归
        // 剩余元素多余，可以不加该元素
        if (need < remain) {
            // 不加该元素
            combine(n, k, i + 1, res, list);
        }
        // 剩余元素多余或刚好，可加入该元素
        list.add(i);
        combine(n, k, i + 1, res, list);
        // 恢复现场
        list.remove(list.size() - 1);

    }
}

class Solution_77 {

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        combine(n, k, 1, res, new ArrayList<>());
        return res;
    }

    void combine(int n, int k, int i, List<List<Integer>> res, List<Integer> list) {
        // 放在前面，不要漏掉结果
        if (list.size() == k) {
            res.add(new ArrayList<>(list));
            // 这里return不要忘了
            return;
        }
        if (i > n) {
            return;
        }
        // 不加该元素
        combine(n, k, i + 1, res, list);
        // 加入该元素，防重复
        list.add(i);
        combine(n, k, i + 1, res, list);
        // 恢复现场
        list.remove(list.size() - 1);

    }
}
//leetcode submit region end(Prohibit modification and deletion)
