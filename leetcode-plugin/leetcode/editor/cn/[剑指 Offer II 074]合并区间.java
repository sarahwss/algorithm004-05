package leetcode.editor.cn;//以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返
//回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。 
//
// 
//
// 示例 1： 
//
// 
//输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
//输出：[[1,6],[8,10],[15,18]]
//解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
// 
//
// 示例 2： 
//
// 
//输入：intervals = [[1,4],[4,5]]
//输出：[[1,5]]
//解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。 
//
// 
//
// 提示： 
//
// 
// 1 <= intervals.length <= 104 
// intervals[i].length == 2 
// 0 <= starti <= endi <= 104 
// 
//
// 
//
// 注意：本题与主站 56 题相同： https://leetcode-cn.com/problems/merge-intervals/ 
// Related Topics 数组 排序 
// 👍 0 👎 0


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution_剑指_074 {

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);
        List<int[]> res = new ArrayList<>();
        int n = intervals.length;
        int i = 0;
        while (i < n) {
            int[] interval = intervals[i];
            int j = i + 1;
            while (j < n && intervals[j][0] <= interval[1]) {
                interval[1] = Math.max(interval[1], intervals[j][1]);
                j++;
            }
            res.add(interval);
            i = j;
        }
        int[][] arr = new int[res.size()][];
        return res.toArray(arr);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
