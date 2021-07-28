package leetcode.editor.cn;//字典 wordList 中从单词 beginWord 和 endWord 的 转换序列 是一个按下述规格形成的序列：
//
// 
// 序列中第一个单词是 beginWord 。 
// 序列中最后一个单词是 endWord 。 
// 每次转换只能改变一个字母。 
// 转换过程中的中间单词必须是字典 wordList 中的单词。 
// 
//
// 给你两个单词 beginWord 和 endWord 和一个字典 wordList ，找到从 beginWord 到 endWord 的 最短转换序列 中
//的 单词数目 。如果不存在这样的转换序列，返回 0。 
// 
//
// 示例 1： 
//
// 
//输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","lo
//g","cog"]
//输出：5
//解释：一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog", 返回它的长度 5。
// 
//
// 示例 2： 
//
// 
//输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","lo
//g"]
//输出：0
//解释：endWord "cog" 不在字典中，所以无法进行转换。 
//
// 
//
// 提示： 
//
// 
// 1 <= beginWord.length <= 10 
// endWord.length == beginWord.length 
// 1 <= wordList.length <= 5000 
// wordList[i].length == beginWord.length 
// beginWord、endWord 和 wordList[i] 由小写英文字母组成 
// beginWord != endWord 
// wordList 中的所有字符串 互不相同 
// 
// Related Topics 广度优先搜索 哈希表 字符串 
// 👍 794 👎 0


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution_127_2 {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return 0;
        }
        int n = beginWord.length();
        // 注意这里是set，否则会造成结果重复
        Map<String, Set<String>> dict = new HashMap<>();
        for (String s : wordList) {
            char[] arr = s.toCharArray();
            for (int i = 0; i < n; i++) {
                char c = arr[i];
                arr[i] = '*';
                String key = new String(arr);
                dict.putIfAbsent(key, new HashSet<>());
                dict.get(key).add(s);
                arr[i] = c;
            }
        }
        Deque<String> deque1 = new ArrayDeque<>();
        Map<String, Integer> visited1 = new HashMap<>();
        Deque<String> deque2 = new ArrayDeque<>();
        Map<String, Integer> visited2 = new HashMap<>();
        deque1.offerFirst(beginWord);
        visited1.put(beginWord, 1);
        deque2.offerFirst(endWord);
        visited2.put(endWord, 1);
        // 注意这里是&&，有一个为空的话代表没有其它路径了
        while (!deque1.isEmpty() && !deque2.isEmpty()) {
            // 默认visited1i少
            if (visited1.size() > visited2.size()) {
                Deque<String> q = deque1;
                deque1 = deque2;
                deque2 = q;
                Map<String, Integer> v = visited1;
                visited1 = visited2;
                visited2 = v;
            }
            String s = deque1.pollLast();
            int c1 = visited1.get(s);
            char[] arr = s.toCharArray();
            for (int i = 0; i < n; i++) {
                char c = arr[i];
                arr[i] = '*';
                String key = new String(arr);
                for (String str : dict.getOrDefault(key, Collections.emptySet())) {
                    // 加入之前先判断，防止多余加入队列
                    Integer c2 = visited2.get(str);
                    if (c2 != null) {
                        return c1 + c2;
                    }
                    if (!visited1.containsKey(str)) {
                        deque1.offerFirst(str);
                        visited1.put(str, c1 + 1);
                    }
                }
                arr[i] = c;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(new Solution_127_2().ladderLength("hot", "dog", Arrays.asList("hot", "dog")));
    }
}

class Solution_127_1 {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return 0;
        }
        int n = beginWord.length();
        // 注意这里是set，虽然List也不影响结果
        Map<String, Set<String>> dict = new HashMap<>();
        for (String s : wordList) {
            char[] arr = s.toCharArray();
            for (int i = 0; i < n; i++) {
                char c = arr[i];
                arr[i] = '*';
                String key = new String(arr);
                dict.putIfAbsent(key, new HashSet<>());
                dict.get(key).add(s);
                arr[i] = c;
            }
        }
        Deque<String> deque = new ArrayDeque<>();
        Map<String, Integer> visited = new HashMap<>();
        deque.offerFirst(beginWord);
        visited.put(beginWord, 1);
        while (!deque.isEmpty()) {
            String s = deque.pollLast();
            char[] arr = s.toCharArray();
            for (int i = 0; i < n; i++) {
                char c = arr[i];
                arr[i] = '*';
                String key = new String(arr);
                for (String str : dict.getOrDefault(key, Collections.emptySet())) {
                    // 加入之前先判断，防止多余加入队列
                    if (str.equals(endWord)) {
                        return visited.get(s) + 1;
                    }
                    if (!visited.containsKey(str)) {
                        deque.offerFirst(str);
                        visited.put(str, visited.get(s) + 1);
                    }
                }
                arr[i] = c;
            }
        }
        return 0;
    }
}

// 双向BFS
class Solution_127 {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Map<String, Set<String>> map = new HashMap<>();
        boolean endExists = false;
        int n = beginWord.length();
        for (String word : wordList) {
            if (endWord.equals(word)) {
                endExists = true;
            }
            char[] arr = word.toCharArray();
            for (int i = 0; i < n; i++) {
                char temp = arr[i];
                arr[i] = '*';
                String s = new String(arr);
                map.putIfAbsent(s, new HashSet<>());
                map.get(s).add(word);
                arr[i] = temp;
            }
        }
        if (!endExists) {
            return 0;
        }
        Deque<String> queue1 = new ArrayDeque<>();
        Deque<String> queue2 = new ArrayDeque<>();
        Map<String, Integer> visited1 = new HashMap<>();
        Map<String, Integer> visited2 = new HashMap<>();
        queue1.offer(beginWord);
        visited1.put(beginWord, 1);
        //end word别忘了加进去
        queue2.offer(endWord);
        visited2.put(endWord, 1);
        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            if (visited2.size() < visited1.size()) {
                Deque<String> q = queue1;
                queue1 = queue2;
                queue2 = q;
                Map<String, Integer> v = visited1;
                visited1 = visited2;
                visited2 = v;
            }
            String s = queue1.poll();
            char[] arr = s.toCharArray();
            for (int i = 0; i < n; i++) {
                char c = arr[i];
                arr[i] = '*';
                Set<String> set = map.getOrDefault(new String(arr), Collections.emptySet());
                //                System.out.println(set);
                for (String str : set) {
                    Integer count2 = visited2.get(str);
                    Integer count1 = visited1.get(str);
                    //                    System.out.println(str + " " + count2 + "" + count1);
                    if (count2 != null) {
                        return count2 + visited1.get(s);
                    }
                    if (count1 == null) {
                        visited1.put(str, visited1.getOrDefault(s, 0) + 1);
                        queue1.offer(str);
                    }
                }
                arr[i] = c;
            }

        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(
                new Solution_127().ladderLength("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log", "cog")));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
