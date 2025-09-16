//给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，其中 answer[i] 是指对于第 i 天，下一个更高温度出现
//在几天后。如果气温在这之后都不会升高，请在该位置用 0 来代替。 
//
// 
//
// 示例 1: 
//
// 
//输入: temperatures = [73,74,75,71,69,72,76,73]
//输出: [1,1,4,2,1,1,0,0]
// 
//
// 示例 2: 
//
// 
//输入: temperatures = [30,40,50,60]
//输出: [1,1,1,0]
// 
//
// 示例 3: 
//
// 
//输入: temperatures = [30,60,90]
//输出: [1,1,0] 
//
// 
//
// 提示： 
//
// 
// 1 <= temperatures.length <= 10⁵ 
// 30 <= temperatures[i] <= 100 
// 
// Related Topics 栈 数组 单调栈 👍 1407 👎 0


import java.util.ArrayDeque;
import java.util.Deque;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public int[] dailyTemperatures(int[] temperatures) {
        int[] res = new int[temperatures.length];
        //        // 自动赋值，不需要显式赋值
        //        res[temperatures.length - 1] = 0;

        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i <= temperatures.length - 1; i++) {
            while (deque.peekFirst() != null && temperatures[deque.peekFirst()] < temperatures[i]) {// 可能弹出多个相等的元素
                Integer top = deque.pollFirst();
                res[top] = i - top;
                //                System.out.println("aaaa" + top + "bbb" + i);
            }
            deque.offerFirst(i);
        }

        //        // 剩余没有同伴的元素，自动赋值0
        //        while (deque.peekFirst() != null) {// 可能弹出多个相等的元素
        //            res[deque.peekFirst()] = 0;
        //        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
