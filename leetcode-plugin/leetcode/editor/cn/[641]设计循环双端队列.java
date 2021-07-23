package leetcode.editor.cn;//设计实现双端队列。
//你的实现需要支持以下操作： 
//
// 
// MyCircularDeque(k)：构造函数,双端队列的大小为k。 
// insertFront()：将一个元素添加到双端队列头部。 如果操作成功返回 true。 
// insertLast()：将一个元素添加到双端队列尾部。如果操作成功返回 true。 
// deleteFront()：从双端队列头部删除一个元素。 如果操作成功返回 true。 
// deleteLast()：从双端队列尾部删除一个元素。如果操作成功返回 true。 
// getFront()：从双端队列头部获得一个元素。如果双端队列为空，返回 -1。 
// getRear()：获得双端队列的最后一个元素。 如果双端队列为空，返回 -1。 
// isEmpty()：检查双端队列是否为空。 
// isFull()：检查双端队列是否满了。 
// 
//
// 示例： 
//
// MyCircularDeque circularDeque = new MycircularDeque(3); // 设置容量大小为3
//circularDeque.insertLast(1);			        // 返回 true
//circularDeque.insertLast(2);			        // 返回 true
//circularDeque.insertFront(3);			        // 返回 true
//circularDeque.insertFront(4);			        // 已经满了，返回 false
//circularDeque.getRear();  				// 返回 2
//circularDeque.isFull();				        // 返回 true
//circularDeque.deleteLast();			        // 返回 true
//circularDeque.insertFront(4);			        // 返回 true
//circularDeque.getFront();				// 返回 4
//  
//
// 
//
// 提示： 
//
// 
// 所有值的范围为 [1, 1000] 
// 操作次数的范围为 [1, 1000] 
// 请不要使用内置的双端队列库。 
// 
// Related Topics 设计 队列 数组 链表 
// 👍 82 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class MyCircularDeque {

    int[] data;

    int capacity;

    // 头节点
    int front;

    // 尾节点（最后一个元素）
    int rear;

    /**
     * Initialize your data structure here. Set the size of the deque to be k.
     */
    public MyCircularDeque(int k) {
        capacity = k;
        // 多一个位置，为了使空和满的条件区分开
        data = new int[k + 1];
        front = 0;
        rear = k;
    }

    /**
     * Adds an item at the front of Deque. Return true if the operation is successful.
     */
    public boolean insertFront(int value) {
        if (isFull()) {
            return false;
        }
        // front - 1 + capacity + 1
        front = (front + capacity) % (capacity + 1);
        //        System.out.println("insertFront front " + front);
        data[front] = value;
        return true;
    }

    /**
     * Adds an item at the rear of Deque. Return true if the operation is successful.
     */
    public boolean insertLast(int value) {
        if (isFull()) {
            return false;
        }
        rear = (rear + 1) % (capacity + 1);
        //        System.out.println("insertLast rear " + rear);
        data[rear] = value;
        return true;
    }

    /**
     * Deletes an item from the front of Deque. Return true if the operation is successful.
     */
    public boolean deleteFront() {
        if (isEmpty()) {
            return false;
        }
        front = (front + 1) % (capacity + 1);
        //        System.out.println("deleteFront front " + front);
        return true;
    }

    /**
     * Deletes an item from the rear of Deque. Return true if the operation is successful.
     */
    public boolean deleteLast() {
        if (isEmpty()) {
            return false;
        }
        // rear-1+capacity+1
        rear = (rear + capacity) % (capacity + 1);
        //        System.out.println("deleteLast rear " + rear);
        return true;
    }

    /**
     * Get the front item from the deque.
     */
    public int getFront() {
        if (isEmpty()) {
            return -1;
        }
        // 注意这里是获取数据，不是获取索引
        return data[front];
    }

    /**
     * Get the last item from the deque.
     */
    public int getRear() {
        if (isEmpty()) {
            return -1;
        }
        // 注意这里是获取数据，不是获取索引
        return data[rear];
    }

    /**
     * Checks whether the circular deque is empty or not.
     */
    public boolean isEmpty() {
        // 尾在头前面，注意这里是capacity + 1
        return (rear + 1) % (capacity + 1) == front;
    }

    /**
     * Checks whether the circular deque is full or not.
     */
    public boolean isFull() {
        // 尾在头前面的前面，注意这里是capacity + 1
        return (rear + 2) % (capacity + 1) == front;
    }

    public static void main(String[] args) {
        MyCircularDeque deque = new MyCircularDeque(5);
        deque.insertFront(7);
        deque.insertLast(0);
        deque.getFront();
        deque.insertLast(3);
        deque.getFront();
        deque.insertFront(9);
        deque.getRear();
        deque.getFront();
        deque.getFront();
        deque.deleteLast();
        deque.getRear();
    }
}

/**
 * Your MyCircularDeque object will be instantiated and called as such:
 * MyCircularDeque obj = new MyCircularDeque(k);
 * boolean param_1 = obj.insertFront(value);
 * boolean param_2 = obj.insertLast(value);
 * boolean param_3 = obj.deleteFront();
 * boolean param_4 = obj.deleteLast();
 * int param_5 = obj.getFront();
 * int param_6 = obj.getRear();
 * boolean param_7 = obj.isEmpty();
 * boolean param_8 = obj.isFull();
 */
//leetcode submit region end(Prohibit modification and deletion)
