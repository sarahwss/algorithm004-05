package leetcode.editor.cn;//给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
//
// 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（
//一个节点也可以是它自己的祖先）。” 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
//输出：3
//解释：节点 5 和节点 1 的最近公共祖先是节点 3 。
// 
//
// 示例 2： 
//
// 
//输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
//输出：5
//解释：节点 5 和节点 4 的最近公共祖先是节点 5 。因为根据定义最近公共祖先节点可以为节点本身。
// 
//
// 示例 3： 
//
// 
//输入：root = [1,2], p = 1, q = 2
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目在范围 [2, 105] 内。 
// -109 <= Node.val <= 109 
// 所有 Node.val 互不相同 。 
// p != q 
// p 和 q 均存在于给定的二叉树中。 
// 
// Related Topics 树 深度优先搜索 二叉树 
// 👍 1214 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import javax.swing.table.TableRowSorter;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */

class Solution_236_2 {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 没找到，不要忘了
        if (root == null) {
            return null;
        }
        // 只要一个找到就算找到
        if (root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        // 两边都找到，则公共节点只能是root
        if (left != null && right != null) {
            return root;
        }
        // 只有一半找到，找到的就是公共节点
        return left != null ? left : right;
    }
}

// 思路正确，代码冗余，两个方法可以合并
class Solution_236_1 {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 左右子树都有，那公共父节点就是当前
        if (find(root.left, p, q) && find(root.right, p, q)) {
            return root;
        }
        // 都在同一子树，找到其中一个就可
        if (root == p || root == q) {
            return root;
        }
        // 都在左子树
        if (find(root.left, p, q)) {
            return lowestCommonAncestor(root.left, p, q);
        }
        // 都在右子树
        return lowestCommonAncestor(root.right, p, q);
    }

    // 同时搜索p或q,搜索到则至少有一个在这个节点
    boolean find(TreeNode root, TreeNode p, TreeNode q) {
        // 这个不要少，没找到
        if (root == null) {
            return false;
        }
        if (root == p || root == q) {
            return true;
        }
        return find(root.left, p, q) || find(root.right, p, q);
    }

}
//leetcode submit region end(Prohibit modification and deletion)
