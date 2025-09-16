//给你一个变量对数组 equations 和一个实数值数组 values 作为已知条件，其中 equations[i] = [Ai, Bi] 和 
//values[i] 共同表示等式 Ai / Bi = values[i] 。每个 Ai 或 Bi 是一个表示单个变量的字符串。 
//
// 另有一些以数组 queries 表示的问题，其中 queries[j] = [Cj, Dj] 表示第 j 个问题，请你根据已知条件找出 Cj / Dj =
// ? 的结果作为答案。 
//
// 返回 所有问题的答案 。如果存在某个无法确定的答案，则用 -1.0 替代这个答案。如果问题中出现了给定的已知条件中没有出现的字符串，也需要用 -1.0 替
//代这个答案。 
//
// 注意：输入总是有效的。你可以假设除法运算中不会出现除数为 0 的情况，且不存在任何矛盾的结果。 
//
// 
//
// 示例 1： 
//
// 
//输入：equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"]
//,["b","a"],["a","e"],["a","a"],["x","x"]]
//输出：[6.00000,0.50000,-1.00000,1.00000,-1.00000]
//解释：
//条件：a / b = 2.0, b / c = 3.0
//问题：a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
//结果：[6.0, 0.5, -1.0, 1.0, -1.0 ]
// 
//
// 示例 2： 
//
// 
//输入：equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], 
//queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
//输出：[3.75000,0.40000,5.00000,0.20000]
// 
//
// 示例 3： 
//
// 
//输入：equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],[
//"a","c"],["x","y"]]
//输出：[0.50000,2.00000,-1.00000,-1.00000]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= equations.length <= 20 
// equations[i].length == 2 
// 1 <= Ai.length, Bi.length <= 5 
// values.length == equations.length 
// 0.0 < values[i] <= 20.0 
// 1 <= queries.length <= 20 
// queries[i].length == 2 
// 1 <= Cj.length, Dj.length <= 5 
// Ai, Bi, Cj, Dj 由小写英文字母与数字组成 
// 
// Related Topics 深度优先搜索 广度优先搜索 并查集 图 数组 最短路 👍 901 👎 0


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Integer> indexMap = new HashMap<>();
        UnionFind unionFind = new UnionFind(equations.size() * 2);
        double[] res = new double[queries.size()];
        for (int i = 0, equationsSize = equations.size(); i < equationsSize; i++) {
            List<String> equation = equations.get(i);
            String a = equation.get(0);
            String b = equation.get(1);
            indexMap.putIfAbsent(a, indexMap.size());
            indexMap.putIfAbsent(b, indexMap.size());
            int x = indexMap.get(a);
            int y = indexMap.get(b);
            unionFind.union(x, y, values[i]);
        }

        for (int i = 0, queriesSize = queries.size(); i < queriesSize; i++) {
            List<String> query = queries.get(i);
            String a = query.get(0);
            String b = query.get(1);
            Integer x = indexMap.get(a);
            Integer y = indexMap.get(b);
            if (x == null || y == null) {
                res[i] = -1.0;
            } else {
                res[i] = unionFind.isConnected(x, y);
            }

        }
        return res;

    }

    //    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
    //        double[] res = new double[queries.size()];
    //        Map<String, List<Integer>> dict = new HashMap<>(equations.size());
    //        for (int i = 0, equationsSize = equations.size(); i < equationsSize; i++) {
    //            List<String> equation = equations.get(i);
    //            String a = equation.get(0);
    //            String b = equation.get(1);
    //            dict.putIfAbsent(a, new ArrayList<>());
    //            dict.get(a).add(i);
    //            dict.putIfAbsent(b, new ArrayList<>());
    //            dict.get(b).add(i);
    //        }
    //        for (int i = 0, queriesSize = queries.size(); i < queriesSize; i++) {
    //            String start = queries.get(i).get(0);
    //            String end = queries.get(i).get(1);
    //            res[i] = bsf(start, end, dict, equations, values);
    //        }
    //        return res;
    //    }

    //    double bsf(String start, String end, Map<String, List<Integer>> dict, List<List<String>> equations,
    //               double[] values) {
    //        if (!dict.containsKey(start) || !dict.containsKey(end)) {
    //            return -1.0;
    //        }
    //        if (start.equals(end)) {
    //            return 1.0;
    //        }
    //        Deque<String> q1 = new ArrayDeque<>();
    //        Deque<String> q2 = new ArrayDeque<>();
    //
    //        Set<String> v1 = new HashSet<>();
    //        Set<String> v2 = new HashSet<>();
    //        q1.offerLast(start);
    //        q2.offerLast(end);
    //        v1.add(start);
    //        v2.add(end);
    //        Map<String, Double> resMap = new HashMap<>();
    //        while (!q1.isEmpty() && !q2.isEmpty()) {
    //            // 保证q1短于q2
    //            if (q1.size() > q2.size()) {
    //                Deque<String> temp = q1;
    //                q1 = q2;
    //                q2 = temp;
    //                Set<String> t = v1;
    //                v1 = v2;
    //                v2 = t;
    //            }
    //            while (!q1.isEmpty()) {
    //                String s1 = q1.pollFirst();
    //                List<Integer> indexes = dict.getOrDefault(s1, Collections.emptyList());
    //                //                System.out.println(indexes);
    //                for (int index : indexes) {
    //                    List<String> equation = equations.get(index);
    //                    String a = equation.get(0);
    //                    String b = equation.get(1);
    //                    double value = values[index];
    //                    if (b.equals(s1)) {
    //                        value = 1.0 / values[index];
    //                        String temp = a;
    //                        a = b;
    //                        b = temp;
    //                    }
    //
    //                    if (v1.contains(b)) {
    //                        continue;
    //                    }
    //
    //                    if (v2.contains(b)) {
    //                        return resMap.getOrDefault(a, 1.0) * value;
    //                    }
    //                    resMap.put(b, resMap.getOrDefault(a, 1.0) * value);
    //                    v1.add(b);
    //                    q1.offerLast(b);
    //                }
    //
    //            }
    //        }
    //        return -1.0;
    //    }


}

class UnionFind {

    int[] parent;

    double[] weight;

    UnionFind(int size) {
        parent = new int[size];
        weight = new double[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            weight[i] = 1.0;
        }
    }

    // 有weight，只能用递归
    int findParent(int x) {
        if (parent[x] == x) {
            return x;
        }
        int origin = parent[x];
        parent[x] = findParent(origin);
        weight[x] *= weight[origin];
        return parent[x];
    }

    void union(int x, int y, double value) {
        int p = findParent(x);
        int q = findParent(y);
        if (p == q) { // 相等的话，不需要改weight
            return;
        }
        parent[p] = q;
        weight[p] = weight[y] * value / weight[x];
    }

    double isConnected(int x, int y) {
        int p = findParent(x);
        int q = findParent(y);
        if (p == q) {
            return weight[x] / weight[y];
        }
        return -1.0;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
