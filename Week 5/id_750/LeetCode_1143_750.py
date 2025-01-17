"""
给定两个字符串 text1 和 text2，返回这两个字符串的最长公共子序列。

一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。两个字符串的「公共子序列」是这两个字符串所共同拥有的子序列。

若这两个字符串没有公共子序列，则返回 0。
示例 1:

输入：text1 = "abcde", text2 = "ace"
输出：3
解释：最长公共子序列是 "ace"，它的长度为 3。
示例 2:

输入：text1 = "abc", text2 = "abc"
输出：3
解释：最长公共子序列是 "abc"，它的长度为 3。
示例 3:

输入：text1 = "abc", text2 = "def"
输出：0
解释：两个字符串没有公共子序列，返回 0。
 

提示:

1 <= text1.length <= 1000
1 <= text2.length <= 1000
输入的字符串只含有小写英文字符。
"""


class Solution_39:
    def longestCommonSubsequence(self, text1: str, text2: str) -> int:
        t1 = list(text1)
        t2 = list(text2)
        t1_len = len(t1)
        t2_len = len(t2)
        dp = [[0] * (t1_len + 1)] * (t2_len + 1)
        for i in range(1, t2_len):
            for j in range(1, t1_len):
                # 如果末端相同
                if t1[i - 1] == t2[j - 1]:
                    dp[i][j] = dp[i - 1][j - 1] + 1
                else:
                    # 如果末端不同
                    dp[i][j] = max(dp[i - 1][j], dp[i][j - 1])
        return dp[t1_len][t2_len]


if __name__ == '__main__':
    text1 = "abn"
    text2 = "av"
    solution = Solution_39()
    result = solution.longestCommonSubsequence(text1, text2)

    print(result)

