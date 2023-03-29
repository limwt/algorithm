package com.lwt.algorithm.bitmask

import java.io.BufferedReader
import java.io.InputStreamReader

private val result = IntArray(2000001) { 1 } // 부분 수열로 나올 경우 해당 되는 index 를  0으로 업데이트 한다.

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val N = br.readLine().toInt()
    val S = br.readLine().split(" ").map { it.toInt() }.toIntArray()

    for (i in 1..N) {
        val visit = BooleanArray(N) { false }
        dfs(N, S, visit, 0, 0, i)
    }

    for (i in 1..2000000) {
        if (result[i] != 0) {
            println(i)
            break
        }
    }
}

private fun dfs(N: Int, S: IntArray, visit: BooleanArray, start: Int, depth: Int, target: Int) {
    if (depth == target) {
        var ret = 0
        S.forEachIndexed { index, s ->
            if (visit[index]) ret += s
        }

        result[ret] = 0
        return
    }

    for (i in start until N) {
        if (visit[i]) continue

        visit[i] = true
        dfs(N, S, visit, i, depth + 1, target)
        visit[i] = false
    }
}