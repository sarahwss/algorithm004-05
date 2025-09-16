//给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。 
//
// 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：head = [4,2,1,3]
//输出：[1,2,3,4]
// 
//
// 示例 2： 
//
// 
//输入：head = [-1,5,3,4,0]
//输出：[-1,0,3,4,5]
// 
//
// 示例 3： 
//
// 
//输入：head = []
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点的数目在范围 [0, 5 * 10⁴] 内 
// -10⁵ <= Node.val <= 10⁵ 
// 
//
// 
//
// 进阶：你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？ 
// Related Topics 链表 双指针 分治 排序 归并排序 👍 1919 👎 0


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
class Solution {

    public ListNode sortList(ListNode head) {
        return mergeSort(head);
    }

    // 归并排序
    public ListNode mergeSort(ListNode head) {
        // 空元素或只有一个元素
        if (head == null || head.next == null) {
            return head;
        }
        // p是中间元素的前一个
        ListNode p = new ListNode();
        p.next = head;
        ListNode q = p;
        while (p != null && q != null && q.next != null) {
            p = p.next;
            q = q.next.next;
        }
        // 两段断开，第二段前一个元素，即第一段最后一个元素
        q = p.next; // 不可少，防止指针改变取值为空
        p.next = null;
        return merge(mergeSort(head), mergeSort(q));
    }

    private ListNode merge(ListNode p, ListNode q) {
        ListNode head = new ListNode();
        ListNode pre = head;
        while (p != null && q != null) {
            if (p.val <= q.val) {
                pre.next = p;
                pre = p;
                p = p.next;
            } else {
                pre.next = q;
                pre = q;
                q = q.next;
            }
        }
        // 循环结束，pre.next一定是null,因为最后一个拼接的一定是刚好遍历完的链表的最后一个元素，其next==null
        if (p != null) {
            pre.next = p;
        } else if (q != null) {
            pre.next = q;
        }
        return head.next;
    }


}

//public class ListNode {
//
//    int val;
//
//    ListNode next;
//
//    ListNode() {
//    }
//
//    ListNode(int val) {
//        this.val = val;
//    }
//
//    ListNode(int val, ListNode next) {
//        this.val = val;
//        this.next = next;
//    }
//}


//leetcode submit region end(Prohibit modification and deletion)
