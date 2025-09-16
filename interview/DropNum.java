/**
 * @(#)DropNum.java, 5月 28, 2023.
 * <p>
 * Copyright 2023 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author wangshuangshuang
 */
public class DropNum {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int n = in.nextInt();
            List<Integer> nums = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                nums.add(i);
            }
            System.out.println(helper(nums));
        }

    }

    static int helper(List<Integer> nums) {
        int i = 0;
        while (nums.size() > 0) {
            i = (i + 2) % nums.size();
            int num = nums.remove(i);
            //            System.out.println(num + " " + nums);
            if (nums.isEmpty()) {
                return num;
            }
        }
        return -1;

    }

}