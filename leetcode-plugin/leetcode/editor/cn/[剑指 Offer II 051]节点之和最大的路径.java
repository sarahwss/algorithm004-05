package leetcode.editor.cn;//路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不
//一定经过根节点。 
//
// 路径和 是路径中各节点值的总和。 
//
// 给定一个二叉树的根节点 root ，返回其 最大路径和，即所有路径上节点值之和的最大值。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：root = [1,2,3]
//输出：6
//解释：最优路径是 2 -> 1 -> 3 ，路径和为 2 + 1 + 3 = 6 
//
// 示例 2： 
//
// 
//
// 
//输入：root = [-10,9,20,null,null,15,7]
//输出：42
//解释：最优路径是 15 -> 20 -> 7 ，路径和为 15 + 20 + 7 = 42
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目范围是 [1, 3 * 104] 
// -1000 <= Node.val <= 1000 
// 
//
// 
//
// 注意：本题与主站 124 题相同： https://leetcode-cn.com/problems/binary-tree-maximum-path-s
//um/ 
// Related Topics 树 深度优先搜索 动态规划 二叉树 
// 👍 2 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class Solution_剑指051 {

    // node.val可能小于0
    public int maxPathSum(TreeNode root) {
        int[] maxSum = new int[]{0};
        dfs(root, maxSum);
        return maxSum[0];
    }

    private int dfs(TreeNode root, int[] maxSum) {
        if (root == null) {
            return 0;
        }
        // node.val可能小于0
        // 左子树中找到的最大路径（不一定包含左孩子）
        int[] leftSum = new int[]{Integer.MIN_VALUE};
        // 不包含本节点，包含左节点在内的路径最大值
        int left = Math.max(0, dfs(root.left, leftSum));
        // 右子树中找到的最大路径（不一定包含右孩子）
        int[] rightSum = new int[]{Integer.MIN_VALUE};
        // 不包含本节点，包含右节点在内的路径最大值
        int right = Math.max(0, dfs(root.right, rightSum));
        // 最大和路径可能出现在左子树，出现在右子树，或者包含本节点和左右子树(包含孩子节点)
        maxSum[0] = Math.max(Math.max(leftSum[0], rightSum[0]), root.val + left + right);
        // 包含本节点和左或右节点之一的路径最大值
        return Math.max(left, right) + root.val;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
