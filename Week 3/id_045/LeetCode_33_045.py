# @author:leacoder
# @des:  二分查找 搜索旋转排序数组

class Solution_39:
    def search(self, nums: List[int], target: int) -> int:

        if not nums:
            return -1

        low , high = 0, len(nums) - 1

        while low <= high:
            mid = (low + high) // 2
            if target == nums[mid]:
                return mid

            if nums[low] <= nums[mid]:
                if nums[low] <= target <= nums[mid]:
                    high = mid - 1
                else:
                    low = mid + 1
            else:
                if nums[mid] <= target <= nums[high]:
                    low = mid + 1
                else:
                    high = mid - 1
        return -1

