# 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
#
# 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
#
#
#
# 示例:
#
# 输入："23"
# 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
#
#
# 说明:
# 尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
# Related Topics 字符串 回溯算法


# leetcode submit region begin(Prohibit modification and deletion)
class Solution_39(object):

    def __init__(self):

        self.pn = {'2': 'abc',
                   '3': 'def',
                   '4': 'ghi',
                   '5': 'jkl',
                   '6': 'mno',
                   '7': 'pqrs',
                   '8': 'tuv',
                   '9': 'wxyz'
                   }
        self.cur = ''
        self.res = []
        self.size = 0
        self.digits = None

    def letterSelect(self, digit_idx):
        if len(self.cur) == self.size:
            self.res.append(self.cur)
            return

        for letter in self.pn[self.digits[digit_idx]]:
            self.cur = self.cur + letter
            self.letterSelect(digit_idx + 1)
            self.cur = self.cur[0:-1]

    def letterCombinations(self, digits):
        """
        :type digits: str
        :rtype: List[str]
        """
        self.size = len(digits)
        if not self.size:
            return []
        self.digits = digits
        self.letterSelect(0)

        return self.res

# leetcode submit region end(Prohibit modification and deletion)
