package leetcode.editor.cn;//给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充
//。
// 
// 
// 
//
// 示例 1： 
//
// 
//输入：board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X"
//,"X"]]
//输出：[["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
//解释：被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都
//会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
// 
//
// 示例 2： 
//
// 
//输入：board = [["X"]]
//输出：[["X"]]
// 
//
// 
//
// 提示： 
//
// 
// m == board.length 
// n == board[i].length 
// 1 <= m, n <= 200 
// board[i][j] 为 'X' 或 'O' 
// 
// 
// 
// Related Topics 深度优先搜索 广度优先搜索 并查集 数组 矩阵 
// 👍 559 👎 0


import javax.swing.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution_130 {

    class UnionFind {

        int[] parent;

        UnionFind(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        int find(int p) {
            int root = p;
            while (root != parent[root]) {
                root = parent[parent[root]];
            }
            while (p != parent[p]) {
                int temp = parent[p];
                parent[p] = root;
                p = temp;
            }
            return root;
        }

        void union(int p, int q) {
            int r1 = find(p);
            int r2 = find(q);
            if (r1 != r2) {
                parent[r2] = r1;
            }
        }


    }

    public void solve(char[][] board) {
        int m = board.length;
        if (m == 0) {
            return;
        }
        int n = board[0].length;
        if (n == 0) {
            return;
        }
        UnionFind unionFind = new UnionFind(m * n + 1);
        int dummy = m * n;
        int[] dx = new int[]{-1, 1, 0, 0};
        int[] dy = new int[]{0, 0, -1, 1};
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    // 边缘的o加入到dummy集合,确保parent是dummy
                    if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                        unionFind.union(dummy, i * n + j);
                        // 不在边缘，集合其它节点
                    } else {
                        for (int k = 0; k < 4; k++) {
                            int a = i + dx[k];
                            int b = j + dy[k];
                            if (a < 0 || a >= m || b < 0 || b >= n) {
                                continue;
                            }
                            if (board[a][b] == 'O') {
                                //                                System.out.println(
                                //                                        "(ij)" + i + " " + j + " " + unionFind.find(i * n + j) + ",(ab)" + a + " " + b +
                                //                                                " " + unionFind.find(a * n + b));
                                unionFind.union(a * n + b, i * n + j);
                            }
                        }
                    }
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //                System.out.println(i + " " + j + " " + unionFind.find(i * n + j));
                // 不在dummy集合的即为内部的O集合
                // 注意要和find(dummy)比较，dummy集合和root不一定是dummy
                if (board[i][j] == 'O' && unionFind.find(i * n + j) != unionFind.find(dummy)) {
                    board[i][j] = 'X';
                }
            }
        }

    }
}
//leetcode submit region end(Prohibit modification and deletion)
