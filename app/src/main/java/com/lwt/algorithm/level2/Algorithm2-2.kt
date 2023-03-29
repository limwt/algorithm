package com.lwt.algorithm.level2

/**
 * 주어진 숫자 중 3개의 수를 더했을 때 소수가 되는 경우의 수를 구하라
 *
 * Input : [1, 2, 11, 19, 4]
 * Output : 3
 */


fun main(args: Array<String>) {
    println(getResult(intArrayOf(1, 2, 11, 19, 4)))
}

private fun getResult(nums: IntArray): Int {
    var answer = 0

    for (i in 0..nums.size - 3) {
        for (j in i + 1..nums.size - 2) {
            for (k in j + 1..nums.size - 1) {
                val sum = nums[i] + nums[j] + nums[k]

                if (isNonPrimeNumber(sum)) continue

                answer++
            }
        }
    }

    return answer
}

private fun isNonPrimeNumber(number: Int): Boolean {
    for (i in 2 until number) {
        if (number % i == 0) return true
    }

    return false
}