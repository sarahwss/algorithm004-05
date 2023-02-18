class Solution_39:
    def majorityElement(self, nums: List[int]) -> int:
        # iterative method
        counter = collections.Counter(nums)
        low_bound = len(nums) // 2
        for key, value in counter.items():
            if value > low_bound:
                return key
