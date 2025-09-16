/**
 * @(#)NumPuzzle.java, 5月 29, 2023.
 * <p>
 * Copyright 2023 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author wangshuangshuang
 */
public class NumPuzzle {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[][] arr = new int[9][9];
        boolean[][] rows = new boolean[9][10];
        boolean[][] cols = new boolean[9][10];
        boolean[][] boxes = new boolean[9][10];
        int i = 0;
        // 注意 hasNext 和 hasNextLine 的区别
        while (i < 9) {
            String s = in.nextLine();
            // 注意 while 处理多个 case
            String[] vals = s.split(" ");
            for (int j = 0; j < 9; j++) {
                int val = Integer.parseInt(vals[j]);
                arr[i][j] = val;
                if (val != 0) {
                    rows[i][val] = true;
                    cols[j][val] = true;
                    boxes[i / 3 * 3 + j / 3][val] = true;
                }
            }
            i++;
        }

        helper(arr, 0, rows, cols, boxes);
        for (i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }


    private static boolean helper(int[][] arr, int index, boolean[][] rows, boolean[][] cols, boolean[][] boxes) {
        if (index == 81) {
            return true;
        }
        int i = index / 9;
        int j = index % 9;
        if (arr[i][j] != 0) {
            return helper(arr, index + 1, rows, cols, boxes);
        }
        for (int k = 1; k <= 9; k++) {
            if (rows[i][k] || cols[j][k] || boxes[i / 3 * 3 + j / 3][k]) {
                continue;
            }
            arr[i][j] = k;
            rows[i][k] = true;
            cols[j][k] = true;
            boxes[i / 3 * 3 + j / 3][k] = true;
            if (helper(arr, index + 1, rows, cols, boxes)) {
                return true;
            }
            arr[i][j] = 0;// 不能少，最后一个循环不成功会导致值被错误填上，不会被下一个循环覆盖
            rows[i][k] = false;
            cols[j][k] = false;
            boxes[i / 3 * 3 + j / 3][k] = false;
        }
        return false;


    }
}