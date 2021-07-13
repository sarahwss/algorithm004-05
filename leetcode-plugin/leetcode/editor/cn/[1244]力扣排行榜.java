package leetcode.editor.cn;//新一轮的「力扣杯」编程大赛即将启动，为了动态显示参赛者的得分数据，需要设计一个排行榜 Leaderboard。
//
// 请你帮忙来设计这个 Leaderboard 类，使得它有如下 3 个函数： 
//
// 
// addScore(playerId, score)：
//
// 
// 假如参赛者已经在排行榜上，就给他的当前得分增加 score 点分值并更新排行。 
// 假如该参赛者不在排行榜上，就把他添加到榜单上，并且将分数设置为 score。 
// 
// 
// top(K)：返回前 K 名参赛者的 得分总和。 
// reset(playerId)：将指定参赛者的成绩清零（换句话说，将其从排行榜中删除）。题目保证在调用此函数前，该参赛者已有成绩，并且在榜单上。 
// 
//
// 请注意，在初始状态下，排行榜是空的。 
//
// 
//
// 示例 1： 
//
// 
//输入： 
//["Leaderboard","addScore","addScore","addScore","addScore","addScore","top","r
//eset","reset","addScore","top"]
//[[],[1,73],[2,56],[3,39],[4,51],[5,4],[1],[1],[2],[2,51],[3]]
//输出：
//[null,null,null,null,null,null,73,null,null,null,141]
//
//解释： 
//Leaderboard leaderboard = new Leaderboard ();
//leaderboard.addScore(1,73);   // leaderboard = [[1,73]];
//leaderboard.addScore(2,56);   // leaderboard = [[1,73],[2,56]];
//leaderboard.addScore(3,39);   // leaderboard = [[1,73],[2,56],[3,39]];
//leaderboard.addScore(4,51);   // leaderboard = [[1,73],[2,56],[3,39],[4,51]];
//leaderboard.addScore(5,4);    // leaderboard = [[1,73],[2,56],[3,39],[4,51],[5
//,4]];
//leaderboard.top(1);           // returns 73;
//leaderboard.reset(1);         // leaderboard = [[2,56],[3,39],[4,51],[5,4]];
//leaderboard.reset(2);         // leaderboard = [[3,39],[4,51],[5,4]];
//leaderboard.addScore(2,51);   // leaderboard = [[2,51],[3,39],[4,51],[5,4]];
//leaderboard.top(3);           // returns 141 = 51 + 51 + 39;
// 
//
// 
//
// 提示： 
//
// 
// 1 <= playerId, K <= 10000 
// 题目保证 K 小于或等于当前参赛者的数量 
// 1 <= score <= 100 
// 最多进行 1000 次函数调用 
// 
// Related Topics 设计 哈希表 排序 
// 👍 24 👎 0


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class Leaderboard {

    // 倒序排序的playerId和score，playerId用于从数据找到，更新mao中的序号
    int[][] scores;

    int size = 0;

    public Leaderboard() {
        scores = new int[10001][];
    }

    public void addScore(int playerId, int score) {
        int index = searchIndex(playerId);
        if (index == -1) {
            // 新加元素，从新位置往前插入
            index = size;
        } else {
            score = scores[index][1] + score;
            // 分数变高，从当前往前插入排序
        }
        insert(playerId, score, index);
    }

    // 倒序插入排序，加了值后要往前找位置插入排序，返回插入位置的inde
    private void insert(int playerId, int score, int index) {
        if (index == size) {
            size++;
        }
        while (index - 1 >= 0 && scores[index - 1][1] < score) {
            scores[index] = scores[index - 1];
            index--;
        }
        scores[index] = new int[]{playerId, score};
    }

    public int top(int K) {
        //        System.out.println(K + " " + playerIdMap);
        //        for (int i = 0; i < playerIdMap.size(); i++) {
        //            System.out.println(Arrays.toString(scores[i]) + " ");
        //        }
        int sum = 0;
        for (int i = 0; i < K; i++) {
            sum += scores[i][1];
        }
        return sum;
    }

    public void reset(int playerId) {
        int index = searchIndex(playerId);
        if (index != -1) {
            while (index + 1 < size) {
                scores[index] = scores[index + 1];
                index++;
            }
            scores[index] = null;
            size--;
        }
    }

    private int searchIndex(int playerId) {
        for (int i = 0; i < size; i++) {
            if (scores[i][0] == playerId) {
                return i;
            }
        }
        return -1;
    }
}

/**
 * Your Leaderboard object will be instantiated and called as such:
 * Leaderboard obj = new Leaderboard();
 * Leaderboard obj = new Leaderboard();
 * obj.addScore(playerId,score);
 * int param_2 = obj.top(K);
 * obj.reset(playerId);
 */
//leetcode submit region end(Prohibit modification and deletion)
