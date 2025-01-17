"""
给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。

示例:

输入: [0,1,0,3,12]
输出: [1,3,12,0,0]

说明:
1.必须在原数组上操作，不能拷贝额外的数组。
2.尽量减少操作次数。
"""
from typing import List


class Solution_39:
    def moveZeroes(self, nums: List[int]) -> None:
        i = 0
        for j in range(len(nums)):
            if nums[j] != 0:
                nums[i] = nums[j]
                i += 1

        for k in range(i, len(nums), 1):
            nums[k] = 0
        print(nums)


if __name__ == '__main__':
    solution = Solution_39()

    nums = [0, 1, 0, 3, 12]

    result = solution.moveZeroes(nums)

    # print(result)
