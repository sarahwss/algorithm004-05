package leetcode.editor.cn;//实现 int sqrt(int x) 函数。
//
// 计算并返回 x 的平方根，其中 x 是非负整数。 
//
// 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。 
//
// 示例 1: 
//
// 输入: 4
//输出: 2
// 
//
// 示例 2: 
//
// 输入: 8
//输出: 2
//说明: 8 的平方根是 2.82842..., 
//     由于返回类型是整数，小数部分将被舍去。
// 
// Related Topics 数学 二分查找 
// 👍 731 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution_69 {

    public int mySqrt(int x) {
        if (x <= 1) {
            return x;
        }
        int l = 1;
        int r = x;
        while (l <= r) {
            int mid = (l + r) / 2;
            // mid过大
            if (x / mid < mid) {
                r = mid - 1;
                // 完全相等的话，l也会加1，最终和r相等，还是返回r
                // 近似的情况下,l会增加，最终l>r，r是正常的，所以要返回r
            } else {
                l = mid + 1;
            }
        }
        return r;
    }

    public static void main(String[] args) {
        System.out.println(new Solution_69().mySqrt(3));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
