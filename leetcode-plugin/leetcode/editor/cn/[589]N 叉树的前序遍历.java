package leetcode.editor.cn;//给定一个 N 叉树，返回其节点值的 前序遍历 。
//
// N 叉树 在输入中按层序遍历进行序列化表示，每组子节点由空值 null 分隔（请参见示例）。 
//
// 
// 
// 
//
// 进阶： 
//
// 递归法很简单，你可以使用迭代法完成此题吗? 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：root = [1,null,3,2,4,null,5,6]
//输出：[1,3,5,6,2,4]
// 
//示例 2：
//
// 
//
// 
//输入：root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,
//null,13,null,null,14]
//输出：[1,2,3,6,7,11,14,4,8,12,5,9,13,10]
// 
//
// 
//
// 提示： 
//
// 
// N 叉树的高度小于或等于 1000 
// 节点总数在范围 [0, 10^4] 内 
// 
// 
// 
// Related Topics 栈 树 深度优先搜索 
// 👍 169 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;


class Solution_589_1 {

    public List<Integer> preorder(Node root) {
        List<Integer> res = new ArrayList<>();
        Deque<Node> deque = new ArrayDeque<>();
        while (root != null || !deque.isEmpty()) {
            while (root != null) {
                // 别忘了添加队列
                deque.offerFirst(root);
                res.add(root.val);
                if (!root.children.isEmpty()) {
                    root = root.children.get(0);
                } else {
                    root = null;
                }
            }
            // 最后一个没有子节点的节点，也是父节点的第一个子节点，已经添加进结果过
            deque.pollFirst();
            // 处理下一个子节点
            if (deque.peekFirst() != null) {
                // children一定不为null
                List<Node> children = deque.peekFirst().children;
                // 先删再取，移除掉第一个元素
                children.remove(0);
                root = !children.isEmpty() ? children.get(0) : null;
                // 不要少了这个else
            } else {
                root = null;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(3);
        Node node3 = new Node(2);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        node1.children = new ArrayList<>();
        node1.children.add(node2);
        node1.children.add(node3);
        node1.children.add(node4);
        node2.children = new ArrayList<>();
        node2.children.add(node5);
        node2.children.add(node6);
        System.out.println(new Solution_589_1().preorder(node1));
    }
}

class Solution_589 {

    public List<Integer> preorder(Node root) {
        List<Integer> res = new ArrayList<>();
        preorder(root, res);
        return res;
    }

    void preorder(Node root, List<Integer> res) {
        if (root == null) {
            return;
        }
        res.add(root.val);
        // 不要少了判断
        for (Node child : root.children) {
            preorder(child, res);
        }
    }

    public static void main(String[] args) {
        List<Integer> list = null;
        for (int i : list) {

        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
