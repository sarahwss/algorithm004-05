class Solution_39(object):
    def longestValidParentheses(self, s):
        dp, stack = [0]*(len(s)+1), []
        for i in range(len(s)):
            if s[i] == '(':
                stack.append(i)
            else:
                if stack:
                    p = stack.pop()
                    dp[i+1] = dp[p] + i - p + 1
        return max(dp)