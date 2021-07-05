package leetcode.editor.cn;//给定一个 m x n 二维字符网格 board 和一个单词（字符串）列表 words，找出所有同时在二维网格和字典中出现的单词。
//
// 单词必须按照字母顺序，通过 相邻的单元格 内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使
//用。 
//
// 
//
// 示例 1： 
//
// 
//输入：board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l"
//,"v"]], words = ["oath","pea","eat","rain"]
//输出：["eat","oath"]
// 
//
// 示例 2： 
//
// 
//输入：board = [["a","b"],["c","d"]], words = ["abcb"]
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// m == board.length 
// n == board[i].length 
// 1 <= m, n <= 12 
// board[i][j] 是一个小写英文字母 
// 1 <= words.length <= 3 * 104 
// 1 <= words[i].length <= 10 
// words[i] 由小写英文字母组成 
// words 中的所有字符串互不相同 
// 
// Related Topics 字典树 数组 字符串 回溯 矩阵 
// 👍 405 👎 0


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution_212 {

    class Trie {

        TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        void insertWord(String word) {
            TrieNode node = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                TrieNode n = node.edges[c - 'a'];
                if (n == null) {
                    n = new TrieNode();
                    n.str = node.str + c;
                    node.edges[c - 'a'] = n;
                    node.size++;
                }
                node = n;
            }
            node.isWord = true;
        }

    }

    class TrieNode {

        TrieNode[] edges = new TrieNode[26];

        boolean isWord;

        String str = "";

        int size;

        TrieNode child(char c) {
            return edges[c - 'a'];
        }

        boolean isEnd(char c) {
            return isWord && size == 0;
        }
    }

    public List<String> findWords(char[][] board, String[] words) {
        int m = board.length;
        if (m == 0) {
            return Collections.emptyList();
        }
        int n = board[0].length;
        // 不同路径可能导致相同结果字符串，此处用set，也方便判断contains
        Set<String> res = new HashSet<>();
        Trie trie = new Trie();
        int maxLen = m * n;
        // 重要，防止有些字符串过长
        for (String word : words) {
            if (word.length() <= maxLen) {
                trie.insertWord(word);
            }
        }
        TrieNode root = trie.root;
        // 字符串开头
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // trieNode查询本来就需要递归，放到递归里面减少不必要查询
                // 变量尽量放到递归内部，每层递归结束都会回收
                // 外层减少不必要的判断、计算和变量
                DFS(board, res, m, n, i, j, root);
            }
        }
        return new ArrayList<>(res);
    }

    void DFS(char[][] board, Set<String> res, int m, int n, int i, int j, TrieNode parent) {
        char temp = board[i][j];
        if (parent == null) {
            return;
        }
        TrieNode node = parent.child(temp);
        if (node == null) {
            return;
        }
        if (!res.contains(node.str) && node.isWord) {
            res.add(node.str);
            // 此处不需要continue，有多个答案，可能有的答案较长
        }
        // 终端词终止迭代，剪枝重点
        if (node.isEnd(temp)) {
            return;
        }
        // 标记访问过
        board[i][j] = '@';
        int[] cols = new int[]{1, 0, -1, 0};
        int[] rows = new int[]{0, -1, 0, 1};
        for (int k = 0; k < 4; k++) {
            int a = i + rows[k];
            int b = j + cols[k];
            if (a < 0 || a >= m || b < 0 || b >= n) {
                continue;
            }
            char c = board[a][b];
            if (c != '@') {
                DFS(board, res, m, n, a, b, node);
            }
        }
        // 还原，方便尝试其它
        board[i][j] = temp;
    }

    public static void main(String[] args) {
        char[][] board = new char[][]{{'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'},
                {'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'},
                {'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'},
                {'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'},
                {'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'},
                {'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'},
                {'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'},
                {'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'},
                {'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'},
                {'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'},
                {'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'},
                {'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'}};
        String[] words = new String[]{"a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa", "aaaaaaaa", "aaaaaaaaa",
                "aaaaaaaaaa"};
        System.out.println(new Solution_212().findWords(board, words));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
