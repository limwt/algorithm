package com.lwt.algorithm.algo

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val N = br.readLine().toInt()
    val arr = IntArray(N) { 0 }
    val visit = BooleanArray(N) { false }
    val result = mutableListOf<Int>()

    repeat(N) {
        arr[it] = it + 1
    }

    dfs(N, arr, visit, result, 0)
}

private fun dfs(n: Int, arr: IntArray, visit: BooleanArray, result: MutableList<Int>, count: Int) {
    if (count == n) {
        val ret = StringBuilder()

        result.forEach { r ->
            ret.append("$r ")
        }

        println(ret.toString())
        return
    }

    for (i in 0 until n) {
        if (!visit[i]) {
            visit[i] = true
            result.add(arr[i])
            dfs(n, arr, visit, result, count + 1)
            result.removeLast()
            visit[i] = false
        }
    }
}