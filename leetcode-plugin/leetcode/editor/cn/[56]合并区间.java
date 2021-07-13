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
// Related Topics 数组 排序 
// 👍 1002 👎 0


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution_56 {

    public int[][] merge(int[][] intervals) {
        int n = intervals.length;
        List<int[]> res = new ArrayList<>(n);
        // 按第一个元素插入排序
        Arrays.sort(intervals, Comparator.comparingInt(x -> x[0]));
        res.add(intervals[0]);
        // 从前往后合并
        for (int i = 1; i < n; i++) {
            int[] temp = intervals[i];
            int[] r = res.get(res.size() - 1);
            //            System.out.println("a " + Arrays.toString(temp));
            //            System.out.println("b " + index + " " + Arrays.toString(res[index]));
            // 区间最靠后，插入最后
            if (temp[0] > r[1]) {
                res.add(temp);
                // 左合并，因为已经排序，一定能找到
            } else {
                if (temp[1] > r[1]) {
                    r[1] = temp[1];
                }
            }
        }
        return res.toArray(new int[res.size()][]);
    }

    public static void main(String[] args) {
        Arrays.stream(new Solution_56().merge(new int[][]{{1, 4}, {0, 1}}))
                .forEach(x -> System.out.println(Arrays.toString(x)));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
