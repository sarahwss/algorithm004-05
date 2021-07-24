package leetcode.editor.cn;//给定一个二叉树的根节点 root ，返回它的 中序 遍历。
//
// 
//
// 示例 1： 
//
// 
//输入：root = [1,null,2,3]
//输出：[1,3,2]
// 
//
// 示例 2： 
//
// 
//输入：root = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：root = [1]
//输出：[1]
// 
//
// 示例 4： 
//
// 
//输入：root = [1,2]
//输出：[2,1]
// 
//
// 示例 5： 
//
// 
//输入：root = [1,null,2]
//输出：[1,2]
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目在范围 [0, 100] 内 
// -100 <= Node.val <= 100 
// 
//
// 
//
// 进阶: 递归算法很简单，你可以通过迭代算法完成吗？ 
// Related Topics 栈 树 深度优先搜索 二叉树 
// 👍 1035 👎 0


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
// 迭代
class Solution_94_3 {

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> deque = new ArrayDeque<>();
        while (root != null || !deque.isEmpty()) {
            // root不为null就下钻，注意这里判断的是root，不是root.val
            while (root != null) {
                //注意这里offer的是root，因为root晚于left遍历
                deque.offerFirst(root);
                root = root.left;
            }
            // 注意不要忘了给root赋值，在此之前root已经等于null
            root = deque.pollFirst();
            // root.left为Null,处理栈顶元素（父节点）
            res.add(root.val);
            // 处理当前元素右节点
            root = root.right;
        }
        return res;
    }
}

// 队列
class Solution_94_2 {

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> deque = new ArrayDeque<>();
        addQueue(root, deque);
        while (!deque.isEmpty()) {
            // 遍历的一定不是Null，不用判断null
            res.add(deque.pollLast().val);
        }
        return res;
    }


    void addQueue(TreeNode root, Deque<TreeNode> deque) {
        // 别少了这一步处理,不做任何处理，直接返回
        if (root == null) {
            return;
        }
        addQueue(root.left, deque);
        deque.offerFirst(root);
        addQueue(root.right, deque);
    }
}

// 栈
class Solution_94_1 {

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> deque = new ArrayDeque<>();
        addStack(root, deque);
        while (!deque.isEmpty()) {
            // 遍历的一定不是Null，不用判断null
            res.add(deque.pollFirst().val);
        }
        return res;
    }


    void addStack(TreeNode root, Deque<TreeNode> deque) {
        // 别少了这一步处理,不做任何处理，直接返回
        if (root == null) {
            return;
        }
        addStack(root.right, deque);
        deque.offerFirst(root);
        addStack(root.left, deque);
    }
}

// 递归
class Solution_94 {

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inorderTraversal(root, res);
        return res;
    }

    void inorderTraversal(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        inorderTraversal(root.left, res);
        res.add(root.val);
        inorderTraversal(root.right, res);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
