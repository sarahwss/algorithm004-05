package leetcode.editor.cn;//给定一个链表，返回链表开始入环的第一个节点。 从链表的头节点开始沿着 next 指针进入环的第一个节点为环的入口节点。如果链表无环，则返回 null。
//
// 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。注意，po
//s 仅仅是用于标识环的情况，并不会作为参数传递到函数中。 
//
// 说明：不允许修改给定的链表。 
//
// 
// 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：head = [3,2,0,-4], pos = 1
//输出：返回索引为 1 的链表节点
//解释：链表中有一个环，其尾部连接到第二个节点。
// 
//
// 示例 2： 
//
// 
//
// 
//输入：head = [1,2], pos = 0
//输出：返回索引为 0 的链表节点
//解释：链表中有一个环，其尾部连接到第一个节点。
// 
//
// 示例 3： 
//
// 
//
// 
//输入：head = [1], pos = -1
//输出：返回 null
//解释：链表中没有环。
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点的数目范围在范围 [0, 104] 内 
// -105 <= Node.val <= 105 
// pos 的值为 -1 或者链表中的一个有效索引 
// 
//
// 
//
// 进阶：是否可以使用 O(1) 空间解决此题？ 
//
// 
//
// 注意：本题与主站 142 题相同： https://leetcode-cn.com/problems/linked-list-cycle-ii/ 
// Related Topics 哈希表 链表 双指针 
// 👍 1 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Definition for singly-linked list.
 * class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) {
 * val = x;
 * next = null;
 * }
 * }
 */
class Solution_剑指022 {

    public ListNode detectCycle(ListNode head) {
        ListNode node = findCycle(head);
        if (node == null) {
            return null;
        }
        ListNode p = node;
        // 注意两个起点不一样
        ListNode q = head;
        while (p != null && q != null) {
            // 可能Node本身就是交点
            if (p == q) {
                return p;
            }
            p = p.next;
            q = q.next;
        }
        return null;
    }

    ListNode findCycle(ListNode head) {
        ListNode p = head;
        ListNode q = head;
        while (p != null && q != null && q.next != null) {
            // 先移动指针再进行判断，否则循环进行不下去
            p = p.next;
            // 不要写p.next，二者距离会越来越远
            q = q.next.next;
            if (p == q) {
                return p;
            }
        }
        return null;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
