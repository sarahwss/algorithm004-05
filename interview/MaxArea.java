/**
 * @(#)MaxArea.java, 5月 29, 2023.
 * <p>
 * Copyright 2023 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author wangshuangshuang
 */
public class MaxArea {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);


        while (in.hasNext()) {// 注意，如果输入是多个测试用例，请通过while循环处理多个测试用例

            List<Integer> heights =
                    Arrays.stream(in.next().split(",")).map(Integer::parseInt).collect(Collectors.toList());
            System.out.println(maxArea(heights));


        }
        Arrays.a
    }

    private static int maxArea(List<Integer> heights) {

        // 固定右端点，压缩左端点
        int max = 0;
        Deque<Integer> min = new ArrayDeque<>();

        for (int i = 0; i < heights.size(); i++) {
            int h = heights.get(i);
            while (min.peekFirst() != null && h < heights.get(min.peekFirst())) {

                max = Math.max(max, h * (i - min.peekFirst()));
                if (heights.get(min.peekFirst()) > h) {
                    min.pollFirst();
                }
            }
            min.offerFirst(i);


        }

    }

}