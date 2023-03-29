package com.lwt.algorithm.algo

import java.io.BufferedReader
import java.io.InputStreamReader

private var answer10819 = Int.MIN_VALUE

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val N = br.readLine().toInt()
    val number = br.readLine().split(" ").map { it.toInt() }.toIntArray()
    val visit = BooleanArray(N) { false }
    val result = IntArray(N) { 0 }
    dfs(N, number, visit, result, 0)
    println(answer10819)
}

private fun dfs(n: Int, num: IntArray, visit: BooleanArray, ret: IntArray, depth: Int) {
    if (depth == n) {
        // 최대 값 구하기
        answer10819 = Math.max(answer10819, getSum(n, ret))
        return
    }

    for (i in 0 until n) {
        if (!visit[i]) {
            visit[i] = true
            ret[depth] = num[i]
            dfs(n, num, visit, ret, depth + 1)
            visit[i] = false
        }
    }
}

private fun getSum(n: Int, ret: IntArray): Int {
    var result = 0

    for (i in 0 until (n -1) ) {
        result += Math.abs(ret[i] - ret[i + 1])
    }

    return result
}
