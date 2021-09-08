package leetcode.editor.cn;//给定一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：head = [1,2,3,4,5], n = 2
//输出：[1,2,3,5]
// 
//
// 示例 2： 
//
// 
//输入：head = [1], n = 1
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：head = [1,2], n = 1
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// 链表中结点的数目为 sz 
// 1 <= sz <= 30 
// 0 <= Node.val <= 100 
// 1 <= n <= sz 
// 
//
// 
//
// 进阶：能尝试使用一趟扫描实现吗？ 
//
// 
//
// 注意：本题与主站 19 题相同： https://leetcode-cn.com/problems/remove-nth-node-from-end-of
//-list/ 
// Related Topics 链表 双指针 
// 👍 2 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution_剑指21 {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode p = dummy;
        ListNode q = head;
        for (int i = 0; i < n; i++) {
            q = q.next;
        }
        while (q != null) {
            q = q.next;
            p = p.next;
        }
        ListNode next = p.next;
        p.next = p.next.next;
        next.next = null;
        return dummy.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
