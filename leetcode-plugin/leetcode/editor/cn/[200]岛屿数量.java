package leetcode.editor.cn;//给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
//
// 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。 
//
// 此外，你可以假设该网格的四条边均被水包围。 
//
// 
//
// 示例 1： 
//
// 
//输入：grid = [
//  ["1","1","1","1","0"],
//  ["1","1","0","1","0"],
//  ["1","1","0","0","0"],
//  ["0","0","0","0","0"]
//]
//输出：1
// 
//
// 示例 2： 
//
// 
//输入：grid = [
//  ["1","1","0","0","0"],
//  ["1","1","0","0","0"],
//  ["0","0","1","0","0"],
//  ["0","0","0","1","1"]
//]
//输出：3
// 
//
// 
//
// 提示： 
//
// 
// m == grid.length 
// n == grid[i].length 
// 1 <= m, n <= 300 
// grid[i][j] 的值为 '0' 或 '1' 
// 
// Related Topics 深度优先搜索 广度优先搜索 并查集 数组 矩阵 
// 👍 1212 👎 0


import java.util.ArrayDeque;
import java.util.Deque;

//leetcode submit region begin(Prohibit modification and deletion)
// BFS
class Solution_200_2 {

    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    grid[i][j] = '0';
                    deque.offerFirst(i * n + j);
                    // 对每个元素进行BFS
                    BFS(deque, m, n, grid);
                    //不需要clear队列，队列此时一定为空
                }
            }
        }
        return count;
    }

    void BFS(Deque<Integer> deque, int m, int n, char[][] grid) {
        int[] dx = new int[]{-1, 1, 0, 0};
        int[] dy = new int[]{0, 0, -1, 1};
        while (!deque.isEmpty()) {
            Integer index = deque.pollLast();
            int i = index / n;
            int j = index % n;
            // 这里不需要判断是否为0，前面已经修改为0
            for (int k = 0; k < 4; k++) {
                int x = i + dx[k];
                int y = j + dy[k];
                if (x < 0 || x >= m || y < 0 || y >= n) {
                    continue;
                }
                if (grid[x][y] == '1') {
                    grid[x][y] = '0';
                    deque.offerFirst(x * n + y);
                }
            }
        }
    }
}

// DFS
class Solution_200_1 {

    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 注意别忘了判断，减少多余递归
                if (grid[i][j] == '1') {
                    count++;
                    DFS(grid, i, j, m, n);
                }
            }
        }
        return count;
    }

    void DFS(char[][] grid, int i, int j, int m, int n) {
        if (grid[i][j] == '0') {
            return;
        }
        grid[i][j] = '0';
        int[] dx = new int[]{-1, 1, 0, 0};
        int[] dy = new int[]{0, 0, -1, 1};
        for (int k = 0; k < 4; k++) {
            int x = i + dx[k];
            int y = j + dy[k];
            // 注意这里有等号
            if (x < 0 || x >= m || y < 0 || y >= n) {
                continue;
            }
            DFS(grid, x, y, m, n);
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution_200_1().numIslands(
                new char[][]{{'1', '1', '0', '0', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '1', '0', '0'},
                        {'0', '0', '0', '1', '1'}}));
    }
}

class Solution_200 {

    class UnionFind {

        int[] parent;

        int[] rank;

        int count;

        UnionFind(char[][] grid) {
            int m = grid.length;
            int n = grid[0].length;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == '1') {
                        count++;
                    }
                }
            }
            parent = new int[m * n];
            rank = new int[m * n];
            for (int i = 0; i < m * n; i++) {
                parent[i] = i;
            }
        }

        int findRoot(int p) {
            while (p != parent[p]) {
                parent[p] = parent[parent[p]];
                p = parent[p];
            }
            return p;

        }

        void union(int p, int q) {
            int parentP = findRoot(p);
            int parentQ = findRoot(q);
            //            System.out.println(p + " " + q + " " + parentP + " " + parentQ + " " + count + " " + (count - 1));
            if (parentP != parentQ) {
                if (rank[parentP] < rank[parentQ]) {
                    // 注意数组下标是parentP，不是p
                    parent[parentP] = parentQ;
                } else if (rank[parentP] > rank[parentQ]) {
                    parent[parentQ] = parentP;
                } else {
                    parent[parentQ] = parentP;
                    rank[parentP]++;
                }
                count--;
            }

        }

    }

    public int numIslands(char[][] grid) {
        int m = grid.length;
        if (m == 0) {
            return 0;
        }
        int n = grid[0].length;
        if (n == 0) {
            return 0;
        }
        UnionFind unionFind = new UnionFind(grid);
        int[] rows = new int[]{1, -1, 0, 0};
        int[] cols = new int[]{0, 0, 1, -1};

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //                System.out.println("(" + i + "," + j + ")");
                if (grid[i][j] == '1') {
                    for (int k = 0; k < 4; k++) {
                        int a = i + rows[k];
                        int b = j + cols[k];
                        if (a < 0 || a >= m || b < 0 || b >= n) {
                            continue;
                        }
                        if (grid[a][b] == '1') {
                            //                            System.out.println("union (" + a + "," + b + ")" + ", (" + i + "," + j + ")");
                            unionFind.union(i * n + j, a * n + b);
                        }
                    }
                }
            }
        }
        return unionFind.count;
    }

    public static void main(String[] args) {
        System.out.println(new Solution_200().numIslands(
                new char[][]{{'1', '1', '1', '1', '0'}, {'1', '1', '0', '1', '0'}, {'1', '1', '0', '0', '0'},
                        {'0', '0', '0', '0', '0'}}));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
