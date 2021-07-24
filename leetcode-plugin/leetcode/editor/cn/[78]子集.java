package leetcode.editor.cn;
//给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
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
// Related Topics 位运算 数组 回溯 
// 👍 1253 👎 0


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution_76_1 {

    public List<List<Integer>> subsets(int[] nums) {
        int n = nums.length;
        // 从前往后一定不会重复
        List<List<Integer>> res = new ArrayList<>();
        // 不需要循环
        subSets(res, 0, new ArrayList<>(), nums);
        return res;
    }

    void subSets(List<List<Integer>> res, int i, List<Integer> list, int nums[]) {
        int n = nums.length;
        if (i == n) {
            // 这里要包装新list
            res.add(new ArrayList<>(list));
            return;
        }
        // 不加该元素
        subSets(res, i + 1, list, nums);
        // 不需要判断是否重复，从前往后各不相同
        list.add(nums[i]);
        // 加入该元素
        subSets(res, i + 1, list, nums);
        // 恢复现场，注意这里索引不是I,是最后一个位置
        list.remove(list.size() - 1);
    }
}

// 不推荐，速度慢，逻辑冗余
class Solution_78 {

    public List<List<Integer>> subsets(int[] nums) {
        int n = nums.length;
        // 从前往后一定不会重复
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> blank = new ArrayList<>();
        // 提前加进来，防止在递归中加重复
        res.add(blank);
        // 元素不一定从0开始，这里需要遍历
        for (int i = 0; i < n; i++) {
            // 从空集合开始遍历，因为空集合也是一个子集
            subSets(res, i, blank, nums);
        }
        return res;
    }

    void subSets(List<List<Integer>> res, int i, List<Integer> list, int nums[]) {
        int n = nums.length;
        if (i == n) {
            return;
        }
        int num = nums[i];
        // 加入该元素
        if (!list.contains(nums[i])) {
            // 创建新的集合，因为上面的集合是结果的一种，不能破坏
            List<Integer> temp = new ArrayList<>(list);
            temp.add(num);
            // 确定加进去再加入结果，防重复
            res.add(temp);
            // 当前元素确定加入的情况下，加入后面其他的元素，能确保不重复，被跳过的元素即为没被加入的元素
            for (int j = i + 1; j < n; j++) {
                subSets(res, j, temp, nums);
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
