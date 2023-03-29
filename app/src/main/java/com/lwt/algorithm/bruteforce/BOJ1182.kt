package com.lwt.algorithm.algo

import java.io.BufferedReader
import java.io.InputStreamReader

private var answer = 0

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (N, S) = br.readLine().split(" ").map { it.toInt() }
    val arr = br.readLine().split(" ").map { it.toInt() }.toIntArray()

    for (i in 1..N) {
        val visit = BooleanArray(N) { false }
        dfs(i, S, arr, visit, 0, 0, 0, N)
    }

    println(answer)
}

private fun dfs(N: Int, S: Int, arr: IntArray, visit: BooleanArray, start: Int, depth: Int, sum: Int, size: Int) {
    if (depth == N) {
        if (sum == S) answer++
        return
    }

    for (i in start until size) {
        if (!visit[i]) {
            visit[i] = true
            dfs(N, S, arr, visit, i, depth + 1, sum + arr[i], size)
            visit[i] = false
        }
    }
}