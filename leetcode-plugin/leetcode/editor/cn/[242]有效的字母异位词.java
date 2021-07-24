package leetcode.editor.cn;//给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
//
// 
//
// 示例 1: 
//
// 
//输入: s = "anagram", t = "nagaram"
//输出: true
// 
//
// 示例 2: 
//
// 
//输入: s = "rat", t = "car"
//输出: false 
//
// 
//
// 提示: 
//
// 
// 1 <= s.length, t.length <= 5 * 104 
// s 和 t 仅包含小写字母 
// 
//
// 
//
// 进阶: 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？ 
// Related Topics 哈希表 字符串 排序 
// 👍 401 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution_242_2 {

    // 仅包含小写字母，最多5000个
    public boolean isAnagram(String s, String t) {
        int n = t.length();
        if (s.length() != n) {
            return false;
        }
        char[] arr1 = s.toCharArray();
        char[] arr2 = t.toCharArray();
        int[] map = new int[26];
        for (int i = 0; i < n; i++) {
            map[arr1[i] - 'a']++;
        }
        for (int i = 0; i < n; i++) {
            int j = arr2[i] - 'a';
            map[j]--;
            if (map[j] < 0) {
                return false;
            }
        }
        return true;
    }
}

class Solution_242 {

    public boolean isAnagram(String s, String t) {
        int n = s.length();
        if (n != t.length()) {
            return false;
        }
        char[] a = s.toCharArray();
        char[] b = t.toCharArray();
        shellSort(a);
        shellSort(b);
        return new String(a).equals(new String(b));
    }

    // 希尔排序
    void shellSort(char[] arr) {
        int n = arr.length;
        for (int gap = n / 2; gap >= 1; gap = gap / 2) {
            // 注意从fap开始，不是从0开始
            for (int i = gap; i < n; i++) {
                int j = i - gap;
                char temp = arr[i];
                // 注意是加减gap
                // 要用temp比较，arr[i]会变化
                while (j >= 0 && temp < arr[j]) {
                    arr[j + gap] = arr[j];
                    j -= gap;
                }
                arr[j + gap] = temp;
            }
        }
    }

    // 堆排序时间超出限制，因为数据不是顺序访问，数据交换次数也多
    void heapSort(char[] arr) {
        int n = arr.length;
        for (int i = n; i > 0; i--) {
            maxHeapfy(arr, i);
            char temp = arr[0];
            arr[0] = arr[i - 1];
            arr[i - 1] = temp;
        }
    }

    private void maxHeapfy(char[] arr, int limit) {
        if (arr.length <= 1 || limit == 1 || limit > arr.length) {
            return;
        }
        // parent序号，考虑节点序号为1，2，3...
        for (int i = limit / 2; i > 0; i--) {
            char parent = arr[i - 1];
            int left = 2 * i;
            int right = left + 1;
            int maxChildId = right > limit ? left : (arr[left - 1] >= arr[right - 1] ? left : right);
            char maxChild = arr[maxChildId - 1];
            if (parent < maxChild) {
                char temp = parent;
                arr[i - 1] = maxChild;
                arr[maxChildId - 1] = temp;
            }
        }

    }

    // 归并排序
    void mergeSort(char[] arr, int left, int right) {
        if (arr.length <= 1 || left >= right) {
            return;
        }
        int mid = (left + right) / 2;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        mergeArray(arr, left, mid, right);
    }

    private void mergeArray(char[] arr, int left, int mid, int right) {
        if (arr.length <= 1 || left >= right) {
            return;
        }
        int i = left;
        int j = mid + 1;
        int n = right - left + 1;
        char[] res = new char[n];
        int k = 0;
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                res[k++] = arr[i++];
            } else {
                res[k++] = arr[j++];
            }
        }
        while (i <= mid) {
            res[k++] = arr[i++];
        }
        while (j <= right) {
            res[k++] = arr[j++];
        }
        for (int l = 0; l < n; l++) {
            arr[left + l] = res[l];
        }
    }


    // 快速排序
    void quickSort(char[] arr, int left, int right) {
        if (left >= right || arr.length <= 1) {
            return;
        }
        char temp = arr[left];
        int l = left;
        int r = right;
        while (l < r) {
            // 注意保存l的话，需要从r开始遍历，这样r不满足要求的元素可以被放到l
            // 别忘了L<r
            while (arr[r] >= temp && l < r) {
                r--;
            }
            arr[l] = arr[r];
            // 别忘了L<r
            while (arr[l] <= temp && l < r) {
                l++;
            }
            // 注意这里不是交换
            // 不满足小于，一定满足大于，则可放到右边r位置，默认r位置的元素不满足，早已被贾环到左边
            arr[r] = arr[l];
        }
        // temp作为标杆，放到中间
        arr[l] = temp;
        quickSort(arr, left, l - 1);
        quickSort(arr, l + 1, right);
    }

    // 字符串可能很长，排序时间超出限制
    void insertSort(char[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int j = i - 1;
            char temp = arr[i];
            // 必须是temp，arr[i]可能变化
            while (j >= 0 && arr[j] > temp) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = temp;
        }
    }

    // 折半插入排序
    void binarySearchInsertSort(char[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            char temp = arr[i];
            int left = 0;
            int right = i - 1;
            // 一种二分策略，非明确相等问题不需要指定跳出条件，最接近temp的元素，一定不会在两个大值之后，或两个小值之前，一定在一大一小之间
            while (left <= right) {
                // middle每趟都要计算，别放在外面
                int mid = (left + right) / 2;
                if (temp < arr[mid]) {
                    right = mid - 1;
                    // 包含等于，等于时，left指向等于的位置,left插在这个等于位置上的元素之前
                } else {
                    left = mid + 1;
                }
            }
            int j = i;
            // 注意这里不是mid，因为left==right，大于时是没找到，left就是插入的位置，等于时是找到
            while (j > left) {
                // 注意不是j+1
                arr[j] = arr[j - 1];
                j--;
            }
            // left是找到的最合适的位置
            arr[left] = temp;
        }
    }
}

class Solution_242_1 {

    public boolean isAnagram(String s, String t) {
        int n = s.length();
        if (n != t.length()) {
            return false;
        }
        int[] map = new int[26];
        char[] a = s.toCharArray();
        for (char c : s.toCharArray()) {
            map[c - 'a']++;
        }
        char[] b = t.toCharArray();
        for (char c : t.toCharArray()) {
            map[c - 'a']--;
            // 长度一样，有人多，就一定有人少
            if (map[c - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
