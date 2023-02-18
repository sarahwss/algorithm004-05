from typing import List


class Solution_39:
    def plusOne(self, digits: List[int]) -> List[int]:
        digits = ''.join(map(str, digits))
        return list(map(int, str(int(digits)+1)))