package leetcode.editor.cn;//给定一棵树的前序遍历 preorder 与中序遍历 inorder。请构造二叉树并返回其根节点。
//
// 
//
// 示例 1: 
//
// 
//Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
//Output: [3,9,20,null,null,15,7]
// 
//
// 示例 2: 
//
// 
//Input: preorder = [-1], inorder = [-1]
//Output: [-1]
// 
//
// 
//
// 提示: 
//
// 
// 1 <= preorder.length <= 3000 
// inorder.length == preorder.length 
// -3000 <= preorder[i], inorder[i] <= 3000 
// preorder 和 inorder 均无重复元素 
// inorder 均出现在 preorder 
// preorder 保证为二叉树的前序遍历序列 
// inorder 保证为二叉树的中序遍历序列 
// 
// Related Topics 树 数组 哈希表 分治 二叉树 
// 👍 1126 👎 0


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
class Solution_105 {

    // 前序遍历右边界
    int pre = 0;

    // 中序遍历右边界
    int in = 0;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // 所有元素的值都小于最大值
        return buildTree(preorder, inorder, Integer.MAX_VALUE);
    }

    TreeNode buildTree(int[] preorder, int[] inorder, int stop) {
        if (pre >= preorder.length) {
            return null;
        }
        // in一直处在stop左下的位置，in-1就是stop左子树的最右端点，in=stop时左子树已找完，从in+1开始找右子树
        if (inorder[in] == stop) {
            in++;
            return null;
        }
        TreeNode root = new TreeNode(preorder[pre++]);
        // 从前序的pre+1处到中序的root.val之前
        root.left = buildTree(preorder, inorder, root.val);
        // 从中序的in+1处到祖父节点之前（in+1可能是in的父及祖父节点，也可能是in的右孩子，在这里只能是右孩子）
        root.right = buildTree(preorder, inorder, stop);
        return root;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
