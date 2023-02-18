class Solution_39(object):
    def rotate(self, nums, k):
        for n in range(k):
            nums.insert(0, nums.pop())