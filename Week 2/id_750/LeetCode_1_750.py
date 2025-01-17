"""
给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。

你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。

示例:

给定 nums = [2, 7, 11, 15], target = 9

因为 nums[0] + nums[1] = 2 + 7 = 9
所以返回 [0, 1]
"""
from typing import List


class Solution_39:
    # 方法1：暴力法
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        for i in range(len(nums)):
            for j in range(i, len(nums)):
                if target - nums[i] == nums[j]:
                    return [i, j]

    # 方法2：hash
    def twoSum2(self, nums: List[int], target: int) -> List[int]:
        num_dict = {}
        for i, n in enumerate(nums):
            m = target - n
            if m in num_dict:
                return [num_dict[m], i]
            else:
                num_dict[n] = i


if __name__ == '__main__':
    s = Solution_39()
    nums = [2, 7, 11, 15]
    target = 9
    result = s.twoSum(nums, target)
    result2 = s.twoSum2(nums, target)
    print(result2)