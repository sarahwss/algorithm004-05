package leetcode.editor.cn;//给定一个 N 叉树，返回其节点值的层序遍历。（即从左到右，逐层遍历）。
//
// 树的序列化输入是用层序遍历，每组子节点都由 null 值分隔（参见示例）。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：root = [1,null,3,2,4,null,5,6]
//输出：[[1],[3,2,4],[5,6]]
// 
//
// 示例 2： 
//
// 
//
// 
//输入：root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,
//null,13,null,null,14]
//输出：[[1],[2,3,4,5],[6,7,8,9,10],[11,12,13],[14]]
// 
//
// 
//
// 提示： 
//
// 
// 树的高度不会超过 1000 
// 树的节点总数在 [0, 10^4] 之间 
// 
// Related Topics 树 广度优先搜索 
// 👍 164 👎 0


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

import java.util.ArrayList;
import java.util.List;

class Solution_429 {

    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        levelOrder(res, 0, root);
        return res;
    }

    void levelOrder(List<List<Integer>> res, int i, Node root) {
        // 为Null直接返回，放在前面，可以不用提前返回root为Null的情况
        if (root == null) {
            return;
        }
        if (i == res.size()) {
            // 不需要判断全为null，因为没有Null
            res.add(new ArrayList<>());
        }
        res.get(i).add(root.val);
        if (root.children != null) {
            for (Node child : root.children) {
                levelOrder(res, i + 1, child);
            }
        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)
