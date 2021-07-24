package leetcode.editor.cn;//给定一个 N 叉树，返回其节点值的 后序遍历 。
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
//输出：[5,6,3,2,4,1]
// 
//
// 示例 2： 
//
// 
//
// 
//输入：root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,
//null,13,null,null,14]
//输出：[2,6,14,11,7,3,12,8,4,13,9,10,5,1]
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
// 👍 154 👎 0


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

import java.util.*;

// 迭代
class Solution_590_3 {

    public List<Integer> postorder(Node root) {
        List<Integer> res = new ArrayList<>();
        Deque<Node> deque = new ArrayDeque<>();
        while (root != null || !deque.isEmpty()) {
            // root不为null，则加入该节点
            while (root != null) {
                deque.offerFirst(root);
                if (root.children != null && !root.children.isEmpty()) {
                    // 下次遍历第一个子节点
                    root = root.children.get(0);

                } else {
                    root = null;
                }
            }
            // 上一个没有子节点的父节点
            root = deque.pollFirst();
            res.add(root.val);
            // 往上去肯定有子节点，不用判断是不是Null
            if (deque.peekFirst() == null) {
                root = null;
            } else {
                List<Node> brothers = deque.peekFirst().children;
                brothers.remove(0);
                // 父节点下一个兄弟节点
                root = brothers.isEmpty() ? null : brothers.get(0);
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
        System.out.println(new Solution_590_3().postorder(node1));
    }
}

// 队列
class Solution_590_1 {

    public List<Integer> postorder(Node root) {
        List<Integer> res = new ArrayList<>();
        // 树可能为空，提前返回
        if (root == null) {
            return res;
        }
        Deque<Node> deque = new ArrayDeque<>();
        addQueue(root, deque);
        while (!deque.isEmpty()) {
            // 遍历的一定不是iNull
            // 现金先出，这里用的是队列
            res.add(deque.pollLast().val);
        }
        return res;
    }

    void addQueue(Node root, Deque<Node> deque) {
        List<Node> children = root.children;
        int n = children.size();
        for (int i = 0; i < n; i++) {
            addQueue(children.get(i), deque);
        }
        deque.offerFirst(root);
    }
}

// 栈
class Solution_590_2 {

    public List<Integer> postorder(Node root) {
        List<Integer> res = new ArrayList<>();
        // 树可能为空，提前返回
        if (root == null) {
            return res;
        }
        Deque<Node> deque = new ArrayDeque<>();
        addStack(root, deque);
        while (!deque.isEmpty()) {
            // 遍历的一定不是iNull
            // 先进后出，栈
            res.add(deque.pollFirst().val);
        }
        return res;
    }

    void addStack(Node root, Deque<Node> deque) {
        List<Node> children = root.children;
        int n = children.size();
        deque.offerFirst(root);
        // 前面的子节点先遍历，后面的先压入栈
        for (int i = n - 1; i >= 0; i--) {
            addStack(children.get(i), deque);
        }
    }
}

// 递归
class Solution_590 {

    public List<Integer> postorder(Node root) {
        List<Integer> res = new ArrayList<>();
        postorder(root, res);
        return res;
    }

    void postorder(Node root, List<Integer> res) {
        if (root == null) {
            return;
        }
        for (Node node : root.children) {
            postorder(node, res);
        }
        res.add(root.val);
    }
}

class Node {

    public int val;

    public List<Node> children;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
