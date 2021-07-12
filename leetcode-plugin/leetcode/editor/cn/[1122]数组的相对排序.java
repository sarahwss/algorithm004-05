package leetcode.editor.cn;//给你两个数组，arr1 和 arr2，
//
// 
// arr2 中的元素各不相同 
// arr2 中的每个元素都出现在 arr1 中 
// 
//
// 对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。未在 arr2 中出现过的元素需要按照升序放在 arr1 的末
//尾。 
//
// 
//
// 示例： 
//
// 
//输入：arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
//输出：[2,2,2,1,4,3,3,9,6,7,19]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= arr1.length, arr2.length <= 1000 
// 0 <= arr1[i], arr2[i] <= 1000 
// arr2 中的元素 arr2[i] 各不相同 
// arr2 中的每个元素 arr2[i] 都出现在 arr1 中 
// 
// Related Topics 数组 哈希表 计数排序 排序 
// 👍 180 👎 0


import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)

class Solution_1121 {

    // 计数排序
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int m = arr1.length;
        // 元素>=0 且小于等于1000
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            int a = arr1[i];
            max = Math.max(max, a);
            min = Math.min(min, a);
        }
        int l = max - min + 1;
        int[] counter = new int[l];
        // 将数组1计数
        for (int i = 0; i < m; i++) {
            counter[arr1[i] - min]++;
        }
        // 先处理数组二的跑徐
        int index = 0;
        int[] ans = new int[m];
        for (int i : arr2) {
            // 循环完后count[i]变成0
            while (counter[i - min]-- > 0) {
                ans[index++] = i;
            }
        }
        // 处理剩余不为0的计数，即不属于数组2的剩余元素
        for (int i = 0; i < l; i++) {
            while (counter[i]-- > 0) {
                ans[index++] = i + min;
            }
        }
        return ans;
    }


}

class Solution_1122_1 {

    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int n = arr2.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr2.length; i++) {
            map.put(arr2[i], i);
        }
        // 确保不在arr2里的排在最后
        sort(arr1, Comparator.comparing(x -> map.containsKey(x) ? map.get(x) : (n + x)));
        return arr1;
    }

    // 冒泡排序
    void sort(int[] arr1, Comparator<Integer> comparator) {
        int n = arr1.length;
        for (int i = n - 1; i >= 1; i--) {
            for (int j = 0; j < i; j++) {
                if (comparator.compare(arr1[j], arr1[j + 1]) > 0) {
                    int temp = arr1[j];
                    arr1[j] = arr1[j + 1];
                    arr1[j + 1] = temp;
                }
            }
        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)
