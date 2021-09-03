package leetcode.editor.cn;//给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能从 s 获得的 有效 IP 地址 。你可以按任何顺序返回答案。
//
// 有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。 
//
// 例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 
//和 "192.168@1.1" 是 无效 IP 地址。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "25525511135"
//输出：["255.255.11.135","255.255.111.35"]
// 
//
// 示例 2： 
//
// 
//输入：s = "0000"
//输出：["0.0.0.0"]
// 
//
// 示例 3： 
//
// 
//输入：s = "1111"
//输出：["1.1.1.1"]
// 
//
// 示例 4： 
//
// 
//输入：s = "010010"
//输出：["0.10.0.10","0.100.1.0"]
// 
//
// 示例 5： 
//
// 
//输入：s = "10203040"
//输出：["10.20.30.40","102.0.30.40","10.203.0.40"]
// 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length <= 3000 
// s 仅由数字组成 
// 
//
// 
//
// 注意：本题与主站 93 题相同：https://leetcode-cn.com/problems/restore-ip-addresses/ 
// Related Topics 字符串 回溯 
// 👍 5 👎 0


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution_剑指_087 {

    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        helper(s, 0, 0, new StringBuilder(), new StringBuilder(), res);
        return res;

    }

    private void helper(String s, int i, int count, StringBuilder seg, StringBuilder ip, List<String> res) {
        if (i == s.length() && count == 3) {
            ip.append(seg);
            res.add(ip.toString());
            // 别少了这一项
            ip.delete(ip.length() - seg.length(), ip.length());
            return;
        }
        if (count <= 3 && i < s.length()) {
            char c = s.charAt(i);
            seg.append(c);
            if (isValid(seg.toString())) {
                helper(s, i + 1, count, seg, ip, res);
            }
            seg.deleteCharAt(seg.length() - 1);
            // 可能上一个seg没有找到
            if (seg.length() != 0 && count < 3) {
                // 注意不能用new StringBuilder(c)，c会被当成长度
                helper(s, i + 1, count + 1, new StringBuilder().append(c), ip.append(seg).append("."), res);
                ip.delete(ip.length() - seg.length() - 1, ip.length());
            }
        }

    }

    private boolean isValid(String s) {
        if (s.isEmpty()) {
            return false;
        }
        return Integer.parseInt(s) <= 255 && (s.equals("0") || s.charAt(0) != '0');
    }

    public static void main(String[] args) {
        System.out.println(new Solution_剑指_087().restoreIpAddresses("25525511135"));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
