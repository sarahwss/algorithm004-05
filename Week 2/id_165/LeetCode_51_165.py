# n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
#
#
#
# 上图为 8 皇后问题的一种解法。
#
# 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
#
# 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
#
# 示例:
#
# 输入: 4
# 输出: [
# [".Q..",  // 解法 1
#  "...Q",
#  "Q...",
#  "..Q."],
#
# ["..Q.",  // 解法 2
#  "Q...",
#  "...Q",
#  ".Q.."]
# ]
# 解释: 4 皇后问题存在两个不同的解法。
#
# Related Topics 回溯算法


# leetcode submit region begin(Prohibit modification and deletion)
class Solution_39(object):

    def __init__(self):

        self.cur = []
        self.res = []
        self.pie = {}
        self.na = {}

    def solveNQueen(self, n, lvl):

        if len(self.cur) >= n:
            self.res.append(self.cur[:])
            return

        for i in range(n):
            if i not in self.cur and i + lvl not in self.pie and lvl - i not in self.na:
                self.cur.append(i)
                self.pie[i + lvl] = 1
                self.na[lvl - i] = 1
                self.solveNQueen(n, lvl + 1)
                self.cur.pop()
                self.pie.pop(i + lvl)
                self.na.pop(lvl - i)

    def solveNQueens(self, n):
        """
        :type n: int
        :rtype: List[List[str]]
        """
        self.solveNQueen(n, 0)

        # format
        final = []
        for r in self.res:
            result = []
            for i in r:
                res = ['.'] * n
                res[i] = 'Q'
                result.append(''.join(res))

            final.append(result)

        return final

# leetcode submit region end(Prohibit modification and deletion)
