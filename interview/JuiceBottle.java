/**
 * @(#)JuiceBottle.java, 5月 24, 2023.
 * <p>
 * Copyright 2023 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

/**
 *
 * 1.
 * 汽水瓶
 * 某商店规定：三个空汽水瓶可以换一瓶汽水，允许向老板借空汽水瓶（但是必须要归还）。
 * 小张手上有n个空汽水瓶，她想知道自己最多可以喝到多少瓶汽水。
 * 数据范围：输入的正整数满足
 * 1
 * ≤
 * �
 * ≤
 * 100
 *
 * 1≤n≤100
 *
 * 注意：本题存在多组输入。输入的 0 表示输入结束，并不用输出结果。
 * 时间限制：C/C++ 1秒，其他语言2秒
 * 空间限制：C/C++ 32M，其他语言64M
 */


import java.util.Scanner;

/**
 * @author wangshuangshuang
 */
public class JuiceBottle {


    // 注意类名必须为 Main, 不要有任何 package xxx 信息

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            int a = in.nextInt();
            if (a == 0) {
                break;
            }
            System.out.println(cal(a));
        }

    }

    private static int cal(int n) {
        if (n < 2) {
            return 0;
        }
        if (n == 2 || n == 3) {
            return 1;
        }
        int m = n / 3;
        int remain = n % 3;

        return m+cal(m+remain);


    }


}