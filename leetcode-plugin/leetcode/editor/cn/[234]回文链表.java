//给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。 
//
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,2,1]
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：head = [1,2]
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点数目在范围[1, 10⁵] 内 
// 0 <= Node.val <= 9 
// 
//
// 
//
// 进阶：你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？ 
// Related Topics 栈 递归 链表 双指针 👍 1623 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.Deque;
import java.util.LinkedList;

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

    boolean valid = true;

    public boolean isPalindrome(ListNode head) {
        isPalindrome(head, head);
        return valid;
    }

    public ListNode isPalindrome(ListNode p, ListNode q) {
        if (q == null) {
            return null;
        }

        // 奇数
        if (q.next == null) {
            q = p;
        } else if (q.next.next == null) { // 偶数
            q = p.next;
        } else {
            if (p.next != null && q.next != null && q.next.next != null) {
                q = isPalindrome(p.next, q.next.next);
                if (!valid) {
                    return null;
                }
            }
        }
        if (p.val != q.val) {
            valid = false;
            return null;
        }
        return q.next;
    }


}

/*class ListNode {

    int val;

    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}*/


//leetcode submit region end(Prohibit modification and deletion)
