package leetcode.editor.cn;//给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,3]
//输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [0,1]
//输出：[[0,1],[1,0]]
// 
//
// 示例 3： 
//
// 
//输入：nums = [1]
//输出：[[1]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 6 
// -10 <= nums[i] <= 10 
// nums 中的所有整数 互不相同 
// 
// Related Topics 数组 回溯 
// 👍 1464 👎 0


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution_46_1 {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        permute(nums, new ArrayList<>(), res);
        return res;
    }

    void permute(int[] nums, List<Integer> list, List<List<Integer>> res) {
        int n = nums.length;
        if (list.size() == n) {
            res.add(new ArrayList<>(list));
            // 别忘return
            return;
        }
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            if (!list.contains(num)) {
                list.add(num);
                permute(nums, list, res);
                list.remove(list.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution_46_1().permute(new int[]{1, 2, 3}));
    }
}

class Solution_46 {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        // LinkedList加快移动速度
        List<Integer> list = new LinkedList<>();
        for (int num : nums) {
            list.add(num);
        }
        permute(list, new ArrayList<>(), res);
        return res;
    }

    void permute(List<Integer> remain, List<Integer> list, List<List<Integer>> res) {
        // 剩余元素为空
        if (remain.size() == 0) {
            res.add(new ArrayList<>(list));
            // 别忘了return
            return;
        }
        for (int i = 0; i < remain.size(); i++) {
            // 从后往前移除
            int num = remain.get(i);
            remain.remove(i);
            list.add(num);
            permute(remain, list, res);
            list.remove(list.size() - 1);
            // 移除的再添加到当前位置，其余位置的会向右移动
            remain.add(i, num);
        }

    }

    public static void main(String[] args) {
        System.out.println(new Solution_46_1().permute(new int[]{1, 2, 3}));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
