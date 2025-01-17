"""
给定两个单词（beginWord 和 endWord）和一个字典 wordList，找出所有从 beginWord 到 endWord 的最短转换序列。转换需遵循如下规则：

每次转换只能改变一个字母。
转换过程中的中间单词必须是字典中的单词。
说明:

如果不存在这样的转换序列，返回一个空列表。
所有单词具有相同的长度。
所有单词只由小写字母组成。
字典中不存在重复的单词。
你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
示例 1:

输入:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]

输出:
[
  ["hit","hot","dot","dog","cog"],
  ["hit","hot","lot","log","cog"]
]
示例 2:

输入:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]

输出: []

解释: endWord "cog" 不在字典中，所以不存在符合要求的转换序列。
"""
from collections import defaultdict
from typing import List


class Solution_39:
    def ladderLength(self, beginWord: str, endWord: str, wordList: List[str]) -> List[List[str]]:
        return []


if __name__ == '__main__':
    solution = Solution_39()
    begin_word = "hit"
    end_word = "cog"
    word_list = ["hot", "dot", "dog", "lot", "log", "cog"]
    result = solution.ladderLength(begin_word, end_word, word_list)
    print(result)
