package leetcode.editor.cn;//数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
//
// 
//
// 示例 1： 
//
// 
//输入：n = 3
//输出：["((()))","(()())","(())()","()(())","()()()"]
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：["()"]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 8 
// 
// Related Topics 字符串 动态规划 回溯 
// 👍 1863 👎 0


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
// BFS
class Solution_22_4 {

    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        Deque<String> deque = new ArrayDeque<>();
        Map<String, Integer> left = new HashMap<>();
        deque.offerFirst("");
        left.put("", 0);
        while (!deque.isEmpty()) {
            String s = deque.pollLast();
            if (s.length() == 2 * n) {
                res.add(s);
                continue;
            }
            int l = left.get(s);
            if (l < n) {
                String str = s + "(";
                deque.offerFirst(str);
                left.put(str, l + 1);
            }
            if (s.length() - l < l) {
                String str = s + ")";
                deque.offerFirst(str);
                // 别少加
                left.put(str, l);
            }
        }
        return res;
    }
}

// DFS
class Solution_22_3 {

    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        generateParenthesis(n, 0, new StringBuilder(), res);
        return res;
    }

    private void generateParenthesis(int n, int left, StringBuilder sb, List<String> res) {
        if (sb.length() == n * 2) {
            res.add(sb.toString());
        }
        // 可以放左括号
        if (left < n) {
            generateParenthesis(n, left + 1, sb.append('('), res);
            sb.deleteCharAt(sb.length() - 1);
        }
        if (sb.length() - left < left) {
            generateParenthesis(n, left, sb.append(')'), res);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}

class Solution_22_2 {

    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        generateParenthesis(0, n, new StringBuilder(), res);
        return res;
    }

    void generateParenthesis(int left, int n, StringBuilder sb, List<String> res) {
        int l = sb.length();
        if (l == 2 * n) {
            res.add(sb.toString());
            return;
        }
        // 左括号还有余量，放左括号
        if (left < n) {
            sb.append('(');
            generateParenthesis(left + 1, n, sb, res);
            // 这里刚好是l，也可以是length-1
            sb.deleteCharAt(l);
        }
        // 右括号少于左括号，可以放右括号
        if (l - left < left) {
            sb.append(')');
            generateParenthesis(left, n, sb, res);
            sb.deleteCharAt(l);
        }
    }
}

// 非回溯，不推荐
class Solution_22_1 {

    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        generateParenthesis(0, n, "", res);
        return res;
    }

    void generateParenthesis(int left, int n, String s, List<String> res) {
        int l = s.length();
        if (l == 2 * n) {
            res.add(s);
            return;
        }
        // 左括号还有余量，放左括号
        if (left < n) {
            generateParenthesis(left + 1, n, s + "(", res);
        }
        // 右括号少于左括号，可以放右括号
        if (l - left < left) {
            generateParenthesis(left, n, s + ")", res);
        }
    }
}

class Solution_22 {

    public List<String> generateParenthesis(int n) {
        List<String> res = new LinkedList<>();
        DFS(n, 0, "", res);
        return res;


    }

    void DFS(int n, int left, String str, List<String> res) {
        int l = str.length();
        if (str.length() == n * 2) {
            res.add(str);
            return;
        }
        int right = l - left;
        if (left < n) {
            DFS(n, left + 1, str + "(", res);
        }
        if (right < left) {
            DFS(n, left, str + ")", res);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
