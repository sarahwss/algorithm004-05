package leetcode.editor.cn;//给定一棵二叉树的根节点 root ，请找出该二叉树中每一层的最大值。
//
// 
//
// 示例1： 
//
// 
//输入: root = [1,3,2,5,3,null,9]
//输出: [1,3,9]
//解释:
//          1
//         / \
//        3   2
//       / \   \  
//      5   3   9 
// 
//
// 示例2： 
//
// 
//输入: root = [1,2,3]
//输出: [1,3]
//解释:
//          1
//         / \
//        2   3
// 
//
// 示例3： 
//
// 
//输入: root = [1]
//输出: [1]
// 
//
// 示例4： 
//
// 
//输入: root = [1,null,2]
//输出: [1,2]
//解释:      
//           1 
//            \
//             2     
// 
//
// 示例5： 
//
// 
//输入: root = []
//输出: []
// 
//
// 
//
// 提示： 
//
// 
// 二叉树的节点个数的范围是 [0,104] 
// -231 <= Node.val <= 231 - 1 
// 
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 
// 👍 137 👎 0


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

class Solution_515_1 {

    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        largestValues(root, 0, res);
        return res;
    }

    void largestValues(TreeNode root, int i, List<Integer> res) {
        if (root == null) {
            return;
        }
        if (i != res.size()) {
            res.set(i, Math.max(res.get(i), root.val));
        } else {
            res.add(root.val);
        }
        if (root.left != null) {
            largestValues(root.left, i + 1, res);
        }
        if (root.right != null) {
            largestValues(root.right, i + 1, res);
        }
    }
}

// bfs
class Solution_515 {

    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> deque = new ArrayDeque<>();
        if (root != null) {
            deque.offerFirst(root);
        }
        while (!deque.isEmpty()) {
            int n = deque.size();
            int max = Integer.MIN_VALUE;
            while (n-- > 0) {
                TreeNode node = deque.pollLast();
                max = Math.max(max, node.val);
                if (node.left != null) {
                    deque.offerFirst(node.left);
                }
                if (node.right != null) {
                    deque.offerFirst(node.right);
                }
            }
            res.add(max);
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
