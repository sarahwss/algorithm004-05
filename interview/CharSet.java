/**
 * @(#)CharSet.java, 5月 28, 2023.
 * <p>
 * Copyright 2023 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

import java.util.Scanner;

/**
 * @author wangshuangshuang
 */
public class CharSet {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNext()) { // 注意 while 处理多个 case
            String s = in.next();
            System.out.println(helper(s));
        }
    }

    static String helper(String s) {
        boolean[] dict = new boolean[128];
        StringBuffer sb = new StringBuffer();
        for (char c : s.toCharArray()) {
            if (!dict[c]) {
                sb.append(c);
                dict[c] = true;
            }
        }
        return sb.toString();
    }

}