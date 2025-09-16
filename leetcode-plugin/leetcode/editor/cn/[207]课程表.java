//你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。 
//
// 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表
//示如果要学习课程 ai 则 必须 先学习课程 bi 。 
//
// 
// 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。 
// 
//
// 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。 
//
// 
//
// 示例 1： 
//
// 
//输入：numCourses = 2, prerequisites = [[1,0]]
//输出：true
//解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。 
//
// 示例 2： 
//
// 
//输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
//输出：false
//解释：总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。 
//
// 
//
// 提示： 
//
// 
// 1 <= numCourses <= 10⁵ 
// 0 <= prerequisites.length <= 5000 
// prerequisites[i].length == 2 
// 0 <= ai, bi < numCourses 
// prerequisites[i] 中的所有课程对 互不相同 
// 
// Related Topics 深度优先搜索 广度优先搜索 图 拓扑排序 👍 1513 👎 0


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    int count = 0;

    boolean valid = true;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] edges = new List[numCourses];


        for (int i = 0; i < numCourses; i++) {
            edges[i] = new ArrayList<>();
        }


        for (int i = 0; i < prerequisites.length; i++) {
            int a = prerequisites[i][0];
            int b = prerequisites[i][1];
            edges[b].add(a);
        }

        int[] visited = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            // 从左端点开始，向右端点遍历，终点是右端点属于visited，此时结果一定不是numCourses
            // 没有重复的端点，则结果一定为numCourses
            if (visited[i] == 0) {//不能少，visited共用
                // dfs里count必须是全局变量
                dfs(edges, i, visited);
                if (!valid) {
                    return false;
                }
            }
        }
        return true;
    }

    void dfs(List<Integer>[] edges, int i, int[] visited) {
        count++;
        visited[i] = 1;
        //        System.out.println(i + " " + Arrays.toString(visited) + " " + count);

        for (Integer j : edges[i]) {
            // 遍历过，又遇到，重复
            // 即使visited过，也要减，驱动后续dfs
            // dfs必须考虑visited
            // 初次visit，dfs

            if (visited[j] == 0) {
                // 避免成环，确保后面的元素不是j需要的元素
                dfs(edges, j, visited);
                if (!valid) {
                    return;
                }
            } else if (visited[j] == 1) {
                valid = false;
                return;
            }
        }
        visited[i] = 2;
    }

    boolean bfs(int numCourses, int[][] prerequisites) {
        // 右到左的边
        List<Integer>[] edges = new List[numCourses];
        // 边的右端点数
        int[] needCount = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < prerequisites.length; i++) {
            int a = prerequisites[i][0];
            int b = prerequisites[i][1];
            edges[b].add(a);
            needCount[a]++;
        }
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < needCount.length; i++) {
            // 没有右端点，则为线段的终点
            if (needCount[i] == 0) {
                queue.offerLast(i);
            }
        }

        int count = 0;
        while (!queue.isEmpty()) {
            int y = queue.pollFirst();
            count++;
            for (int x : edges[y]) {
                needCount[x]--; // x的右端点被遍历完，可以开始遍历x的左端点，每次遍历都是不同的右端点
                if (needCount[x] == 0) {
                    queue.offerLast(x);
                }
            }
        }

        return count == needCount.length;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
