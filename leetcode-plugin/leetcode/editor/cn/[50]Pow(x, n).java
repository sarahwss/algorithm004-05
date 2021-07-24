package leetcode.editor.cn;//实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn）。
//
// 
//
// 示例 1： 
//
// 
//输入：x = 2.00000, n = 10
//输出：1024.00000
// 
//
// 示例 2： 
//
// 
//输入：x = 2.10000, n = 3
//输出：9.26100
// 
//
// 示例 3： 
//
// 
//输入：x = 2.00000, n = -2
//输出：0.25000
//解释：2-2 = 1/22 = 1/4 = 0.25
// 
//
// 
//
// 提示： 
//
// 
// -100.0 < x < 100.0 
// -231 <= n <= 231-1 
// -104 <= xn <= 104 
// 
// Related Topics 递归 数学 
// 👍 692 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution_50 {

    public double myPow(double x, int n) {
        if (n == 0) {
            return 1;
        }
        // n可能是小数
        double base = n < 0 ? (1 / x) : x;
        //分成两次计算
        // 注意这里n要取绝对值，而且要除以2之后再取，因为n可能为最小值，取绝对值后会超界
        double b = myPow(base, Math.abs(n / 2));
        //        System.out
        //                .println(x + "^" + n + ", base: " + base + ", sigle:  " + ((n & 1) == 1 ? base : 1) + ",half:   " + b);
        // 是奇数则多出一个数，注意这里应该是base，不是x
        return ((n & 1) == 1 ? base : 1) * b * b;
    }

    public static void main(String[] args) {
        System.out.println(2 & 1);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
