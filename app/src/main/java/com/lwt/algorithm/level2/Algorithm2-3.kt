package com.lwt.algorithm.level2

/**
 * Input : 10, -4, 3, 1, 5, 6, -35, 12, 21, -1 주어질 때 연속된 몇 개의 수를 더했을 때 최대로 나올 수 있는 합 구하기
 * Output : 33
 */


fun main(args: Array<String>) {
    println(getResult(intArrayOf(10, -4, 3, 1, 5, 6, -35, 12, 21, -1)))
}

private fun getResult(number: IntArray): Int {
    val dp = IntArray(number.size)
    dp[0] = number[0]

    for (i in 1 until number.size) {
        dp[i] = Math.max(dp[i - 1] + number[i], number[i])
    }

    var result = Int.MIN_VALUE
    for (i in number.indices) {
        if (result < dp[i]) {
            result = dp[i]
        }
    }

    return result
}