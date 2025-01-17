#
# @lc app=leetcode.cn id=127 lang=python3
#
# [127] 单词接龙
#
# https://leetcode-cn.com/problems/word-ladder/description/
#
# algorithms
# Medium (37.07%)
# Likes:    152
# Dislikes: 0
# Total Accepted:    13.3K
# Total Submissions: 35.4K
# Testcase Example:  '"hit"\n"cog"\n["hot","dot","dog","lot","log","cog"]'
#
# 给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord
# 的最短转换序列的长度。转换需遵循如下规则：
# 
# 
# 每次转换只能改变一个字母。
# 转换过程中的中间单词必须是字典中的单词。
# 
# 
# 说明:
# 
# 
# 如果不存在这样的转换序列，返回 0。
# 所有单词具有相同的长度。
# 所有单词只由小写字母组成。
# 字典中不存在重复的单词。
# 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
# 
# 
# 示例 1:
# 
# 输入:
# beginWord = "hit",
# endWord = "cog",
# wordList = ["hot","dot","dog","lot","log","cog"]
# 
# 输出: 5
# 
# 解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
# ⁠    返回它的长度 5。
# 
# 
# 示例 2:
# 
# 输入:
# beginWord = "hit"
# endWord = "cog"
# wordList = ["hot","dot","dog","lot","log"]
# 
# 输出: 0
# 
# 解释: endWord "cog" 不在字典中，所以无法进行转换。
# 
#

# @lc code=start
from collections import defaultdict
class Solution_39:
    def ladderLength(self, beginWord: str, endWord: str, wordList: List[str]) -> int:
        if endWord not in wordList:
            return 0
        # 预处理
        newWordDict = defaultdict(list)
        L = len(beginWord)
        for word in wordList:
            for i in range(L):
                newWordDict[word[:i] + "*" + word[i+1:]].append(word)
                
        queue = [beginWord]
        res = 2
        visited = {beginWord: True} # 防止出现环
        while queue:
            
            sub_queue = []
            for word in queue:
                
                #word = queue.pop()
                
                # next
                for i in range(L):
                    iner_asterisk = word[:i] + "*" + word[i+1:]
                    for inListWord in newWordDict[iner_asterisk]:
                        if inListWord == endWord:
                            return res
                        if inListWord not in visited:
                            visited[inListWord] = True
                            sub_queue.append(inListWord)
            
            res += 1
            queue = sub_queue
        
        return 0
# @lc code=end

