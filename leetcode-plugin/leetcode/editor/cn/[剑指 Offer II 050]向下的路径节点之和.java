package leetcode.editor.cn;//给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
//
// 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
//输出：3
//解释：和等于 8 的路径有 3 条，如图所示。
// 
//
// 示例 2： 
//
// 
//输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
//输出：3
// 
//
// 
//
// 提示: 
//
// 
// 二叉树的节点个数的范围是 [0,1000] 
// -109 <= Node.val <= 109 
// -1000 <= targetSum <= 1000 
// 
//
// 
//
// 注意：本题与主站 437 题相同：https://leetcode-cn.com/problems/path-sum-iii/ 
// Related Topics 树 深度优先搜索 二叉树 
// 👍 1 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.HashMap;
import java.util.Map;

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
class Solution_剑指050 {

    public int pathSum(TreeNode root, int targetSum) {
        Map<Integer, Integer> pathToCount = new HashMap<>();
        // 注意别少了初始值，用于path刚好等于targetSum时获取key为0的数目
        pathToCount.put(0, 1);
        return dfs(root, pathToCount, targetSum, 0);
    }

    private int dfs(TreeNode root, Map<Integer, Integer> pathToCount, int targetSum, int path) {
        if (root == null) {
            return 0;
        }
        path += root.val;
        // map存储父节点累计结果
        int count = pathToCount.getOrDefault(path - targetSum, 0);
        // path出现次数加一
        // 计算当前节点的贡献
        pathToCount.put(path, pathToCount.getOrDefault(path, 0) + 1);
        // 尖酸子节点的贡献和
        count += dfs(root.left, pathToCount, targetSum, path);
        count += dfs(root.right, pathToCount, targetSum, path);
        // 回溯，上一层尝试
        pathToCount.put(path, pathToCount.get(path) - 1);
        return count;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
