"""
实现 int sqrt(int x) 函数。

计算并返回 x 的平方根，其中 x 是非负整数。

由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。

示例 1:

输入: 4
输出: 2
示例 2:

输入: 8
输出: 2
说明: 8 的平方根是 2.82842...,
     由于返回类型是整数，小数部分将被舍去。
"""


class Solution_39:
    def mySqrt(self, x: int) -> int:
        left = 0
        right = x // 2 + 1
        while left < right:
            mid = (left + right + 1) >> 1
            square = mid * mid

            if square > x:
                right = mid - 1
            else:
                left = mid
        return left

    def mySqrt2(self, x: int) -> int:
        if x == 0 or x == 1:
            return x
        left = 1
        right = x
        while left < right:
            mid = left + (right - left) // 2
            square = mid * mid

            if square > x:
                right = mid - 1
            else:
                left = mid + 1
        return right


if __name__ == '__main__':
    solution = Solution_39()

    x = 8
    result = solution.mySqrt2(x)
    print(result)
