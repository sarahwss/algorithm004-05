<?php

class Solution_39 {

    /**
     * @param Integer $num
     * @return Integer[]
     */
    function countBits($num) {
        $arr[0] = 0;
    for($i=1;$i<=$num;$i++){
        $arr[$i] = $arr[$i>>1] + ($i&1);
    }
    return $arr;
    }
}
