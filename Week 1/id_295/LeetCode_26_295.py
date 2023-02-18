from typing import List


class Solution_39:
    def removeDuplicates(self, nums: List[int]) -> int:
        if len(nums) == 0:
            return 0
        i = 0
        for j in range(1, len(nums)):
            if nums[j] != nums[i]:
                i += 1
                nums[i] = nums[j]
        return i + 1


if __name__ == '__main__':
    Solution_39().removeDuplicates([0, 0, 1, 1, 1, 2, 2, 3, 3, 4])
