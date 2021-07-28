package leetcode.editor.cn;//机器人在一个无限大小的 XY 网格平面上行走，从点 (0, 0) 处开始出发，面向北方。该机器人可以接收以下三种类型的命令 commands ：
//
// 
// -2 ：向左转 90 度 
// -1 ：向右转 90 度 
// 1 <= x <= 9 ：向前移动 x 个单位长度 
// 
//
// 在网格上有一些格子被视为障碍物 obstacles 。第 i 个障碍物位于网格点 obstacles[i] = (xi, yi) 。 
//
// 机器人无法走到障碍物上，它将会停留在障碍物的前一个网格方块上，但仍然可以继续尝试进行该路线的其余部分。 
//
// 返回从原点到机器人所有经过的路径点（坐标为整数）的最大欧式距离的平方。（即，如果距离为 5 ，则返回 25 ） 
//
// 
// 
// 
// 
// 
// 
//
// 
// 注意： 
//
// 
// 北表示 +Y 方向。 
// 东表示 +X 方向。 
// 南表示 -Y 方向。 
// 西表示 -X 方向。 
// 
// 
// 
// 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：commands = [4,-1,3], obstacles = []
//输出：25
//解释：
//机器人开始位于 (0, 0)：
//1. 向北移动 4 个单位，到达 (0, 4)
//2. 右转
//3. 向东移动 3 个单位，到达 (3, 4)
//距离原点最远的是 (3, 4) ，距离为 32 + 42 = 25 
//
// 示例 2： 
//
// 
//输入：commands = [4,-1,4,-2,4], obstacles = [[2,4]]
//输出：65
//解释：机器人开始位于 (0, 0)：
//1. 向北移动 4 个单位，到达 (0, 4)
//2. 右转
//3. 向东移动 1 个单位，然后被位于 (2, 4) 的障碍物阻挡，机器人停在 (1, 4)
//4. 左转
//5. 向北走 4 个单位，到达 (1, 8)
//距离原点最远的是 (1, 8) ，距离为 12 + 82 = 65 
//
// 
//
// 提示： 
//
// 
// 1 <= commands.length <= 104 
// commands[i] is one of the values in the list [-2,-1,1,2,3,4,5,6,7,8,9]. 
// 0 <= obstacles.length <= 104 
// -3 * 104 <= xi, yi <= 3 * 104 
// 答案保证小于 231 
// 
// Related Topics 数组 模拟 
// 👍 144 👎 0

import java.util.HashSet;
import java.util.Set;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution_874_2 {

    public int robotSim(int[] commands, int[][] obstacles) {
        int n = commands.length;
        Set<String> obs = new HashSet<>();
        for (int i = 0; i < obstacles.length; i++) {
            // 注意这里是两维，不是1维
            obs.add(obstacles[i][0] + "," + obstacles[i][1]);
        }
        // 右转的坐标
        int[][] dd = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        // 方向索引
        int dir = 0;
        int res = 0;
        int x = 0, y = 0;
        for (int i = 0; i < n; i++) {
            int command = commands[i];
            // 向右转
            if (command == -1) {
                dir = (dir + 1) % 4;
                // 向左转
            } else if (command == -2) {
                dir = (dir + 3) % 4;
                // 往指定方向走
            } else {
                // 每一步都判断是否有障碍，注意障碍表达式不是x,y
                while (command-- > 0 && !obs.contains((x + dd[dir][0]) + "," + (y + dd[dir][1]))) {
                    x += dd[dir][0];
                    y += dd[dir][1];
                }
                // 注意这里要有max，取所有点的最远距离，不是最后一个距离
                res = Math.max(res, x * x + y * y);
            }
        }
        return res;
    }
}

class Solution_874_1 {

    public int robotSim(int[] commands, int[][] obstacles) {
        int n = commands.length;
        Set<String> obs = new HashSet<>();
        for (int i = 0; i < obstacles.length; i++) {
            // 注意这里是两维，不是1维
            obs.add(obstacles[i][0] + "," + obstacles[i][1]);
        }
        return DFS(commands, 0, n, 0, 0, 0, obs);
    }

    int DFS(int[] commands, int i, int n, int x, int y, int dir, Set<String> obs) {
        int res = x * x + y * y;
        if (i == n) {
            return res;
        }
        // 右转的坐标
        int[][] dd = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int command = commands[i];
        // 向右转
        if (command == -1) {
            return DFS(commands, i + 1, n, x, y, (dir + 1) % 4, obs);
            // 向左转
        } else if (command == -2) {
            return DFS(commands, i + 1, n, x, y, (dir + 3) % 4, obs);
            // 往指定方向走
        } else {
            // 每一步都判断是否有障碍，注意障碍表达式不是x,y
            while (command-- > 0 && !obs.contains((x + dd[dir][0]) + "," + (y + dd[dir][1]))) {
                x += dd[dir][0];
                y += dd[dir][1];
            }
            // 注意这里要有max，取所有点的最远距离，不是最后一个距离
            return Math.max(res, DFS(commands, i + 1, n, x, y, dir, obs));
        }
    }
}

class Solution_874 {

    public int robotSim(int[] commands, int[][] obstacles) {
        int n = commands.length;
        return DFS(commands, obstacles, 0, n, new int[]{0, 0}, 0);
    }

    int DFS(int[] commands, int[][] obstacles, int i, int n, int[] index, int dir) {
        int x = index[0];
        int y = index[1];
        int res = x * x + y * y;
        if (i == n) {
            return res;
        }
        // 右转的坐标
        int[][] dd = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int command = commands[i];
        // 向右转
        if (command == -1) {
            //            System.out.println(i + " " + Arrays.toString(index));
            return DFS(commands, obstacles, i + 1, n, index, (dir + 1) % 4);
            // 向左转
        } else if (command == -2) {
            //            System.out.println(i + " " + Arrays.toString(index));
            return DFS(commands, obstacles, i + 1, n, index, (dir + 3) % 4);
            // 往指定方向走
        } else {
            int[] newIndex = new int[]{x + dd[dir][0] * command, y + dd[dir][1] * command};
            // 不变的index
            int a = (dd[dir][0] == 0 ? 0 : 1);
            // 变的index
            int b = (dd[dir][0] == 0 ? 1 : 0);
            for (int j = 0; j < obstacles.length; j++) {
                if (obstacles[j][a] == index[a]) {
                    // 注意这里不能有等号，当前位置不可能在障碍物上，题目存在原点有障碍物的情况，但起点仍然在障碍物，这种情况，不可能因为原点有障碍物就往后退
                    if (dd[dir][b] == 1 && obstacles[j][b] > index[b] && obstacles[j][b] <= newIndex[b]) {
                        // 被忘了-1和+1,不可能走到障碍物上，只能走到他前面
                        newIndex[b] = obstacles[j][b] - 1;
                    } else if (dd[dir][b] == -1 && obstacles[j][b] < index[b] && obstacles[j][b] >= newIndex[b]) {
                        newIndex[b] = obstacles[j][b] + 1;
                    }
                }
            }
            //            System.out.println(i + " " + Arrays.toString(newIndex));
            // 注意这里要有max，取所有点的最远距离，不是最后一个距离
            return Math.max(res, DFS(commands, obstacles, i + 1, n, newIndex, dir));
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
