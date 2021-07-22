package leetcode.editor.cn;//给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
//
// k 是一个正整数，它的值小于或等于链表的长度。 
//
// 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。 
//
// 进阶： 
//
// 
// 你可以设计一个只使用常数额外空间的算法来解决此问题吗？ 
// 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,3,4,5], k = 2
//输出：[2,1,4,3,5]
// 
//
// 示例 2： 
//
// 
//输入：head = [1,2,3,4,5], k = 3
//输出：[3,2,1,4,5]
// 
//
// 示例 3： 
//
// 
//输入：head = [1,2,3,4,5], k = 1
//输出：[1,2,3,4,5]
// 
//
// 示例 4： 
//
// 
//输入：head = [1], k = 1
//输出：[1]
// 
//
// 
// 
//
// 提示： 
//
// 
// 列表中节点的数量在范围 sz 内 
// 1 <= sz <= 5000 
// 0 <= Node.val <= 1000 
// 1 <= k <= sz 
// 
// Related Topics 递归 链表 
// 👍 1190 👎 0


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

class Solution_25_1 {

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        while (pre != null) {
            // 注意p可能为null
            ListNode p = pre.next;
            // 确定是否存在k个节点
            for (int i = 0; i < k; i++) {
                if (p == null) {
                    // 不需要交换则提前返回，break会导致死循环
                    return dummy.next;
                }
                p = p.next;
            }
            // 下一个元素投，交换后的元素尾
            ListNode next = pre.next;
            pre.next = reverseK(pre.next, k);
            // pre指针右移k次
            pre = next;
        }
        return dummy.next;
    }

    private ListNode reverseK(ListNode head, int k) {
        ListNode pre = null;
        ListNode cur = head;
        for (int i = 0; i < k; i++) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        // head是最后一个，和下一个需要交换的连接起来
        head.next = cur;
        return pre;
    }

    public static void main(String[] args) {
        Solution_25_1 solution = new Solution_25_1();
        ListNode head = new ListNode(1);
        ListNode head1 = new ListNode(2);
        //    ListNode head2 = new ListNode(3);
        //    ListNode head3 = new ListNode(4);
        //    ListNode head4 = new ListNode(5);
        head.next = head1;
        //    head1.next = head2;
        //    head2.next = head3;
        //    head3.next = head4;
        solution.reverseKGroup(head, 2);
    }
}

class Solution_25 {

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode p = head;
        // 找到翻转好后面的组
        for (int i = 0; i < k; i++) {
            // 注意这里判断p，不是p.next
            if (p == null) {
                // 小于k个不翻转
                return head;
            }
            p = p.next;
        }
        ListNode pre = null;
        ListNode cur = head;
        for (int i = 0; i < k; i++) {
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        // head被交换到最后
        head.next = reverseKGroup(p, k);
        // pre是翻转前最后一个元素，翻转后为头一个
        return pre;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
