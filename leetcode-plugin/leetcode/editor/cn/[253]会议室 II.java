//给你一个会议时间安排的数组 intervals ，每个会议时间都会包括开始和结束的时间 intervals[i] = [starti, endi] ，返回 
//所需会议室的最小数量 。 
//
// 
//
// 示例 1： 
//
// 
//输入：intervals = [[0,30],[5,10],[15,20]]
//输出：2
// 
//
// 示例 2： 
//
// 
//输入：intervals = [[7,10],[2,4]]
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= intervals.length <= 10⁴ 
// 0 <= starti < endi <= 10⁶ 
// 
// Related Topics 贪心 数组 双指针 前缀和 排序 堆（优先队列） 👍 511 👎 0


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(x -> x[0]));
        //        for (int i = 0, intervalsLength = intervals.length; i < intervalsLength; i++) {
        //            System.out.print(Arrays.toString(intervals[i]) + " ");
        //        }
        // 包含时间i的会议数
        PriorityQueue<Integer> rights = new PriorityQueue<>();
        rights.offer(intervals[0][1]);

        int res = 1;
        // 从1开始看冲突
        for (int i = 1; i < intervals.length; i++) {
            int start = intervals[i][0];
            // 不冲突的时间点，越靠前越小
            while (rights.peek() != null && rights.peek() <= start) {
                rights.poll();
            }
            res = Math.max(res, rights.size() + 1);
            // 冲突的时间右端
            rights.offer(intervals[i][1]);

        }
        return res;

    }
}
//leetcode submit region end(Prohibit modification and deletion)
