package leetcode.editor.cn;//给定一个二叉搜索树的 根节点 root 和一个整数 k , 请判断该二叉搜索树中是否存在两个节点它们的值之和等于 k 。假设二叉搜索树中节点的值均唯一。
//
// 
//
// 示例 1： 
//
// 
//输入: root = [8,6,10,5,7,9,11], k = 12
//输出: true
//解释: 节点 5 和节点 7 之和等于 12
// 
//
// 示例 2： 
//
// 
//输入: root = [8,6,10,5,7,9,11], k = 22
//输出: false
//解释: 不存在两个节点值之和为 22 的节点
// 
//
// 
//
// 提示： 
//
// 
// 二叉树的节点个数的范围是 [1, 104]. 
// -104 <= Node.val <= 104 
// root 为二叉搜索树 
// -105 <= k <= 105 
// 
//
// 
//
// 注意：本题与主站 653 题相同： https://leetcode-cn.com/problems/two-sum-iv-input-is-a-bst/
// 
// Related Topics 树 深度优先搜索 广度优先搜索 二叉搜索树 哈希表 双指针 二叉树 
// 👍 1 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

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

class Solution_剑指056_1 {

    class BSTIterator {

        Deque<TreeNode> deque;

        TreeNode cur;

        public BSTIterator(TreeNode root) {
            deque = new ArrayDeque<>();
            cur = root;
        }

        public int next() {
            // 注意这里不能有外循环
            while (cur != null) {
                deque.offerFirst(cur);
                cur = cur.left;
            }
            cur = deque.pollFirst();
            int val = cur.val;
            cur = cur.right;
            return val;
        }

        public boolean hasNext() {
            return cur != null || !deque.isEmpty();
        }
    }

    class BSTIteratorReversed {

        Deque<TreeNode> deque;

        TreeNode cur;

        public BSTIteratorReversed(TreeNode root) {
            deque = new ArrayDeque<>();
            cur = root;
        }

        public int prev() {
            // 注意这里不能有外循环
            while (cur != null) {
                deque.offerFirst(cur);
                cur = cur.right;
            }
            cur = deque.pollFirst();
            int val = cur.val;
            cur = cur.left;
            return val;
        }

        public boolean hasPrev() {
            return cur != null || !deque.isEmpty();
        }
    }

    public boolean findTarget(TreeNode root, int k) {
        BSTIterator iterator = new BSTIterator(root);
        BSTIteratorReversed iteratorReversed = new BSTIteratorReversed(root);
        int next = iterator.next();
        int prev = iteratorReversed.prev();
        while (prev != next) {
            if (prev + next == k) {
                return true;
            }
            // 二者相遇之前不会出现没有元素的情况
            if (prev + next < k) {
                next = iterator.next();
            } else {
                prev = iteratorReversed.prev();
            }
        }
        return false;
    }
}

class Solution_剑指056 {

    public boolean findTarget(TreeNode root, int k) {
        Set<Integer> set = new HashSet<>();
        Deque<TreeNode> deque = new ArrayDeque<>();
        TreeNode cur = root;
        while (cur != null || !deque.isEmpty()) {
            while (cur != null) {
                deque.offerFirst(cur);
                cur = cur.left;
            }
            cur = deque.pollFirst();
            if (set.contains(k - cur.val)) {
                return true;
            }
            set.add(cur.val);
            cur = cur.right;
        }
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
