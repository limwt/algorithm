package com.lwt.algorithm.algo

import java.io.BufferedReader
import java.io.InputStreamReader

private var prevValue = 0

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (N, M) = br.readLine().split(" ").map { it.toInt() }
    val arr = IntArray(N) { 0 }
    val visit = BooleanArray(N) { false }
    val result = mutableListOf<Int>()

    repeat(N) {
        arr[it] = it + 1
    }

    prevValue = 0
    dfs(N, M, arr, visit, 0)
}

private fun dfs(n: Int, m: Int, arr: IntArray, visit: BooleanArray, count: Int) {
    if (count == m) {
        for (i in 0 until n) {
            if (visit[i]) print("${arr[i]} ")
        }
        println("")
        return
    }

    for (i in 0 until n) {
        if (visit[i]) continue
        if (prevValue > arr[i]) continue

        visit[i] = true
        prevValue = arr[i]
        dfs(n, m, arr, visit, count + 1)
        visit[i] = false
        prevValue = 0
    }
}