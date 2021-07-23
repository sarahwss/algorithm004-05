package leetcode.editor.cn;//设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
//
// 
// push(x) —— 将元素 x 推入栈中。 
// pop() —— 删除栈顶的元素。 
// top() —— 获取栈顶元素。 
// getMin() —— 检索栈中的最小元素。 
// 
//
// 
//
// 示例: 
//
// 输入：
//["MinStack","push","push","push","getMin","pop","top","getMin"]
//[[],[-2],[0],[-3],[],[],[],[]]
//
//输出：
//[null,null,null,null,-3,null,0,-2]
//
//解释：
//MinStack minStack = new MinStack();
//minStack.push(-2);
//minStack.push(0);
//minStack.push(-3);
//minStack.getMin();   --> 返回 -3.
//minStack.pop();
//minStack.top();      --> 返回 0.
//minStack.getMin();   --> 返回 -2.
// 
//
// 
//
// 提示： 
//
// 
// pop、top 和 getMin 操作总是在 非空栈 上调用。 
// 
// Related Topics 栈 设计 
// 👍 962 👎 0


import sun.jvm.hotspot.opto.HaltNode;

import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)

class MinStack {

    Deque<Integer> deque = new LinkedList<>();

    Deque<Integer> minDeque = new LinkedList<>();

    /**
     * initialize your data structure here.
     */
    public MinStack() {

    }

    public void push(int val) {
        deque.offerFirst(val);
        // 等于val也放，这样就不用计数
        // 注意这里的判断是==null，不是不等于Null
        if (minDeque.peekFirst() == null || minDeque.peekFirst() >= val) {
            minDeque.offerFirst(val);
        }
    }

    public void pop() {
        // 注意这里不要是integer，否则会不相等，因为peekFirst返回值也是Integer
        int val = deque.pollFirst();
        if (minDeque.peekFirst() == val) {
            minDeque.pollFirst();
        }
    }

    public int top() {
        return deque.peekFirst();
    }

    public int getMin() {
        return minDeque.peekFirst();
    }

    public static void main(String[] args) {
        MinStack stack = new MinStack();
        stack.push(-2);
        stack.push(0);
        stack.push(-3);
        System.out.println(stack.getMin());
        stack.pop();
        System.out.println(stack.top());
        System.out.println(stack.getMin());
        System.out.println();
    }
}

class MinStack_115 {

    Deque<ListNode> deque;

    ListNode head;

    // tail不要少
    ListNode tail;

    /**
     * initialize your data structure here.
     */
    public MinStack_115() {
        head = new ListNode(-1);
        tail = head;
        deque = new LinkedList<>();
    }

    public void push(int val) {
        ListNode node = new ListNode(val);
        deque.offerFirst(node);
        insert(node);
    }

    // 从小到大顺序插入该元素
    private void insert(ListNode node) {
        ListNode p = head.next;
        // 找到第一个大约等于紫的元素的前一个元素
        while (p != null && p.val < node.val) {
            p = p.next;
        }
        // 没有找到比自己大的元素，放在最后，不要少
        if (p == null) {
            tail.next = node;
            node.pre = tail;
            tail = node;
        } else {
            p.pre.next = node;
            node.pre = p.pre;
            node.next = p;
            p.pre = node;
        }
    }

    public void pop() {
        ListNode node = deque.pollFirst();
        if (node != null) {
            removeNode(node);
        }
    }

    private void removeNode(ListNode node) {
        node.pre.next = node.next;
        if (node.next != null) {
            node.next.pre = node.pre;
            // node是tail,不要少
        } else {
            tail = node.pre;
        }
        node.next = null;
        node.pre = null;
    }

    public int top() {
        return deque.peekFirst().val;
    }

    public int getMin() {
        return head.next.val;
    }

    class ListNode {

        int val;

        ListNode pre;

        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
//leetcode submit region end(Prohibit modification and deletion)
