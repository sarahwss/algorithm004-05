<?php

class Solution_39 {

    /**
     * @param Integer[] $nums
     * @return Integer
     */
    function removeDuplicates(&$nums) {
        $index = 1;
        for ($i = 1; $i<count($nums); $i++) {
            if ($nums[$i] !== $nums[$i - 1]) {
                $nums[$index++] = $nums[$i];
            }
        }
        return $index;
    }
}
