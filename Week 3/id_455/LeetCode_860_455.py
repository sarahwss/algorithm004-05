#
# @lc app=leetcode.cn id=860 lang=python3
#
# [860] 柠檬水找零
#

# @lc code=start
class Solution_39:
    def lemonadeChange(self, bills: List[int]) -> bool:
        five, ten = 0, 0
        for bill in bills:
            if bill == 5:
                five += 1
            elif bill == 10:
                if five == 0: return False
                five -= 1
                ten += 1
            else:
                if five and ten:
                    five -= 1
                    ten -= 1
                elif five >= 3:
                    five -= 3
                else: return False
        return True

# @lc code=end

