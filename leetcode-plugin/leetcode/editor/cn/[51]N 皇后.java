package leetcode.editor.cn;//n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
//
// 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。 
//
// 
// 
// 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 4
//输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
//解释：如上图所示，4 皇后问题存在两个不同的解法。
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：[["Q"]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 9 
// 皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。 
// 
// 
// 
// Related Topics 数组 回溯 
// 👍 929 👎 0


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution_51 {

    public List<List<String>> solveNQueens(int n) {
        boolean[] col = new boolean[n];
        boolean[] pie = new boolean[2 * n];
        boolean[] na = new boolean[2 * n];
        List<List<String>> res = new LinkedList<>();
        solveNQueens(n, 0, col, pie, na, new LinkedList<>(), res);
        return res;


    }

    void solveNQueens(int n, int i, boolean[] col, boolean[] pie, boolean[] na, List<Integer> path,
                      List<List<String>> res) {
        if (i == n) {
            res.add(generate(path));
            return;
        }
        for (int j = 0; j < n; j++) {
            if (col[j] || pie[j + i] || na[i - j + n]) {
                continue;
            }
            //            System.out.println(i + " " + j);
            col[j] = true;
            pie[i + j] = true;
            na[i - j + n] = true;
            List<Integer> list = new LinkedList<>(path);
            list.add(j);
            solveNQueens(n, i + 1, col, pie, na, list, res);
            col[j] = false;
            pie[i + j] = false;
            na[i - j + n] = false;
        }

    }

    private List<String> generate(List<Integer> path) {
        List<String> res = new LinkedList<>();
        int n = path.size();
        char[] arr = new char[n];
        Arrays.fill(arr, '.');
        for (int index : path) {
            arr[index] = 'Q';
            res.add(new String(arr));
            arr[index] = '.';
        }
        return res;
    }
}

class Solution_51_1 {

    public List<List<String>> solveNQueens(int n) {
        int col = 0, pie = 0, na = 0;
        List<List<String>> res = new LinkedList<>();
        solveNQueens(n, col, pie, na, new LinkedList<>(), res);
        return res;


    }

    void solveNQueens(int n, int col, int pie, int na, List<Integer> path, List<List<String>> res) {
        if (path.size() == n) {
            res.add(generate(path));
            return;
        }
        // 取得所有可用列
        int cols = (~(col | pie | na)) & ((1 << n) - 1);
        while (cols != 0) {
            // 取得单个可用的列
            int c = cols & (-cols);
            // 去掉最后一个1
            cols = cols & (cols - 1);
            // 别忘了申请新数组
            List<Integer> temp = new ArrayList(path);
            temp.add(c);
            solveNQueens(n, col | c, (pie | c) << 1, (na | c) >> 1, temp, res);
        }

    }

    private List<String> generate(List<Integer> path) {
        List<String> res = new LinkedList<>();
        int n = path.size();
        char[] arr = new char[n];
        Arrays.fill(arr, '.');
        for (int p : path) {
            int index = n - Integer.toBinaryString(p).length();
            arr[index] = 'Q';
            res.add(new String(arr));
            arr[index] = '.';
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Solution_51_1().solveNQueens(4));
    }
}
//leetcode submit region end(Prohibit modification and deletion)

