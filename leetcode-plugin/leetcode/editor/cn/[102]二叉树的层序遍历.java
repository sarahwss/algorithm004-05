package leetcode.editor.cn;//给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
//
// 
//
// 示例： 
//二叉树：[3,9,20,null,null,15,7], 
//
// 
//    3
//   / \
//  9  20
//    /  \
//   15   7
// 
//
// 返回其层序遍历结果： 
//
// 
//[
//  [3],
//  [9,20],
//  [15,7]
//]
// 
// Related Topics 树 广度优先搜索 二叉树 
// 👍 941 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

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
//DFS
class Solution_102_1 {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        levelOrder(root, res, 0);
        return res;
    }

    private void levelOrder(TreeNode root, List<List<Integer>> res, int i) {
        // 放在前面，防止创建多余列表
        if (root == null) {
            return;
        }
        if (res.size() == i) {
            res.add(new ArrayList<>());
        }
        res.get(i).add(root.val);
        levelOrder(root.left, res, i + 1);
        levelOrder(root.right, res, i + 1);
    }
}

// BFS
class Solution_102 {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Deque<TreeNode> deque = new ArrayDeque<>();
        // 放在队列的不能是null
        if (root != null) {
            deque.offerFirst(root);
        }
        while (!deque.isEmpty()) {
            int n = deque.size();
            List<Integer> list = new ArrayList<>();
            res.add(list);
            // 按层遍历
            while (n-- > 0) {
                TreeNode node = deque.pollLast();
                list.add(node.val);
                // 别忘判断Null，放在队列的不能是null
                if (node.left != null) {
                    deque.offerFirst(node.left);
                }
                if (node.right != null) {
                    deque.offerFirst(node.right);
                }
            }
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
