package com.lwt.algorithm.level1

/**
 * 5 개의 입력(1, 5, 9, 3, 5)이 주어 졌을 때
 * 서로 다른 2개의 수를 뽑아서 더한 값 중에서 최대값을 구하라
 *
 * Input : [1, 5, 9, 3, 5]
 * Output : 14
 */


fun main(args: Array<String>) {
    println(getMax(intArrayOf(1, 5, 9, 3, 5)))
}

private fun getMax(input: IntArray): Int {
    var result = Int.MIN_VALUE

    for (i in 0 until input.size - 1) {
        for (j in i + 1 until input.size) {
            val value = input[i] + input[j]

            if (result < value)
                result = value
        }
    }

    return result
}