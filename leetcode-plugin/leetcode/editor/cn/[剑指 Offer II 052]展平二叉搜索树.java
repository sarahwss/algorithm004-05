package leetcode.editor.cn;//给你一棵二叉搜索树，请 按中序遍历 将其重新排列为一棵递增顺序搜索树，使树中最左边的节点成为树的根节点，并且每个节点没有左子节点，只有一个右子节点。
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：root = [5,3,6,2,4,null,8,1,null,null,null,7,9]
//输出：[1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]
// 
//
// 示例 2： 
//
// 
//
// 
//输入：root = [5,1,7]
//输出：[1,null,5,null,7]
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数的取值范围是 [1, 100] 
// 0 <= Node.val <= 1000 
// 
//
// 
//
// 注意：本题与主站 897 题相同： https://leetcode-cn.com/problems/increasing-order-search-tr
//ee/ 
// Related Topics 栈 树 深度优先搜索 二叉搜索树 二叉树 
// 👍 2 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayDeque;
import java.util.Deque;

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
class Solution_剑指052 {

    public TreeNode increasingBST(TreeNode root) {
        Deque<TreeNode> deque = new ArrayDeque<>();
        // 注意把cur设成root,而不是把root压入栈，压入栈的默认是按深度压好的
        TreeNode cur = root;
        TreeNode pre = null;
        TreeNode first = null;
        while (cur != null || !deque.isEmpty()) {
            // 注意这里判断cur额而不是cur.left
            while (cur != null) {
                // 注意这里要压入cur而不是cur.left
                deque.offerFirst(cur);
                cur = cur.left;
            }
            // 没有cur，从队列中弹出
            // 没有左节点，弹出根节点
            // 后进先出，栈
            cur = deque.pollFirst();
            // 没有左节点可能前面有比自己小的节点
            if (pre != null) {
                pre.right = cur;
            } else {
                first = cur;
            }
            // 去掉左孩子
            cur.left = null;
            pre = cur;
            // 该处理右节点了
            cur = cur.right;
        }
        return first;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
