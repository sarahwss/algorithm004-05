<?php
class Solution_39 {

    /**
     * @param Integer[] $digits
     * @return Integer[]
     */
    function plusOne($digits) {

        $len = count($digits);
        for($i = $len - 1; $i >= 0; $i--) {
            $digits[$i]++;
            $digits[$i] %= 10;
            if($digits[$i]!=0)
                return $digits;
        }

        array_unshift($digits, 1);
        return $digits;

    }
}
