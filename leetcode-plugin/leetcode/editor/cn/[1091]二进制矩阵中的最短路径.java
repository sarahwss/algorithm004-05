package leetcode.editor.cn;//给你一个 n x n 的二进制矩阵 grid 中，返回矩阵中最短 畅通路径 的长度。如果不存在这样的路径，返回 -1 。
//
// 二进制矩阵中的 畅通路径 是一条从 左上角 单元格（即，(0, 0)）到 右下角 单元格（即，(n - 1, n - 1)）的路径，该路径同时满足下述要求
//： 
//
// 
// 路径途经的所有单元格都的值都是 0 。 
// 路径中所有相邻的单元格应当在 8 个方向之一 上连通（即，相邻两单元之间彼此不同且共享一条边或者一个角）。 
// 
//
// 畅通路径的长度 是该路径途经的单元格总数。 
//
// 
//
// 示例 1： 
//
// 
//输入：grid = [[0,1],[1,0]]
//输出：2
// 
//
// 示例 2： 
//
// 
//输入：grid = [[0,0,0],[1,1,0],[1,1,0]]
//输出：4
// 
//
// 示例 3： 
//
// 
//输入：grid = [[1,0,0],[1,1,0],[1,1,0]]
//输出：-1
// 
//
// 
//
// 提示： 
//
// 
// n == grid.length 
// n == grid[i].length 
// 1 <= n <= 100 
// grid[i][j] 为 0 或 1 
// 
// Related Topics 广度优先搜索 数组 矩阵 
// 👍 106 👎 0


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution_1091 {

    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        // 判断第一个元素同while内部for循环加元素前的判断作用相同，判断最后一个元素因为最后一个元素不是0的话，结果会迟迟找不到，可能会超时
        if (grid[0][0] != 0 || grid[n - 1][n - 1] != 0) {
            return -1;
        }
        // 如果没有中间元素，就不需要遍历了
        if (n <= 2) {
            return n;
        }
        int[] priority = new int[n * n];
        for (int i = 0; i < n * n; i++) {
            priority[i] = Integer.MAX_VALUE;
        }
        int[] visited = new int[n * n];
        // 为什么不用int[]作为key，因为int[]equals不符合需要
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(x -> priority[x]));
        // 优先队列的优先级，使用数组因为n最大为100
        priority[0] = getPriority(n, 0, 0, 0);
        queue.offer(0);
        visited[0] = 1;
        int[] dx = new int[]{-1, 1, 0, 0, -1, 1, -1, 1};
        int[] dy = new int[]{0, 0, -1, 1, 1, 1, -1, -1};
        while (!queue.isEmpty()) {
            Integer d = queue.poll();
            int a = d / n;
            int b = d % n;
            //            System.out.println(a + " " + b + " " + visited[d]);
            int step = visited[d];

            for (int i = 0; i < 8; i++) {
                int x = a + dx[i];
                int y = b + dy[i];
                if (x < 0 || x >= n || y < 0 || y >= n) {
                    continue;
                }
                // 在加入队列前判断，防止找到结果前像队列加入过多元素，同时因为第一个不用判断（需要前面处理特殊返回值），放在这里也更合理，不过放在外层也能实现结果
                if (x == n - 1 && y == n - 1) {
                    //                    System.out.println(queue.size() + "----------------");
                    //                    while (!queue.isEmpty()) {
                    //                        int nn = queue.poll();
                    //                        System.out.println(nn / n + " " + nn % n + " " + visited[nn] + " " + priority[nn]);
                    //                    }
                    return step + 1;
                }
                int idx = x * n + y;
                // 如果访问值较大，可以重新加入，这可能造成队列重复元素，但优先级大的可能排的非常靠后
                //                if ((grid[x][y] != 0 || visited[idx] != 0) && visited[idx] <= step + 1) {
                //                    continue;
                //                }
                if (grid[x][y] == 0) {
                    if (visited[idx] == 0 || visited[idx] > step + 1) {
                        //                System.out.println(x + " " + y + " " + visited[idx] + " " + priority[idx]);
                        // 先修改priority后再offer，方便重新排序
                        priority[idx] = getPriority(n, step, x, y);
                        queue.offer(idx);
                        visited[idx] = step + 1;
                    }
                }
            }
        }
        return -1;
    }

    int getPriority(int n, int step, int i, int j) {
        return step + 1 +
                // 不能用sqrt,斜路经和直路经部署一样，别忘了abs，汉明距离
                Math.max(Math.abs(n - 1 - i), Math.abs(n - 1 - j));
    }

    public static void main(String[] args) {
        System.out.println(new Solution_1091().shortestPathBinaryMatrix(
                new int[][]{{0, 1, 1, 0, 0, 0}, {0, 1, 0, 1, 1, 0}, {0, 1, 1, 0, 1, 0}, {0, 0, 0, 1, 1, 0},
                        {1, 1, 1, 1, 1, 0}, {1, 1, 1, 1, 1, 0}}));
        //        System.out.println(new int[]{1, 2}.equals(new int[]{1, 2}));
        //        System.out.println(new Integer[]{1, 2}.equals(new Integer[]{1, 2}));
        //        int[] priority = new int[]{1, 2, 3};
        //        PriorityQueue<Integer> q = new PriorityQueue<>(Comparator.comparing(x -> priority[x]));
        //        for (int i = 0; i < 3; i++) {
        //            q.offer(i);
        //        }
        //        q.offer(2);
        //        while (!q.isEmpty()) {
        //            System.out.println(q.poll());
        //        }

    }
}
//leetcode submit region end(Prohibit modification and deletion)
