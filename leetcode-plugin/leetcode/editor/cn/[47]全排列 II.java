package leetcode.editor.cn;//给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,1,2]
//输出：
//[[1,1,2],
// [1,2,1],
// [2,1,1]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,3]
//输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 8 
// -10 <= nums[i] <= 10 
// 
// Related Topics 数组 回溯 
// 👍 754 👎 0


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution_47_1 {

    public List<List<Integer>> permuteUnique(int[] nums) {
        int n = nums.length;
        // nums需要先排下序
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        boolean[] visited = new boolean[n];
        permuteUnique(nums, n, res, visited, new ArrayList<>());
        return new ArrayList<>(res);
    }

    void permuteUnique(int[] nums, int n, List<List<Integer>> res, boolean[] visited, List<Integer> list) {
        if (list.size() == n) {
            res.add(new ArrayList<>(list));
            // 不要少了return
            return;
        }
        for (int i = 0; i < n; i++) {
            // 如果相同的前面使用过，可以使用当前；如果相同的前面没有使用过，则跳过当前（到最后一个重复元素才可已使用，剩余重复元素之后正常使用）
            if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) {
                continue;
            }
            // 不要少了这个判断
            if (!visited[i]) {
                int num = nums[i];
                list.add(num);
                visited[i] = true;
                permuteUnique(nums, n, res, visited, list);
                list.remove(list.size() - 1);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution_47_1().permuteUnique(new int[]{1, 1, 1, 2}));
    }
}

class Solution_47 {

    public List<List<Integer>> permuteUnique(int[] nums) {
        int n = nums.length;
        Set<List<Integer>> res = new HashSet<>();
        List<Integer> remain = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            remain.add(nums[i]);
        }
        permuteUnique(remain, res, new ArrayList<>());
        return new ArrayList<>(res);
    }

    void permuteUnique(List<Integer> remain, Set<List<Integer>> res, List<Integer> list) {
        if (remain.size() == 0) {
            res.add(new ArrayList<>(list));
        }
        int n = remain.size();
        for (int i = 0; i < n; i++) {
            int num = remain.get(i);
            list.add(num);
            remain.remove(i);
            permuteUnique(remain, res, list);
            list.remove(list.size() - 1);
            remain.add(i, num);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
