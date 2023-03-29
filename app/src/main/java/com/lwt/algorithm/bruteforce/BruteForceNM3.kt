package com.lwt.algorithm.algo

import java.io.BufferedReader
import java.io.InputStreamReader

private val ret = StringBuilder()

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (N, M) = br.readLine().split(" ").map { it.toInt() }
    val number = IntArray(N) { 0 }

    repeat(N) {
        number[it] = it + 1
    }

    val result = IntArray(M) { 0 }
    dfs(N, M, number, result, 0, 0)
    println(ret.toString())
}

private fun dfs(n: Int, m: Int, arr: IntArray, result: IntArray, start: Int, count: Int) {
    if (count == m) {
        // 결과 출력...
        for (r in result) {
            ret.append("$r ")
        }

        ret.append("\n")
        return
    }

    for (i in 0 until n) {
        result[count] = arr[i]
        dfs(n, m, arr, result, i, count + 1)
    }
}