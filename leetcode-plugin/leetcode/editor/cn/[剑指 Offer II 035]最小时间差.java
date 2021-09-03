package leetcode.editor.cn;//给定一个 24 小时制（小时:分钟 "HH:MM"）的时间列表，找出列表中任意两个时间的最小时间差并以分钟数表示。
//
// 
//
// 示例 1： 
//
// 
//输入：timePoints = ["23:59","00:00"]
//输出：1
// 
//
// 示例 2： 
//
// 
//输入：timePoints = ["00:00","23:59","00:00"]
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 2 <= timePoints <= 2 * 104 
// timePoints[i] 格式为 "HH:MM" 
// 
//
// 
//
// 注意：本题与主站 539 题相同： https://leetcode-cn.com/problems/minimum-time-difference/ 
// Related Topics 数组 数学 字符串 排序 
// 👍 0 👎 0


import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution_剑指035 {

    public int findMinDifference(List<String> timePoints) {
        boolean[] mins = new boolean[1440];
        for (String s : timePoints) {
            String[] ss = s.split(":");
            int min = Integer.parseInt(ss[0]) * 60 + Integer.parseInt(ss[1]);
            if (mins[min]) {
                return 0;
            }
            mins[min] = true;
        }
        return findMinDifference(mins);

    }

    private int findMinDifference(boolean[] mins) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int pre = -1;
        int minDiff = 1440;
        for (int i = 0; i < 1440; i++) {
            if (mins[i]) {
                if (pre >= 0) {
                    minDiff = Math.min(i - pre, minDiff);
                }
                pre = i;
                min = Math.min(i, min);
                max = Math.max(i, max);
            }
        }
        // 第一个时间可能是第二天的
        return Math.min(minDiff, min + 1440 - max);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
