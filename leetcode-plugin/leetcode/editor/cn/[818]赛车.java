package leetcode.editor.cn;//你的赛车起始停留在位置 0，速度为 +1，正行驶在一个无限长的数轴上。（车也可以向负数方向行驶。）
//
// 你的车会根据一系列由 A（加速）和 R（倒车）组成的指令进行自动驾驶 。 
//
// 当车得到指令 "A" 时, 将会做出以下操作： position += speed, speed *= 2。 
//
// 当车得到指令 "R" 时, 将会做出以下操作：如果当前速度是正数，则将车速调整为 speed = -1 ；否则将车速调整为 speed = 1。 (当前所
//处位置不变。) 
//
// 例如，当得到一系列指令 "AAR" 后, 你的车将会走过位置 0->1->3->3，并且速度变化为 1->2->4->-1。 
//
// 现在给定一个目标位置，请给出能够到达目标位置的最短指令列表的长度。 
//
// 
//
// 
//示例 1:
//输入: 
//target = 3
//输出: 2
//解释: 
//最短指令列表为 "AA"
//位置变化为 0->1->3
// 
//
// 
//示例 2:
//输入: 
//target = 6
//输出: 5
//解释: 
//最短指令列表为 "AAARA"
//位置变化为 0->1->3->7->7->6
// 
//
// 说明: 
//
// 
// 1 <= target（目标位置） <= 10000。 
// 
// Related Topics 动态规划 
// 👍 106 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution_818 {

    public int racecar(int target) {
        // target位置指令次数
        // 为了数组长度最少为3，而1最小为1，所以要在target+1的基础上加上2
        int[] dp = new int[target + 3];
        // 别忘了初始值
        Arrays.fill(dp, Integer.MAX_VALUE);
        // 虽然有速度，还是得有指定，否则位置不会变
        dp[0] = 0;
        // target = 1，A
        dp[1] = 1;
        // target = 2, AARA
        dp[2] = 4;
        // 需要3个初始值为了凑够一轮2^k，供下一轮迭代使用 只有2^0不够
        for (int i = 3; i <= target; i++) {
            // 为什么是 (1 << k) - 1，可画图，发现路径时等差数列
            // 先确定k
            int k = 32 - Integer.numberOfLeadingZeros(i);
            // 刚好k步，不需要往回移
            if (i == (1 << k) - 1) {
                // 注意这里不是return k, 判断也不是判断target
                dp[i] = k;
                continue;
            }
            // 从小处折返再折回，之所以j<k-1因为再减得多就变成2^k-1的范围
            for (int j = 0; j < k - 1; j++) {
                // A(k-1)RA(j)R
                dp[i] = Math.min(dp[i], k - 1 + j + 2 + dp[i - (1 << (k - 1)) + (1 << j)]);
            }
            // 先走到远位置，再往回返
            // 注意这里不是target事i
            if ((1 << k) - 1 - i < i) {
                dp[i] = Math.min(dp[i], k + 1 + dp[(1 << k) - 1 - i]);
            }
        }
        return dp[target];
    }

    public static void main(String[] args) {
        System.out.println(new Solution_818().racecar(8));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
