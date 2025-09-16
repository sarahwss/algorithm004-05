/**
 * @(#)RancomSort.java, 5月 28, 2023.
 * <p>
 * Copyright 2023 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

import java.util.Scanner;

/**
 * @author wangshuangshuang
 */
public class RandomSort {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        boolean[] arr = new boolean[501];
        // 注意 hasNext 和 hasNextLine 的区别
        for (int i = 0; i < n; i++) { // 注意 while 处理多个 case
            arr[in.nextInt()] = true;
        }
        for (int i = 1; i <= 500; i++) {
            if (arr[i]) {
                System.out.println(i);
            }
        }
    }


}