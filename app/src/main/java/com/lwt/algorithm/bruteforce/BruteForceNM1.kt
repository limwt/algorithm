package com.lwt.algorithm.algo

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (N, M) = br.readLine().split(" ").map { it.toInt() }
    val arr = IntArray(N) { 0 }
    val visit = BooleanArray(N) { false }
    val result = IntArray(M) { 0 }

    repeat(N) {
        arr[it] = it + 1
    }

    dfs(N, M, arr, visit, result, 0)
}

private fun dfs(n: Int, m: Int, arr: IntArray, visit: BooleanArray, result: IntArray, count: Int) {
    if (count == m) {
        for (r in result) {
            print("$r ")
        }
        println("")
        return
    }

    for (i in 0 until n) {
        if (!visit[i]) {
            visit[i] = true
            result[count] = arr[i]
            dfs(n, m, arr, visit, result, count + 1)
            visit[i] = false
        }
    }
}