package leetcode.editor.cn;//给定一个单链表 L 的头节点 head ，单链表 L 表示为：
//
// L0 → L1 → … → Ln-1 → Ln 
//请将其重新排列后变为： 
//
// L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → … 
//
// 不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。 
//
// 
//
// 示例 1: 
//
// 
//
// 
//输入: head = [1,2,3,4]
//输出: [1,4,2,3] 
//
// 示例 2: 
//
// 
//
// 
//输入: head = [1,2,3,4,5]
//输出: [1,5,2,4,3] 
//
// 
//
// 提示： 
//
// 
// 链表的长度范围为 [1, 5 * 104] 
// 1 <= node.val <= 1000 
// 
//
// 
//
// 注意：本题与主站 143 题相同：https://leetcode-cn.com/problems/reorder-list/ 
// Related Topics 栈 递归 链表 双指针 
// 👍 3 👎 0


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
class Solution_剑指026 {

    public void reorderList(ListNode head) {
        ListNode dummy = new ListNode();
        // 这里别少了.next
        dummy.next = head;
        // slow指向前半段最后一个节点
        ListNode slow = dummy;
        // fast指向后半段最后一个节点
        ListNode fast = dummy;
        // 至少移动慢指针，可以不移动快指针
        // slow跟随fast，和fast移动次数相同，只判断fast即可
        while (fast.next != null) {
            slow = slow.next;
            // 最后可能只移动一步，指向最后一个元素，方便下一步判断fast.next != null
            fast = fast.next;
            // 不能让fast等于null
            if (fast.next != null) {
                fast = fast.next;
            }
        }
        ListNode temp = slow.next;
        // 前后两段断开
        slow.next = null;
        linkList(head, reverse(temp), dummy);
    }

    private ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    private void linkList(ListNode p, ListNode q, ListNode dummy) {
        // 已经排好序的节点
        ListNode pre = dummy;
        // 这里判断q!=null的话，q==null时，p将不会进入循环指针不会百变，故放到循环内部判断
        // p最长，可能出现p!=null而q==null的情况
        while (p != null && q != null) {
            ListNode next = p.next;
            pre.next = p;
            p.next = q;
            pre = q;
            p = next;
            q = q.next;
            // 不用断联，下一循环会更改next，因q已更改指向，也无法断联
        }
        // p可能多余
        pre.next = p;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode head1 = new ListNode(2);
        ListNode head2 = new ListNode(3);
        ListNode head3 = new ListNode(4);
        ListNode head4 = new ListNode(5);
        head.next = head1;
        head1.next = head2;
        head2.next = head3;
        head3.next = head4;
        new Solution_剑指026().reorderList(head);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
