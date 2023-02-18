# n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
#
#
#
# 上图为 8 皇后问题的一种解法。
#
# 给定一个整数 n，返回 n 皇后不同的解决方案的数量。
#
# 示例:
#
# 输入: 4
# 输出: 2
# 解释: 4 皇后问题存在如下两个不同的解法。
# [
#  [".Q..",  // 解法 1
#   "...Q",
#   "Q...",
#   "..Q."],
#
#  ["..Q.",  // 解法 2
#   "Q...",
#   "...Q",
#   ".Q.."]
# ]
#
# 来源：力扣（LeetCode）
# 链接：https://leetcode-cn.com/problems/n-queens-ii
# 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。


class Solution_39:
    def totalNQueens(self, n: int) -> int:

        if n < 1: return []
        self.result = []

        self.cols = set()
        self.pie = set()
        self.na = set()

        self.DFS(n, 0, [])
        return len(self.result)

    def DFS(self, n, row, cur_state):
        if row >= n:
            self.result.append(cur_state)
            return

        for col in range(n):
            if col in self.cols or row + col in self.pie or row - col in self.na:
                continue
            # update states
            self.cols.add(col)
            self.pie.add(row + col)
            self.na.add(row - col)

            self.DFS(n, row + 1, cur_state + [col])

            # reverse status
            self.cols.remove(col)
            self.pie.remove(row + col)
            self.na.remove(row - col)
