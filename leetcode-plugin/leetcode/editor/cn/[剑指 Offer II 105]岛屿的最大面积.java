package leetcode.editor.cn;//给定一个由 0 和 1 组成的非空二维数组 grid ，用来表示海洋岛屿地图。
//
// 一个 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在水平或者竖直方向上相邻。你可以假设 grid 的四个边缘都被 
//0（代表水）包围着。 
//
// 找到给定的二维数组中最大的岛屿面积。如果没有岛屿，则返回面积为 0 。 
//
// 
//
// 示例 1: 
//
// 
//
// 
//输入: grid = [[0,0,1,0,0,0,0,1,0,0,0,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,1,1,0,1
//,0,0,0,0,0,0,0,0],[0,1,0,0,1,1,0,0,1,0,1,0,0],[0,1,0,0,1,1,0,0,1,1,1,0,0],[0,0,0
//,0,0,0,0,0,0,0,1,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,0,0,0,0,0,0,1,1,0,0,0,0]]
//输出: 6
//解释: 对于上面这个给定矩阵应返回 6。注意答案不应该是 11 ，因为岛屿只能包含水平或垂直的四个方向的 1 。 
//
// 示例 2: 
//
// 
//输入: grid = [[0,0,0,0,0,0,0,0]]
//输出: 0 
//
// 
//
// 提示： 
//
// 
// m == grid.length 
// n == grid[i].length 
// 1 <= m, n <= 50 
// grid[i][j] is either 0 or 1 
// 
//
// 
//
// 注意：本题与主站 695 题相同： https://leetcode-cn.com/problems/max-area-of-island/ 
// Related Topics 深度优先搜索 广度优先搜索 并查集 数组 矩阵 
// 👍 3 👎 0


import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution_剑指_105_1 {

    public int maxAreaOfIsland(int[][] grid) {
        int maxArea = 0;
        int m = grid.length;
        int n = grid[0].length;
        Set<Integer> visited = new HashSet<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && !visited.contains(i * n + j)) {
                    int area = getArea(grid, i, j, visited);
                    maxArea = Math.max(maxArea, area);
                }
            }
        }
        return maxArea;

    }

    private int getArea(int[][] grid, int i, int j, Set<Integer> visited) {
        Deque<int[]> deque = new ArrayDeque<>();
        int m = grid.length;
        int n = grid[0].length;
        deque.offerLast(new int[]{i, j});
        visited.add(i * n + j);
        int[] dx = new int[]{1, -1, 0, 0};
        int[] dy = new int[]{0, 0, 1, -1};
        int area = 0;
        while (!deque.isEmpty()) {
            int[] index = deque.pollFirst();
            area++;
            for (int k = 0; k < 4; k++) {
                int x = index[0] + dx[k];
                int y = index[1] + dy[k];
                if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1 && !visited.contains(x * n + y)) {
                    visited.add(x * n + y);
                    deque.offerLast(new int[]{x, y});
                }
            }
        }
        return area;
    }
}

class Solution_剑指_105 {

    public int maxAreaOfIsland(int[][] grid) {
        int maxArea = 0;
        int m = grid.length;
        int n = grid[0].length;
        Set<Integer> visited = new HashSet<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && !visited.contains(i * n + j)) {
                    int area = getArea(grid, i, j, visited);
                    maxArea = Math.max(maxArea, area);
                }
            }
        }
        return maxArea;

    }

    private int getArea(int[][] grid, int i, int j, Set<Integer> visited) {
        // 注意初始值是1
        int area = 1;
        int m = grid.length;
        int n = grid[0].length;
        // 注意这里而不是循环添加set
        visited.add(i * n + j);
        int[] dx = new int[]{1, -1, 0, 0};
        int[] dy = new int[]{0, 0, 1, -1};
        for (int k = 0; k < 4; k++) {
            int x = i + dx[k];
            int y = j + dy[k];
            // 注意这里判断的是x,y，不是i,j
            // 注意别少了grid[x][y] == 1
            if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1 && !visited.contains(x * n + y)) {
                area += getArea(grid, x, y, visited);
            }
        }
        return area;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
