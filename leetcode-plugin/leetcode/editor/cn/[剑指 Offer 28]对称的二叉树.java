package leetcode.editor.cn;//请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。
//
// 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。 
//
// 1 
// / \ 
// 2 2 
// / \ / \ 
//3 4 4 3 
//但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的: 
//
// 1 
// / \ 
// 2 2 
// \ \ 
// 3 3 
//
// 
//
// 示例 1： 
//
// 输入：root = [1,2,2,3,4,4,3]
//输出：true
// 
//
// 示例 2： 
//
// 输入：root = [1,2,2,null,3,null,3]
//输出：false 
//
// 
//
// 限制： 
//
// 0 <= 节点个数 <= 1000 
//
// 注意：本题与主站 101 题相同：https://leetcode-cn.com/problems/symmetric-tree/ 
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 
// 👍 230 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.Deque;
import java.util.LinkedList;
import java.util.Optional;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
class Solution_剑指offer_28_1 {

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        // arraydeque不允许null值
        Deque<Optional<TreeNode>> deque1 = new LinkedList<>();
        Deque<Optional<TreeNode>> deque2 = new LinkedList<>();
        deque1.offerLast(Optional.ofNullable(root.left));
        deque2.offerLast(Optional.ofNullable(root.right));
        while (!deque1.isEmpty() && !deque2.isEmpty()) {
            int size = deque1.size();
            int size1 = deque2.size();
            if (size != size1) {
                return false;
            }
            while (size-- > 0) {
                Optional<TreeNode> left = deque1.pollFirst();
                Optional<TreeNode> right = deque2.pollFirst();
                if (left.isPresent() && !right.isPresent() || !left.isPresent() && right.isPresent() ||
                        left.isPresent() && right.isPresent() && left.get().val != right.get().val) {
                    return false;
                }
                if (left.isPresent()) {
                    deque1.offerLast(Optional.ofNullable(left.get().left));
                    deque1.offerLast(Optional.ofNullable(left.get().right));
                }
                if (right.isPresent()) {
                    deque2.offerLast(Optional.ofNullable(right.get().right));
                    deque2.offerLast(Optional.ofNullable(right.get().left));
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(0);
        //        root.right = new TreeNode(2);
        //        root.left.left = new TreeNode(3);
        //        root.left.right = new TreeNode(4);
        //        root.right.left = new TreeNode(4);
        //        root.right.right = new TreeNode(3);
        System.out.println(new Solution_剑指offer_28_1().isSymmetric(root));
    }

}

class Solution_剑指offer_28 {

    //    public class TreeNode {
    //
    //        int val;
    //
    //        TreeNode left;
    //
    //        TreeNode right;
    //
    //        TreeNode(int x) {
    //            val = x;
    //        }
    //    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isSymmetric(root.left, root.right);
    }

    boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null || left.val != right.val) {
            return false;
        }
        return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
