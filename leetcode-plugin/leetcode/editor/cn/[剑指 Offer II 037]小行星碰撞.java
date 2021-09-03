package leetcode.editor.cn;//给定一个整数数组 asteroids，表示在同一行的小行星。
//
// 对于数组中的每一个元素，其绝对值表示小行星的大小，正负表示小行星的移动方向（正表示向右移动，负表示向左移动）。每一颗小行星以相同的速度移动。 
//
// 找出碰撞后剩下的所有小行星。碰撞规则：两个行星相互碰撞，较小的行星会爆炸。如果两颗行星大小相同，则两颗行星都会爆炸。两颗移动方向相同的行星，永远不会发生碰
//撞。 
//
// 
//
// 示例 1： 
//
// 
//输入：asteroids = [5,10,-5]
//输出：[5,10]
//解释：10 和 -5 碰撞后只剩下 10 。 5 和 10 永远不会发生碰撞。 
//
// 示例 2： 
//
// 
//输入：asteroids = [8,-8]
//输出：[]
//解释：8 和 -8 碰撞后，两者都发生爆炸。 
//
// 示例 3： 
//
// 
//输入：asteroids = [10,2,-5]
//输出：[10]
//解释：2 和 -5 发生碰撞后剩下 -5 。10 和 -5 发生碰撞后剩下 10 。 
//
// 示例 4： 
//
// 
//输入：asteroids = [-2,-1,1,2]
//输出：[-2,-1,1,2]
//解释：-2 和 -1 向左移动，而 1 和 2 向右移动。 由于移动方向相同的行星不会发生碰撞，所以最终没有行星发生碰撞。 
//
// 
//
// 提示： 
//
// 
// 2 <= asteroids.length <= 104 
// -1000 <= asteroids[i] <= 1000 
// asteroids[i] != 0 
// 
//
// 
//
// 注意：本题与主站 735 题相同： https://leetcode-cn.com/problems/asteroid-collision/ 
// Related Topics 栈 数组 
// 👍 0 👎 0


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution_剑指037 {

    public int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> deque = new ArrayDeque<>(asteroids.length);
        for (int as : asteroids) {
            // 向右飞行的小行星被撞飞
            // 注意这里条件不是as<0而是deque.peekFirst() > 0，因为 deque.peekFirst() < -as的条件对小数也满足
            while (!deque.isEmpty() && deque.peekFirst() > 0 && deque.peekFirst() < -as) {
                deque.pollFirst();
            }
            // 向右飞行的相同小行星和当前小行星一起被撞飞,并且只能撞飞一次
            // 这里的peek不要写成pooll了
            if (!deque.isEmpty() && as < 0 && deque.peekFirst() == -as) {
                deque.pollFirst();
                // 左边没有小行星，或者当前小行星向右飞，或者前面的小行星向左非，当前小行星不会被撞，入栈

            } else if (deque.isEmpty() || as > 0 || deque.peekFirst() < 0) {
                deque.offerFirst(as);
            }
            // 最后一种情况，当前小行星被前面的小行星撞飞，不会入栈
        }
        int[] res = new int[deque.size()];
        int i = 0;
        // 栈是倒序，这里反向一下
        while (!deque.isEmpty()) {
            res[i++] = deque.pollLast();
        }
        return res;

    }
}
//leetcode submit region end(Prohibit modification and deletion)
