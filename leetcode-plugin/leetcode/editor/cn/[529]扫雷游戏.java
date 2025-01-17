package leetcode.editor.cn;//让我们一起来玩扫雷游戏！
//
// 给定一个代表游戏板的二维字符矩阵。 'M' 代表一个未挖出的地雷，'E' 代表一个未挖出的空方块，'B' 代表没有相邻（上，下，左，右，和所有4个对角线）
//地雷的已挖出的空白方块，数字（'1' 到 '8'）表示有多少地雷与这块已挖出的方块相邻，'X' 则表示一个已挖出的地雷。 
//
// 现在给出在所有未挖出的方块中（'M'或者'E'）的下一个点击位置（行和列索引），根据以下规则，返回相应位置被点击后对应的面板： 
//
// 
// 如果一个地雷（'M'）被挖出，游戏就结束了- 把它改为 'X'。 
// 如果一个没有相邻地雷的空方块（'E'）被挖出，修改它为（'B'），并且所有和其相邻的未挖出方块都应该被递归地揭露。 
// 如果一个至少与一个地雷相邻的空方块（'E'）被挖出，修改它为数字（'1'到'8'），表示相邻地雷的数量。 
// 如果在此次点击中，若无更多方块可被揭露，则返回面板。 
// 
//
// 
//
// 示例 1： 
//
// 输入: 
//
//[['E', 'E', 'E', 'E', 'E'],
// ['E', 'E', 'M', 'E', 'E'],
// ['E', 'E', 'E', 'E', 'E'],
// ['E', 'E', 'E', 'E', 'E']]
//
//Click : [3,0]
//
//输出: 
//
//[['B', '1', 'E', '1', 'B'],
// ['B', '1', 'M', '1', 'B'],
// ['B', '1', '1', '1', 'B'],
// ['B', 'B', 'B', 'B', 'B']]
//
//解释:
//
// 
//
// 示例 2： 
//
// 输入: 
//
//[['B', '1', 'E', '1', 'B'],
// ['B', '1', 'M', '1', 'B'],
// ['B', '1', '1', '1', 'B'],
// ['B', 'B', 'B', 'B', 'B']]
//
//Click : [1,2]
//
//输出: 
//
//[['B', '1', 'E', '1', 'B'],
// ['B', '1', 'X', '1', 'B'],
// ['B', '1', '1', '1', 'B'],
// ['B', 'B', 'B', 'B', 'B']]
//
//解释:
//
// 
//
// 
//
// 注意： 
//
// 
// 输入矩阵的宽和高的范围为 [1,50]。 
// 点击的位置只能是未被挖出的方块 ('M' 或者 'E')，这也意味着面板至少包含一个可点击的方块。 
// 输入面板不会是游戏结束的状态（即有地雷已被挖出）。 
// 简单起见，未提及的规则在这个问题中可被忽略。例如，当游戏结束时你不需要挖出所有地雷，考虑所有你可能赢得游戏或标记方块的情况。 
// 
// Related Topics 深度优先搜索 广度优先搜索 数组 矩阵 
// 👍 237 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution_529 {

    public char[][] updateBoard(char[][] board, int[] click) {
        int i = click[0];
        int j = click[1];
        DFS(board, i, j, board.length, board[0].length);
        return board;
    }

    void DFS(char[][] board, int i, int j, int m, int n) {
        int b = board[i][j];
        // 立即返回，不会继续递归，空格的递归不会触发这个条件，因为周围没有地雷才会递归
        if (b == 'M') {
            board[i][j] = 'X';
        }
        if (b == 'E') {
            int res = 0;
            int[] dx = new int[]{-1, 1, 0, 0, -1, -1, 1, 1};
            int[] dy = new int[]{0, 0, -1, 1, -1, 1, -1, 1};
            for (int k = 0; k < 8; k++) {
                int x = i + dx[k];
                int y = j + dy[k];
                if (x < 0 || x >= m || y < 0 || y >= n) {
                    continue;
                }
                if (board[x][y] == 'M' || board[x][y] == 'X') {
                    res++;
                }
            }
            // 别忘了修改
            if (res != 0) {
                board[i][j] = (char) ('0' + res);
                // 没有相邻地雷的才递归借楼
            } else {
                // 先改成B，作为visited，否则会有死循环
                board[i][j] = 'B';
                for (int k = 0; k < 8; k++) {
                    int x = i + dx[k];
                    int y = j + dy[k];
                    if (x < 0 || x >= m || y < 0 || y >= n) {
                        continue;
                    }
                    DFS(board, x, y, m, n);
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution_529().updateBoard(
                new char[][]{{'E', 'E', 'E', 'E', 'E'}, {'E', 'E', 'M', 'E', 'E'}, {'E', 'E', 'E', 'E', 'E'},
                        {'E', 'E', 'E', 'E', 'E'}}, new int[]{3, 0}));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
