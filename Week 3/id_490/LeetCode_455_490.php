<?php
/**
 * 题目名称：assign-cookies 分发饼干
 * 题目链接：https://leetcode-cn.com/problems/assign-cookies/
 */

class Solution_39 {

    /**
     * @param Integer[] $children
     * @param Integer[] $cookies
     * @return Integer
     */
    function findContentChildren($children, $cookies) {
        sort($children);
        sort($cookies);
        $child = 0;
        for ($cookie = 0; $child < count($children) && $cookie < count($cookies); $cookie++) {
            if ($cookies[$cookie] >= $children[$child]) {
                $child++;
            }
        }
        return $child;
    }
}

$solution = new Solution_39();
$children = [1, 2, 3]; 
$cookies = [1, 2];
$index = $solution->findContentChildren($children, $cookies);
var_dump($index);