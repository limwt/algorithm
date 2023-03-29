package com.lwt.algorithm.level1

/**
 * [1, 5, 2, 6, 3, 7, 4] 배열에서
 * 2번째 인덱스 부터 5번째 인덱스까지의 원소들을 추출한 새로운 배열에서 2번째 값 구하라
 *
 * Input : [1, 5, 2, 6, 3, 7, 4]
 * Output : 2
 */


fun main(args: Array<String>) {
    println(getResult(intArrayOf(1, 5, 2, 6, 3, 7, 4)))
}

private fun getResult(input: IntArray): Int {
    val subList = input.asList().subList(1, 4)
    return subList[1]
}