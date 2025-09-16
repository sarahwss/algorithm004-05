/**
 * @(#)RadixTrans.java, 5月 28, 2023.
 * <p>
 * Copyright 2023 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

import java.util.Scanner;

/**
 * @author wangshuangshuang
 */
public class RadixTrans {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        int res = 0;
        for (int i = 2; i < s.length(); i++) {
            char c = Character.toLowerCase(s.charAt(i));
            if (Character.isDigit(c)) {
                res = res * 16 + (c - '0');
            } else {
                res = res * 16 + (c - 'a' + 10);
            }
            System.out.println(c);
        }
        System.out.println(res);
    }

}