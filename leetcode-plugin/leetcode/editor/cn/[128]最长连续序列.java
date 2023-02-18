//给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。 
//
// 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [100,4,200,1,3,2]
//输出：4
//解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。 
//
// 示例 2： 
//
// 
//输入：nums = [0,3,7,2,5,8,4,6,0,1]
//输出：9
// 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 10⁵ 
// -10⁹ <= nums[i] <= 10⁹ 
// 
// Related Topics 并查集 数组 哈希表 👍 1543 👎 0


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        int maxCount = 1;
        int curCount = 1;
        for (int i = 1; i < nums.length; i++) {

            if (nums[i] - nums[i - 1] == 1) {
                curCount++;
            } else {
                curCount = 1;
            }
            maxCount = Math.max(maxCount, curCount);
            // 去重
            while (i < nums.length - 1 && nums[i + 1] == nums[i]) {
                i++;
            }
        }
        return maxCount;
    }

    // 计数排序（适合数值不大的裴旭，不适合本题）
    //    void sort(int[] nums) {
    //        int min = Integer.MAX_VALUE;
    //        int max = Integer.MIN_VALUE;
    //        for (int i = 0; i < nums.length; i++) {
    //            min = Math.min(min, nums[i]);
    //            max = Math.max(max, nums[i]);
    //        }
    //        int[] count = new int[max - min + 1];
    //        for (int i = 0; i < nums.length; i++) {
    //            count[nums[i] - min]++;
    //        }
    //        int k = 0;
    //        for (int i = min; i <= max; i++) {
    //            int c = count[i - min];
    //            while (c-- > 0) {
    //                nums[k++] = i;
    //            }
    //        }
    ////        System.out.println(Arrays.toString(nums));
    //
    //    }
    // 快速排序，比基数排序慢
    void quickSort(int[] nums) {
        if (nums.length <= 1) {
            return;
        }
        quickSort(nums, 0, nums.length - 1);
    }

    void quickSort(int[] nums, int l, int r) {
        if (l >= r) {
            return;
        }

        int pivot = r;
        int temp = nums[r];
        // 从左到右，左边都比pivot小
        int i = l, j = r;
        while (i < j) {
//            System.out.println(pivot + " " + i + " " + j);
            while (i < pivot && nums[i] <= temp) {
                i++;
            }
            nums[pivot] = nums[i];
            pivot = i;

            while (j > pivot && nums[j] > temp) {
                j--;
            }
            nums[pivot] = nums[j];
            pivot = j;
//            System.out.println(Arrays.toString(nums) + " " + pivot + " " + i + " " + j);
        }
        nums[pivot] = temp;
        quickSort(nums, l, pivot - 1);
        quickSort(nums, pivot + 1, r);
//        System.out.println(Arrays.toString(nums));


    }


    // 基数排序，事实上比快速排序满
    void sort(int[] nums) {
        int radix = 10;
        int max = Integer.MIN_VALUE;

        List<Integer> positive = new ArrayList<>();
        List<Integer> negative = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, Math.abs(nums[i]));
            if (nums[i] >= 0) {
                positive.add(nums[i]);
            } else {
                negative.add(nums[i]);
            }
        }
        for (int base = 1; base <= max; base *= radix) {
            List<Integer>[] arr = new List[radix];
            for (int k = 0; k < radix; k++) {
                arr[k] = new ArrayList<>();
            }
            for (Integer num : negative) {
                int quotient = -num / base % radix;
                //                System.out.println(num + "     " + base + "     " + quotient);
                arr[quotient].add(num);
            }
            negative.clear();
            for (List<Integer> l1 : arr) {
                negative.addAll(l1);
            }


            for (int k = 0; k < radix; k++) {
                arr[k].clear();
            }
            for (Integer num : positive) {
                int quotient = num / base % radix;
                arr[quotient].add(num);
            }
            positive.clear();
            for (List<Integer> l1 : arr) {
                positive.addAll(l1);
            }
            //            System.out.println(base + "     " + negative + "     " + positive);
        }
        int k = 0;
        for (int i = negative.size() - 1; i >= 0; i--) {
            nums[k++] = negative.get(i);
        }
        for (int i = 0; i < positive.size(); i++) {
            nums[k++] = positive.get(i);
        }
        //        System.out.println(Arrays.toString(nums));
    }


}
//leetcode submit region end(Prohibit modification and deletion)
