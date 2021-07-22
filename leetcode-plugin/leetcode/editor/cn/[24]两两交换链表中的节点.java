package leetcode.editor.cn;//给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
//
// 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。 
//
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,3,4]
//输出：[2,1,4,3]
// 
//
// 示例 2： 
//
// 
//输入：head = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：head = [1]
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点的数目在范围 [0, 100] 内 
// 0 <= Node.val <= 100 
// 
//
// 
//
// 进阶：你能在不修改链表节点值的情况下解决这个问题吗?（也就是说，仅修改节点本身。） 
// Related Topics 递归 链表 
// 👍 975 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution_24_2 {

    // 返回交换后的头节点
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        // pre是已经排好的的上个组的最后一个
        ListNode pre = dummy;
        while (pre.next != null && pre.next.next != null) {
            ListNode a = pre.next;
            ListNode b = a.next;
            a.next = b.next;
            b.next = a;
            // 一直把翻转好的和未翻转好的连其阿里啊
            pre.next = b;
            pre = a;
        }
        // 前面的后面炼器来的好处
        return dummy.next;
    }
}

class Solution_24_1 {

    // 返回交换后的头节点
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode next = head.next;
        ListNode after = swapPairs(next.next);
        head.next = after;
        next.next = head;
        // nest作为头节点
        return next;
    }
}


class Solution_24 {

    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        // 假设是已经reverse后的头节点
        ListNode pre = dummy;
        // 两两交换,只要一个变量pre就好
        while (pre.next != null && pre.next.next != null) {
            ListNode cur = pre.next;
            // 将翻转好的和新翻转好的连接，
            pre.next = reverseTwo(pre.next);
            // 更新翻转后的最后一个节点
            pre = cur;
        }
        // 翻转后的头节点
        return dummy.next;

    }

    // 返回reverse后头节点
    ListNode reverseTwo(ListNode head) {
        ListNode pre = null;
        // reverse前的第一个节点
        ListNode cur = head;
        for (int i = 0; i < 2; i++) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        // 将翻转后的和后面的连接起来，方便之后获取到pre就能拿到后面的所有节点
        // 注意这里是head不是pre，pre是翻转前的第二个元素
        head.next = cur;
        return pre;
    }


    public static void main(String[] args) {
        Solution_24 solution = new Solution_24();
        ListNode head = new ListNode(1);
        ListNode head1 = new ListNode(2);
        ListNode head2 = new ListNode(3);
        ListNode head3 = new ListNode(4);
        ListNode head4 = new ListNode(5);
        head.next = head1;
        head1.next = head2;
        head2.next = head3;
        head3.next = head4;
        solution.swapPairs(head);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
