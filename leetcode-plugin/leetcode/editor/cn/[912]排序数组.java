package leetcode.editor.cn;//给你一个整数数组 nums，请你将该数组升序排列。
//
// 
//
// 
// 
//
// 示例 1： 
//
// 输入：nums = [5,2,3,1]
//输出：[1,2,3,5]
// 
//
// 示例 2： 
//
// 输入：nums = [5,1,1,2,0,0]
//输出：[0,0,1,1,2,5]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 50000 
// -50000 <= nums[i] <= 50000 
// 
// Related Topics 数组 分治 桶排序 计数排序 基数排序 排序 堆（优先队列） 归并排序 
// 👍 360 👎 0


import java.util.Arrays;
import java.util.Random;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution_912 {

    public int[] sortArray(int[] nums) {
        if (nums.length == 0) {
            return nums;
        }
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    private void quickSort(int[] nums, int start, int end) {
        int left = start;
        int right = end;
        if (left < right) {
            // 加上random提高效率
            int pivot = new Random().nextInt(end - start) + start;
            int temp = nums[pivot];
            nums[pivot] = nums[start];
            nums[start] = temp;
            while (left < right) {
                while (nums[right] > temp && right > left) {
                    right--;
                }
                nums[left] = nums[right];
                while (nums[left] <= temp && left < right) {
                    left++;
                }
                nums[right] = nums[left];
            }
            nums[left] = temp;
            quickSort(nums, start, left - 1);
            quickSort(nums, left + 1, end);
        }
    }
}

class Solution_剑指_912 {

    public int[] sortArray(int[] nums) {
        if (nums.length == 0) {
            return nums;
        }
        quickSort(nums, 0, nums.length - 1);
        return nums;

    }

    // 不包含end
    private void quickSort(int[] nums, int start, int end) {
        // 别少了大于的边界判断
        if (start >= end) {
            return;
        }
        // 必须使用随机，否则排序将很慢
        int pivot = new Random().nextInt(end - start) + start;
        swap(nums, pivot, end);
        int i = start - 1;
        for (int j = start; j < end; j++) {
            if (nums[j] <= nums[end]) {
                i++;
                // 将未排序的下一个位置和j交换
                swap(nums, j, i);
            }
        }
        // 剩余的全是比num[pivot]大的元素
        i++;
        swap(nums, i, end);
        // 别少了quickSort前边一段
        quickSort(nums, start, i - 1);
        // 注意这里是+1,是对大于pivot的元素排序
        quickSort(nums, i + 1, end);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        System.out.println(
                Arrays.toString(new Solution_剑指_912().sortArray(new int[]{-4, 0, 7, 4, 9, -5, -1, 0, -7, -1})));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
