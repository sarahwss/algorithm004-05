# 颠倒二进制位
# 第一遍
class Solution_39:
    # @param n, an integer
    # @return an integer
    def reverseBits(self, n):
        ans, MASK = 0, 1
        for i in range(32):
            if n & MASK:
                ans |= 1 << (31 - i)
            MASK <<= 1
        return ans 