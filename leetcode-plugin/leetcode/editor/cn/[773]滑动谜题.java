package leetcode.editor.cn;//在一个 2 x 3 的板上（board）有 5 块砖瓦，用数字 1~5 来表示, 以及一块空缺用 0 来表示.
//
// 一次移动定义为选择 0 与一个相邻的数字（上下左右）进行交换. 
//
// 最终当板 board 的结果是 [[1,2,3],[4,5,0]] 谜板被解开。 
//
// 给出一个谜板的初始状态，返回最少可以通过多少次移动解开谜板，如果不能解开谜板，则返回 -1 。 
//
// 示例： 
//
// 
//输入：board = [[1,2,3],[4,0,5]]
//输出：1
//解释：交换 0 和 5 ，1 步完成
// 
//
// 
//输入：board = [[1,2,3],[5,4,0]]
//输出：-1
//解释：没有办法完成谜板
// 
//
// 
//输入：board = [[4,1,2],[5,0,3]]
//输出：5
//解释：
//最少完成谜板的最少移动次数是 5 ，
//一种移动路径:
//尚未移动: [[4,1,2],[5,0,3]]
//移动 1 次: [[4,1,2],[0,5,3]]
//移动 2 次: [[0,1,2],[4,5,3]]
//移动 3 次: [[1,0,2],[4,5,3]]
//移动 4 次: [[1,2,0],[4,5,3]]
//移动 5 次: [[1,2,3],[4,5,0]]
// 
//
// 
//输入：board = [[3,2,4],[1,5,0]]
//输出：14
// 
//
// 提示： 
//
// 
// board 是一个如上所述的 2 x 3 的数组. 
// board[i][j] 是一个 [0, 1, 2, 3, 4, 5] 的排列. 
// 
// Related Topics 广度优先搜索 数组 矩阵 
// 👍 223 👎 0


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution_773 {

    String target = "123450";

    public static int[][] dist =
            {{0, 1, 2, 1, 2, 3}, {1, 0, 1, 2, 1, 2}, {2, 1, 0, 3, 2, 1}, {1, 2, 3, 0, 1, 2}, {2, 1, 2, 1, 0, 1},
                    {3, 2, 1, 2, 1, 0}};

    public int slidingPuzzle(int[][] board) {

        int[][] neighbors = {{1, 3}, {0, 2, 4}, {1, 5}, {0, 4}, {1, 3, 5}, {2, 4}};


        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append(board[i][j]);
            }
        }
        String initial = sb.toString();
        // 循环遍历的条件是需要遍历，排序不需要遍历的情况
        if (target.equals(initial)) {
            return 0;
        }
        Map<String, Integer> priority = new HashMap<>();
        PriorityQueue<String> queue =
                new PriorityQueue<>(Comparator.comparingInt(x -> priority.getOrDefault(x, Integer.MAX_VALUE)));
        Map<String, Integer> visited = new HashMap<>();
        priority.put(initial, getPriority(initial));
        queue.offer(initial);
        visited.put(initial, 0);
        while (!queue.isEmpty()) {
            String s = queue.poll();
//            System.out.println(s + " " + visited.get(s) + " " + priority.get(s) + "----------");
            char[] arr = s.toCharArray();
            int index = s.indexOf('0');
            int count = visited.get(s);
            for (int i : neighbors[index]) {
                String ss = swap(arr, index, i);
//                System.out.println("    " + ss + " " + visited.get(ss) + " " + priority.get(ss));
                if (visited.get(ss) == null) {
                    if (ss.equals(target)) {
                        return visited.get(s) + 1;
                    }
//                    System.out.println("         " + ss + " " + (count + 1) + " " + (getPriority(ss) + count + 1));
                    priority.put(ss, getPriority(ss) + count + 1);
                    queue.offer(ss);
                    visited.put(ss, count + 1);
                }
                swap(arr, index, i);
            }

        }
        return -1;

    }

    String swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        return new String(arr);
    }

    int getPriority(String s) {
        int count = 0;
        for (int i = 0; i < 6; i++) {
            char c = s.charAt(i);
            // 很重要，0不计算距离
            if (c != '0') {
                // 这里不是计算汉明距离，是根据序号和位置计算移动距离
                count += dist[i][s.charAt(i) - '1'];
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println("res " + new Solution_773().slidingPuzzle(new int[][]{{5, 3, 2}, {0, 4, 1}}));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
