package com.lwt.algorithm.level1

/**
 * 주어진 [3, 7, 21, 10, 9, 11, 15] 배열에서 3으로 나누어 떨어지는 원소들을 오름차순으로 배열 하기
 *
 * Input : [3, 7, 21, 10, 9, 11, 15]
 * Output : [3, 9, 15, 21]
 */


fun main(args: Array<String>) {
    val result = getResult(intArrayOf(3, 7, 21, 10, 9, 11, 15))

    result.forEach { r ->
        print("$r ")
    }
}

private fun getResult(input: IntArray): IntArray {
    val result = mutableListOf<Int>()
    input.forEach { i ->
        if (i % 3 == 0) result.add(i)
    }

    result.sort()

    return result.toIntArray()
}