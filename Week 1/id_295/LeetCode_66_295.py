from typing import List


# class Solution_39:
#     def plusOne(self, digits: List[int]) -> List[int]:
#         n = ''.join(map(str, digits))
#         return [int(x) for x in str(int(n) + 1)]

class Solution_39:
    def plusOne(self, digits: List[int]) -> List[int]:
        n = len(digits) - 1
        for i in range(n, -1, -1):
            if digits[i] < 9:
                digits[i] += 1
                return digits
            digits[i] = 0
        res = (n + 2) * [0]
        res[0] = 1
        return res


if __name__ == '__main__':
    print(Solution_39().plusOne([1, 2, 3]))
