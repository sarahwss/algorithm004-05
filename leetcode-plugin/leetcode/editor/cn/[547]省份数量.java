package leetcode.editor.cn;//
//
// 有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连
//。
//
// 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
//
// 给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而
//isConnected[i][j] = 0 表示二者不直接相连。
//
// 返回矩阵中 省份 的数量。
//
//
//
// 示例 1：
//
//
//输入：isConnected = [[1,1,0],[1,1,0],[0,0,1]]
//输出：2
//
//
// 示例 2：
//
//
//输入：isConnected = [[1,0,0],[0,1,0],[0,0,1]]
//输出：3
//
//
//
//
// 提示：
//
//
// 1 <= n <= 200
// n == isConnected.length
// n == isConnected[i].length
// isConnected[i][j] 为 1 或 0
// isConnected[i][i] == 1
// isConnected[i][j] == isConnected[j][i]
//
//
//
// Related Topics 深度优先搜索 广度优先搜索 并查集 图
// 👍 568 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution_547 {

    class UnionFind {

        int count;

        int[] root;

        int[] rank;

        UnionFind(int[][] isConnected) {
            int m = isConnected.length;
            root = new int[m];
            rank = new int[m];
            count = m;
            for (int i = 0; i < m; i++) {
                root[i] = i;
            }
        }


        int findRoot(int p) {
            while (p != root[p]) {
                root[p] = root[root[p]];
                p = root[p];
            }
            return p;
        }

        void union(int p, int q) {
            int r1 = findRoot(p);
            int r2 = findRoot(q);
            if (r1 != r2) {
                //                System.out.println(p + " " + q + " " + r1 + " " + r2 + " " + count + " " + (count - 1));
                if (rank[r1] < rank[r2]) {
                    root[r1] = r2;
                } else if (rank[r1] > rank[r2]) {
                    root[r2] = r1;
                } else {
                    root[r2] = r1;
                    rank[r1]++;
                }
                count--;
            }
        }

    }

    public int findCircleNum(int[][] isConnected) {
        int m = isConnected.length;
        if (m == 0) {
            return 0;
        }
        int[] rows = new int[]{-1, 1, 0, 0};
        int[] cols = new int[]{0, 0, -1, 1};
        UnionFind unionFind = new UnionFind(isConnected);
        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j < m; j++) {
                if (isConnected[i][j] == 1) {
                    //                    System.out.println("(" + i + "," + j + ")");
                    unionFind.union(i, j);
                }
            }
        }
        return unionFind.count;
    }

    public static void main(String[] args) {
        System.out.println(
                new Solution_547().findCircleNum(new int[][]{{1, 0, 0, 1}, {0, 1, 1, 0}, {0, 1, 1, 1}, {1, 0, 1, 1}}));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
