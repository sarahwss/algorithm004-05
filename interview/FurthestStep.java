/**
 * @(#)FuthhestStep.java, 5月 29, 2023.
 * <p>
 * Copyright 2023 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */


import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author wangshuangshuang
 */
public class FurthestStep {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);


        while (in.hasNext()) {// 注意，如果输入是多个测试用例，请通过while循环处理多个测试用例
            String s = in.next();

            Pattern pattern = Pattern.compile("([a-z0-9A-Z]+\\(([0-9]{1,3}),([0-9]{1,3})\\))");
            Matcher matcher = pattern.matcher(s);

            int max = 0;
            int[] position = new int[2];
            while (matcher.find()) {
                String a = matcher.group(2);
                String b = matcher.group(3);
                if (a.startsWith("0") || b.startsWith("0")) {
                    continue;
                }
                int i = Integer.parseInt(a);
                int j = Integer.parseInt(b);
                if (i <= 0 || i >= 1000 || j <= 0 || j >= 1000) {
                    continue;
                }
                int step = i * i + j * j;
                if (step > max) {
                    max = step;
                    position[0] = i;
                    position[1] = j;
                }

            }
            System.out.println("(" + position[0] + "," + position[1] + ")");
        }
    }


}