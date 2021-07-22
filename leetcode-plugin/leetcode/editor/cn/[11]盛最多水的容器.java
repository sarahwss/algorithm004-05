package leetcode.editor.cn;//给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i,
//ai) 和 (i, 0) 。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。 
//
// 说明：你不能倾斜容器。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：[1,8,6,2,5,4,8,3,7]
//输出：49 
//解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。 
//
// 示例 2： 
//
// 
//输入：height = [1,1]
//输出：1
// 
//
// 示例 3： 
//
// 
//输入：height = [4,3,2,1,4]
//输出：16
// 
//
// 示例 4： 
//
// 
//输入：height = [1,2,1]
//输出：2
// 
//
// 
//
// 提示： 
//
// 
// n = height.length 
// 2 <= n <= 3 * 104 
// 0 <= height[i] <= 3 * 104 
// 
// Related Topics 贪心 数组 双指针 
// 👍 2625 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution_11 {

    public int maxArea(int[] height) {
        int n = height.length;
        int l = 0;
        // 窗口长度越长越好，所以r从最后面开始，不用考虑中间高度太高遮挡，都只是虚拟坐标，不是真实柱子
        int r = n - 1;
        int res = 0;
        while (l < r) {
            res = Math.max(res, (r - l) * Math.min(height[l], height[r]));
            // 为枚举其他情况，要么左移右坐标，要么左移左坐标，取其中较矮的一方移动，尽量多用较高的一方
            if (height[l] > height[r]) {
                r--;
            } else {
                l++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Solution_11().maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
